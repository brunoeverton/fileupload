package br.com.hotmart.fileupload;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.hotmart.fileupload.enums.ResponseStatusEnum;
import br.com.hotmart.fileupload.service.FileInfoService;
import br.com.hotmart.fileupload.vo.FileUploadVO;
import br.com.hotmart.fileupload.vo.ResponseVO;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FileUploadTest extends TestCase
{
	@Autowired
	private FileInfoService fileInfoService;
	
	
    @Test
    public void findFiles()
    {
		assertNotNull(fileInfoService.findAllAsFileUploadVO());
    }
    
    @Test
    public void saveFile()
    {
    		StringBuilder fileName = new StringBuilder();
    		fileName.append("Arquivo_teste_").append(new Date().getTime()).append(".txt");
    		MockMultipartFile firstFile = new MockMultipartFile("data", fileName.toString(), "text/plain", "Teste de conteúdo de arquivo.".getBytes());
    		ResponseVO<FileUploadVO> response = fileInfoService.save(firstFile, fileName.toString(), "teste@teste.com", 1, 0);
    		assertNotNull(response.getResponse().getId());
    }
    
    @Test
    public void duplicatedFile()
    {
    		StringBuilder fileName = new StringBuilder();
    		fileName.append("Arquivo_teste_").append(new Date().getTime()).append(".txt");
    		
    		MockMultipartFile firstFile = new MockMultipartFile("data", fileName.toString(), "text/plain", "Teste de conteúdo de arquivo.".getBytes());
    		ResponseVO<FileUploadVO> response = fileInfoService.save(firstFile, fileName.toString(), "teste@teste.com", 1, 0);
    		
    		
    		firstFile = new MockMultipartFile("data", fileName.toString(), "text/plain", "Teste de conteúdo de arquivo.".getBytes());
    		response = fileInfoService.save(firstFile, fileName.toString(), "teste@teste.com", 1, 0);    		
    		assertTrue(ResponseStatusEnum.ERROR.equals(response.getStatus()));
    }
    
    @Test
    public void getFile() {
		
    		StringBuilder fileName = new StringBuilder();
		fileName.append("Arquivo_teste_download_").append(new Date().getTime()).append(".txt");
		MockMultipartFile firstFile = new MockMultipartFile("data", fileName.toString(), "text/plain", "Teste de conteúdo de arquivo.".getBytes());
		ResponseVO<FileUploadVO> response = fileInfoService.save(firstFile, fileName.toString(), "teste@teste.com", 1, 0);
		Long id = response.getResponse().getId();
		
		br.com.hotmart.fileupload.entity.FileInfo fileInfo = fileInfoService.findByIdFetchFileData(id);
		assertNotNull(fileInfo.getFileData().getId());
    }
}
