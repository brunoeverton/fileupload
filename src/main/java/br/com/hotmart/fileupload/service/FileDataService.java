package br.com.hotmart.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotmart.fileupload.dao.FileDataDAO;
import br.com.hotmart.fileupload.entity.FileData;
import br.com.hotmart.fileupload.exception.FileUploadException;

/**
 * Classe de servico de usuario
 * @author brunoeverton
 *
 */
@Service
public class FileDataService {

	private final Logger LOGGER = LoggerFactory.getLogger(FileDataService.class);
	private final String ERRO_LER_ARQUIVO = "Erro ao ler arquivo temporário";
	private final String ERRO_SALVAR_ARQUIVO = "Erro ao salvar arquivo em banco. Verifique as configurações do banco de dados.";
	
	@Autowired
	private FileDataDAO fileDataDAO;
	

	/**
	 * Cria arquivo
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	public FileData create(File file) throws FileUploadException {
		FileData fileData;
		try {
			fileData = new FileData(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {			
			LOGGER.error(ERRO_LER_ARQUIVO, e);
			throw new FileUploadException(ERRO_LER_ARQUIVO, e);
		}
		try {
			return fileDataDAO.save(fileData);
		} catch (Exception e) {
			LOGGER.error(ERRO_SALVAR_ARQUIVO, e);
			throw new FileUploadException(ERRO_SALVAR_ARQUIVO, e);
		}
	}
	
}
