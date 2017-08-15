package br.com.hotmart.fileupload.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import br.com.hotmart.fileupload.enums.ResponseStatusEnum;
import br.com.hotmart.fileupload.vo.ResponseVO;


/**
 * Builder para montar resposta do servidor
 * @author brunoeverton
 *
 */
public class ResponseBuilder<E> {
	
	private List<String> errorMessages = new ArrayList<String>();
	private E entityReturn;
	
	/**
	 * Cria instancia do builder
	 */
	public ResponseBuilder() {
		super();
	}
	/**
	 * Cria instancia do builder com a entidade de retorno
	 * @param entityReturn
	 */
	public ResponseBuilder(E entityReturn) {
		this();
		this.entityReturn = entityReturn;
	}
	
	/**
	 * Adiciona mensagem de erro
	 * @param param
	 * @return
	 */
	public ResponseBuilder<E> addErrorMessage(String param) {
		errorMessages.add(param);
		return this;
	}
	
	/**
	 * Verifica se um campo obrigatorio esta preenchido. caso não esteja, retorna mensagem de erro
	 * @param name Nome do campo usado para mensagem de retorno
	 * @param value Valor que será testado
	 * @return
	 */
	public ResponseBuilder<E> validateRequired(String name, Object value) {
		if(value ==null) {
			addRequiredFieldError(name);
		}else if(value instanceof String && StringUtils.isEmpty(value)) {
			addRequiredFieldError(name);
		}else if(value instanceof List<?> && ((List<?>)value).isEmpty()) {
			addRequiredFieldError(name);
		}
		
		return this;
	}
	
	/**
	 * Define a entidade de retorno
	 * @param entityReturn 
	 */
	public ResponseBuilder<E> setEntityReturn(E entityReturn) {
		this.entityReturn = entityReturn;
		return this;
	}
	/**
	 * Indica se ja existe algum erro
	 * @return
	 */
	public boolean hasError() {
		return !errorMessages.isEmpty();
	}
	/**
	 * Build das informaçoes e cria ResponseVO
	 * @return
	 */
	public ResponseVO<E> build(){
		ResponseVO<E> responseVO = new ResponseVO<E>(entityReturn);
		responseVO.setStatus(errorMessages.isEmpty()?ResponseStatusEnum.SUCCESS:ResponseStatusEnum.ERROR);
		responseVO.setMessages(errorMessages);
		
		return responseVO;
	}
	private void addRequiredFieldError(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("O campo ").append(name).append(" é obrigatório.");
		errorMessages.add(sb.toString());
	}	
}
