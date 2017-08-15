package br.com.hotmart.fileupload.vo;

import java.util.List;

import br.com.hotmart.fileupload.enums.ResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dados de retorno
 * @author brunoeverton
 *
 * @param <E>
 */
public class ResponseVO<E> {
	
	@ApiModelProperty(notes = "Entidade de retorno")
	private E response;
	
	@ApiModelProperty(notes = "Lista de mensagens")
	private List<String> messages;
	
	@ApiModelProperty(notes = "Status do retorno")
	private ResponseStatusEnum status;
	
	/**
	 * Cria instancia da entidade com response
	 * @param response
	 */
	public ResponseVO(E response) {
		super();
		this.response = response;
	}

	/**
	 * @return the response
	 */
	public E getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(E response) {
		this.response = response;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the status
	 */
	public ResponseStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseStatusEnum status) {
		this.status = status;
	}
	
	
}
