package com.contact.management.controlleradvice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.contact.management.response.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> studentValidationhandler(MethodArgumentNotValidException methodArgumentNotValidException){
		ApiResponse apiResponse = new ApiResponse();
		Map<String, String> map = new LinkedHashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
		apiResponse.setObject(map);
		apiResponse.setMessage("Mandatory fields should not be empty");
		apiResponse.setResponseCode(5000);
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
