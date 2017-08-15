package br.com.hotmart.fileupload.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.hotmart.fileupload.enums.FileStatusEnum;

/**
 * Dados do upload do arquivo
 * @author brunoeverton
 *
 */
@Entity
@Table(name="file_info")
public class FileInfo extends BaseEntity{

	private static final long serialVersionUID = 901571458713845637L;

	@Column(name = "name", nullable = false,length=200)
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false, columnDefinition="tinyint")
	private FileStatusEnum status;
	
	@Column(name = "upload_start", nullable = false)
	private Long uploadStart;
	
	@Column(name = "upload_finish", nullable = false)
	private Long uploadFinish;
	
	@Column(name = "blocks_amount", nullable = false)
	private Integer blocksAmount;	
	
	@Column(name = "last_block_received", nullable = false)
	private Integer lastBlockReceived;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_data_id")
	private FileData fileData;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/**
	 * Cria instancia da entidade de info do arquivo
	 */
	public FileInfo() {
		super();
	}
	
	/**
	 * Cria instancia da entidade de info do arquivo
	 * @param name
	 * @param status
	 * @param uploadStart
	 * @param blocksAmount
	 * @param user
	 */
	public FileInfo(String name, FileStatusEnum status, Date uploadStart, Integer blocksAmount, User user) {
		this();
		this.name = name;
		this.status = status;
		this.uploadStart = uploadStart.getTime();
		this.blocksAmount = blocksAmount;
		this.user = user;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public FileStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(FileStatusEnum status) {
		this.status = status;
	}

	/**
	 * @return the uploadStart
	 */
	public Long getUploadStart() {
		return uploadStart;
	}

	/**
	 * @param uploadStart the uploadStart to set
	 */
	public void setUploadStart(Long uploadStart) {
		this.uploadStart = uploadStart;
	}

	/**
	 * @return the uploadFinish
	 */
	public Long getUploadFinish() {
		return uploadFinish;
	}

	/**
	 * @param uploadFinish the uploadFinish to set
	 */
	public void setUploadFinish(Long uploadFinish) {
		this.uploadFinish = uploadFinish;
	}

	/**
	 * @return the blocksAmount
	 */
	public Integer getBlocksAmount() {
		return blocksAmount;
	}

	/**
	 * @param blocksAmount the blocksAmount to set
	 */
	public void setBlocksAmount(Integer blocksAmount) {
		this.blocksAmount = blocksAmount;
	}

	/**
	 * @return the fileData
	 */
	public FileData getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the lastBlockReceived
	 */
	public Integer getLastBlockReceived() {
		return lastBlockReceived;
	}

	/**
	 * @param lastBlockReceived the lastBlockReceived to set
	 */
	public void setLastBlockReceived(Integer lastBlockReceived) {
		this.lastBlockReceived = lastBlockReceived;
	}
	
	
}
