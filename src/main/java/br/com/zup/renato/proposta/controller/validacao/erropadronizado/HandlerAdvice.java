package br.com.zup.renato.proposta.controller.validacao.erropadronizado;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class HandlerAdvice {

	@ExceptionHandler(ApiErroException.class)
	public ResponseEntity<ErroPadronizado> handleApiErroException(ApiErroException apiErroException) {
	    Collection<String> mensagens = new ArrayList<>();
	    mensagens.add(apiErroException.getReason());

	    ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
	    return ResponseEntity.status(apiErroException.getHttpStatus()).body(erroPadronizado);
	}
}
