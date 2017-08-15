package br.com.hotmart.fileupload.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotmart.fileupload.entity.FileData;

/**
 * Interface de persistencia de bytes do arquivo
 * @author brunoeverton
 *
 */
public interface FileDataDAO  extends JpaRepository<FileData, Long>{

}
