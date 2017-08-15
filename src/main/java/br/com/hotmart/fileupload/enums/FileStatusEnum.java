package br.com.hotmart.fileupload.enums;

/**
 * Status de upload de arquivo
 * @author brunoeverton
 *
 */
public enum FileStatusEnum {
	EM_ANDAMENTO("Em andamento"),
	FALHA("falha"),
	CONCLUIDO("Concluído");
	
	private String description;

	private FileStatusEnum(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	
}
