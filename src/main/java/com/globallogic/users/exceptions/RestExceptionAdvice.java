package com.globallogic.users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle to catch an exception and map them to an object with error message
 * user message and system message.
 *
 *
 */
@ControllerAdvice
public class RestExceptionAdvice {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> response = new HashMap<>();
		if (ex.getBindingResult().hasErrors()) {
			List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(err -> {
				return "El campo  : '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Map<String, ErrorResponse>> userNotFoundException(UserNotFoundException ex) {
		Map<String, ErrorResponse> response = new HashMap<>();
		ErrorResponse errorResponse = new ErrorResponse();
		String className = ex.getStackTrace()[0].getClassName();
		int line = ex.getStackTrace()[0].getLineNumber();
		logger.error("Exception: " + ex.getUserMessage() + " - Class: " + className + " - line: " + line);
		errorResponse.setCodigo(ex.getCode());
		errorResponse.setDetail(ex.getUserMessage());
		errorResponse.setTimestamp(ex.getTimestamp());
		response.put("error", errorResponse);
		return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExist.class)
	@ResponseBody
	public ResponseEntity<Map<String, ErrorResponse>> userAlreadyExist(UserAlreadyExist ex) {
		Map<String, ErrorResponse> response = new HashMap<>();
		ErrorResponse errorResponse = new ErrorResponse();
		String className = ex.getStackTrace()[0].getClassName();
		int line = ex.getStackTrace()[0].getLineNumber();
		logger.error("Exception: " + ex.getUserMessage() + " - Class: " + className + " - line: " + line);
		errorResponse.setCodigo(ex.getCode());
		errorResponse.setDetail(ex.getUserMessage());
		errorResponse.setTimestamp(ex.getTimestamp());
		response.put("error", errorResponse);
		return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	// TODO Crear la excepcion NumberFormatException
	public ResponseEntity<Map<String, ErrorResponse>> numberFormatException(NumberFormatException ex) {
		Map<String, ErrorResponse> response = new HashMap<>();
		ErrorResponse errorResponse = new ErrorResponse();
		String className = ex.getStackTrace()[0].getClassName();
		int line = ex.getStackTrace()[0].getLineNumber();
		logger.error("Exception: " + ex.getMessage() + " - Class: " + className + " - line: " + line);
		errorResponse.setCodigo(500);
		errorResponse.setDetail("numero incorrecto");
		errorResponse.setTimestamp(LocalDateTime.now());
		response.put("error", errorResponse);
		return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	// TODO Crear la excepcion DataIntegrityViolationException
	public ResponseEntity<Map<String, ErrorResponse>> dataIntegrityViolationException(
			DataIntegrityViolationException ex) {
		Map<String, ErrorResponse> response = new HashMap<>();
		ErrorResponse errorResponse = new ErrorResponse();
		String className = ex.getStackTrace()[0].getClassName();
		int line = ex.getStackTrace()[0].getLineNumber();
		logger.error("Exception: " + ex.getMessage() + " - Class: " + className + " - line: " + line);
		errorResponse.setCodigo(500);
		errorResponse.setDetail("Este usuario ya existe");
		errorResponse.setTimestamp(LocalDateTime.now());
		response.put("error", errorResponse);
		return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.CONFLICT);
	}
}
