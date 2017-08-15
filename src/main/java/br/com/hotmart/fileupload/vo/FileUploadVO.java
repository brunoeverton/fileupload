package br.com.hotmart.fileupload.vo;

import br.com.hotmart.fileupload.entity.FileInfo;
import br.com.hotmart.fileupload.enums.FileStatusEnum;

/**
 * Dados do arquivo
 * @author brunoeverton
 *
 */
public class FileUploadVO {
	
	private Long id;	
	private String userEmail;
	private String fileName;
	private String status;
	private Integer blocksAmount;
	private Long uploadTime;
	private String downloadLink;
	
	
	/**
	 * Cria instancia
	 */
	public FileUploadVO() {
		super();
	}
	
	/**
	 * Cria instancia a partir da entidade
	 * @param fileInfo
	 */
	public FileUploadVO(FileInfo fileInfo) {
		this.id = fileInfo.getId();
		this.fileName = fileInfo.getName();
		this.userEmail = fileInfo.getUser().getEmail();
		this.status = fileInfo.getStatus().getDescription();
		this.blocksAmount = fileInfo.getBlocksAmount();
		if(fileInfo.getUploadFinish()!=null) {
			this.uploadTime = fileInfo.getUploadFinish() - fileInfo.getUploadStart();
		}else {
			this.uploadTime = 0L;
		}
		if(FileStatusEnum.CONCLUIDO.equals(fileInfo.getStatus())) {
			this.downloadLink = "fileupload/download/" + this.id;
		}
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the uploadTime
	 */
	public Long getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
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
	 * @return the downloadLink
	 */
	public String getDownloadLink() {
		return downloadLink;
	}

	/**
	 * @param downloadLink the downloadLink to set
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
}
