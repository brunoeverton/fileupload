package br.com.hotmart.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.hotmart.fileupload.builder.ResponseBuilder;
import br.com.hotmart.fileupload.dao.FileInfoDAO;
import br.com.hotmart.fileupload.entity.FileData;
import br.com.hotmart.fileupload.entity.FileInfo;
import br.com.hotmart.fileupload.entity.User;
import br.com.hotmart.fileupload.enums.FileStatusEnum;
import br.com.hotmart.fileupload.exception.FileUploadException;
import br.com.hotmart.fileupload.vo.FileUploadVO;
import br.com.hotmart.fileupload.vo.ResponseVO;

/**
 * Classe de servico do arquivo
 * @author brunoeverton
 *
 */
@Service
public class FileInfoService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FileInfoService.class);
	
	@Autowired
	private FileInfoDAO fileDAO;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Value("${file.directory}")
	private String fileDirectory;
	
	/**
	 * Busca arquivo pelo nome e usuário
	 * @param name
	 * @param user
	 * @return
	 */
	public FileInfo findByNameAndUser(String name, User user) {
		return fileDAO.findByNameAndUser(name, user);
	};
	
	/**
	 * Valida as informacoes e salva o arquivo
	 * @param file
	 * @param originalFileName
	 * @param userEmail
	 * @param chunks
	 * @param chunk
	 * @return
	 */
	public ResponseVO<FileUploadVO> save(MultipartFile multipartFile, String originalFileName,String userEmail, Integer chunks, Integer chunk) {
		
		ResponseBuilder<FileUploadVO> responseBuilder = new ResponseBuilder<FileUploadVO>();;
		responseBuilder.validateRequired("E-mail", userEmail);
		responseBuilder.validateRequired("Nome do arquivo", originalFileName);
		
		if(responseBuilder.hasError()) {
			return responseBuilder.build();
		}
		
		chunks = chunks==null ? 1 : chunks;
		chunk = chunk==null ? 0 : chunk;
		FileInfo fileInfo = getFileInfo(responseBuilder, originalFileName, userEmail, chunks, chunk);
		if(responseBuilder.hasError()) {
			return responseBuilder.build();
		}
		
		File file = appendFileBytes(multipartFile, originalFileName, userEmail, responseBuilder);
		
		//Atualiza info arquivo
		fileInfo.setUploadFinish(new Date().getTime());
		fileInfo.setLastBlockReceived(chunk);
		
		
		//Se for o ultimo arquivo finaliza e salva os dados em banco
		if(chunk >= (chunks - 1)) {
			try {
				FileData fileData = fileDataService.create(file);
				fileInfo.setStatus(FileStatusEnum.CONCLUIDO);
				fileInfo.setFileData(fileData);
				file.delete();
			} catch (FileUploadException e) {
				fileInfo.setStatus(FileStatusEnum.FALHA);
				responseBuilder.addErrorMessage(e.getMessage());
				LOGGER.debug(e.getMessage());
			}
			
		}
		
		fileDAO.save(fileInfo);
		responseBuilder.setEntityReturn(new FileUploadVO(fileInfo));		
		
		return responseBuilder.build();
	}

	private File appendFileBytes(MultipartFile multipartFile,String originalFileName,String userEmail, ResponseBuilder<FileUploadVO> responseBuilder) {
		String filePath = fileDirectory + userEmail + "_" + originalFileName;
		File file = new File(filePath);
		try {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes(), true);
		} catch (IOException e) {
			responseBuilder.addErrorMessage("Problema ao criar arquivo temporário. " );
			LOGGER.error("Problema ao criar arquivo temporário. ", e.getMessage());
		}
		
		return file;
	}	
	private FileInfo getFileInfo(ResponseBuilder<FileUploadVO> responseBuilder, String originalFileName,String userEmail, Integer chunks, Integer chunk) {
		User user = userService.getByEmail(userEmail);
		FileInfo fileInfo = findByNameAndUser(originalFileName, user);
		
		if(fileInfo==null) {
			fileInfo = new FileInfo(originalFileName, FileStatusEnum.EM_ANDAMENTO, new Date(), chunks, user);
		}else if(FileStatusEnum.CONCLUIDO.equals(fileInfo.getStatus())) {
			responseBuilder.addErrorMessage("Este arquivo já foi enviado por este usuário.");
		}else if(fileInfo.getLastBlockReceived()>=chunk) {
			responseBuilder.addErrorMessage("Esta parte do arquivo já foi recebida");
		}else if(fileInfo.getLastBlockReceived()+1<chunk) {
			responseBuilder.addErrorMessage("O arquivo está incompleto. A última parte recebida do arquivo foi: " + fileInfo.getLastBlockReceived());
		}
		
		return fileInfo;
	}
	/**
	 * Retorna a lista de Arquivos ordenados pela data de termino de upload decrescente
	 * @return
	 */
	public List<FileUploadVO> findAllAsFileUploadVO() {
		return fileDAO.findAllAsFileUploadVO();
	}
	
	/**
	 * Retorna a informacao do arquivo com seus bytes
	 * @param id
	 * @return
	 */	
	public FileInfo findByIdFetchFileData(Long id) {
		return fileDAO.findByIdFetchFileData(id);
	}

}
