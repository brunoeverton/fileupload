package br.com.hotmart.fileupload.exception;

/**
 * Exceções gerenciar arquivos
 * @author brunoeverton
 *
 */
public class FileUploadException extends Exception {


	private static final long serialVersionUID = 6433961469124031957L;

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(Throwable cause) {
		super(cause);
	}

	
}
