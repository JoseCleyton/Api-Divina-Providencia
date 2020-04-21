package com.br.apiDivinaProvidencia.responses;

import java.util.ArrayList;
import java.util.List;

public class Response <T>{

	private T data;
	private List<String> errors;
	private String message;

	public Response(String message) {
		this.message = message;
	}
	public Response() {
		this.errors = new ArrayList<>();
	}
	public Response(T data) {
		this.data = data;
	}
	public Response(List<String> errors) {
		this.errors = errors;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(String error) {
		this.errors.add(error);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
