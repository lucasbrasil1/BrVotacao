package br.com.compasso.votacao.config.validation;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class,
		NoSuchElementException.class, HttpMessageNotReadableException.class, EntityNotFoundException.class})
	public SimpleFormErrorDTO handle(RuntimeException exception) {
		return new SimpleFormErrorDTO(exception.getMessage());
	}
	
}
