package br.com.hotmart.fileupload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.hotmart.fileupload.builder.ResponseBuilder;
import br.com.hotmart.fileupload.entity.FileInfo;
import br.com.hotmart.fileupload.service.FileInfoService;
import br.com.hotmart.fileupload.vo.FileUploadVO;
import br.com.hotmart.fileupload.vo.ResponseVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fileupload")
public class FileUploadController {

	@Autowired
	private FileInfoService fileInfoService;

	@Value("${file.directory}")
	private String fileDirectory;
	

	@ApiOperation(value = "Enviar arquivo", nickname = "uploadfile",notes="Realiza o upload do arquivo, ou das partes do arquivo. Cada parte do arquivo deve conter no máximo 1Mb")
	@RequestMapping(method = RequestMethod.POST, value = "/uploadfile")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "Arquivo ou parte do arquivo para upload. Máximo: 1048576 bytes", required = false, dataType = "file", paramType = "query"),
        @ApiImplicitParam(name = "userEmail", value = "E-mail do usuário que está fazendo o upload do arquivo", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "name", value = "Nome do arquivo para upload", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "chunks", value = "Quantidade de partes que o arquivo foi dividido", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "chunk", value = "Indice da parte que está sendo enviado atualmente. As partes do arquivo devem ser enviadas em sequência. A contagem se inicia com 0.", dataType = "int", paramType = "query"),
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = ResponseVO.class),
            @ApiResponse(code = 500, message = "FileSizeLimitExceeded")})
	public ResponseVO<FileUploadVO> uploadMedia(@RequestParam(required = true, value = "file") MultipartFile file,
			@RequestParam(required = false, value = "userEmail") String userEmail,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "chunks") Integer chunks,
			@RequestParam(required = false, value = "chunk") Integer chunk) throws IOException {

		return fileInfoService.save(file, name, userEmail, chunks, chunk);
	}
	
	@ApiOperation(value = "Listar arquivos" , nickname = "findFiles", notes="Retorna as informações dos arquivos salvos no banco de dados. A listagem está em ordem decrescente pela data de término do upload.")
	@RequestMapping(method = RequestMethod.GET, value = "/findFiles")
	public ResponseVO<List<FileUploadVO>> uploadMedia() {
		List<FileUploadVO> fileList = fileInfoService.findAllAsFileUploadVO();
		return new ResponseBuilder<List<FileUploadVO>>(fileList).build();
	}

	@ApiOperation(value = "Baixar arquivo", notes="Retorna o arquivo para download.", nickname="download/{fileInfoId}", produces="application/octet-stream")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/download/{fileInfoId}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(@ApiParam(value = "Id do arquivo para download") @PathVariable("fileInfoId") Long fileInfoId) throws IOException {
		FileInfo fileInfo = fileInfoService.findByIdFetchFileData(fileInfoId);
		if(fileInfo==null || fileInfo.getFileData()==null) {
			return new ResponseEntity ("File Not Found", HttpStatus.OK);
		}
		
		ByteArrayResource resource = new ByteArrayResource(fileInfo.getFileData().getBytes());		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment; filename=" + fileInfo.getName());
		
		return ResponseEntity.ok().contentLength(fileInfo.getFileData().getBytes().length).headers(headers)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

}
