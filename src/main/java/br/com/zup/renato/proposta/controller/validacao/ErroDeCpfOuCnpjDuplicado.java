package br.com.zup.renato.proposta.controller.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErroDeCpfOuCnpjDuplicado {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ResponseStatusException.class)
	public ErroValidacao handleResponseStatusException(ResponseStatusException responseStatusException) {
		ErroValidacao erro = new ErroValidacao("CpfOuCnpj", responseStatusException.getReason());
		
		return erro;
	}
}
