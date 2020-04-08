package br.com.compasso.votacao.config.validation;

public class FormErrorDTO {

	private String field;
	private String message;

	public FormErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
