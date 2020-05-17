package br.com.starwarschallenge.exception;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class PlanetaExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(final Exception ex, final WebRequest request) {

		final ExceptionResponse exceptionResponse = new ExceptionResponse(//
				new Date(), //
				ex.getMessage(), //
				request.getDescription(false), //
				ExceptionUtils.getStackTrace(ex));

		if (ex instanceof BusinessException) {
			return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		} else if (ex instanceof PlanetaNotFoundException) {
			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}