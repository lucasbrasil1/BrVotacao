package br.com.compasso.votacao.config.validation;

public class SimpleFormErrorDTO {

	private String message;
	
	public SimpleFormErrorDTO(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
