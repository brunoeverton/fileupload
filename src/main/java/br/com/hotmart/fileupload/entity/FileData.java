package br.com.hotmart.fileupload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bytes do arquivo
 * @author brunoeverton
 *
 */
@Entity
@Table(name="file_data")
public class FileData extends BaseEntity{

	private static final long serialVersionUID = -2357303186902506607L;
	
	@Column(name = "bytes", columnDefinition="longblob")
	private byte[] bytes;

	/**
	 * Cria instancia do arquivo
	 */
	public FileData() {
		super();
	}
	/**
	 * Cria instancia e inicializa com os dados do arquivo
	 * @param bytes
	 */
	public FileData(byte[] bytes) {
		super();
		this.bytes = bytes;
	}


	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
