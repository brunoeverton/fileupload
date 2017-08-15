package br.com.hotmart.fileupload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hotmart.fileupload.builder.ResponseBuilder;
import br.com.hotmart.fileupload.vo.ResponseVO;
import springfox.documentation.annotations.ApiIgnore;

@ControllerAdvice
@ApiIgnore
public class MultipartExceptionController {
	
	@Value("${spring.http.multipart.max-file-size}")
	private String maxFileSize;
	
	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	public ResponseVO<Void> handleMultipartException(MultipartException e, RedirectAttributes redirectAttributes) {		
		ResponseBuilder<Void> builder = new ResponseBuilder<Void>();		
		builder.addErrorMessage("Não foi possível fazer upload do arquivo. O tamanho máximo permitido é: " + maxFileSize);
	    return builder.build();
	}

}
