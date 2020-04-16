package br.com.compasso.votacao.controller.dto;

public class TokenDTO {

	private String type;
	private String token;

	public TokenDTO(String token, String string) {
		this.token = token;
		this.type = string;
	}

	public String getString() {
		return type;
	}

	public String getToken() {
		return token;
	}

}
