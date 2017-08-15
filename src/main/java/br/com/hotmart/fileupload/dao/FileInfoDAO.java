package br.com.hotmart.fileupload.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.fileupload.entity.FileInfo;
import br.com.hotmart.fileupload.entity.User;
import br.com.hotmart.fileupload.vo.FileUploadVO;

/**
 * Interface de persistencia de arquivo
 * @author brunoeverton
 *
 */
public interface FileInfoDAO extends JpaRepository<FileInfo, Long>{
	
	/**
	 * Busca arquivo pelo nome e usuário
	 * @param name
	 * @param user
	 * @return
	 */
	FileInfo findByNameAndUser(String name, User user);
	
	/**
	 * Retorna a informacao do arquivo com seus bytes
	 * @param id
	 * @return
	 */
	@Query("SELECT f FROM FileInfo f LEFT JOIN FETCH f.fileData WHERE f.id=:id")
	FileInfo findByIdFetchFileData(@Param("id") Long id);
	
	/**
	 * Retorna a lista de Arquivos ordenados pela data de termino de upload decrescente
	 * @return
	 */
	@Query("SELECT new br.com.hotmart.fileupload.vo.FileUploadVO(f) FROM FileInfo f ORDER BY f.uploadFinish DESC")
	List<FileUploadVO> findAllAsFileUploadVO();
	
	
}
