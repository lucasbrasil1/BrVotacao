package br.com.compasso.votacao.controller.form;

import com.sun.istack.NotNull;

public class SessionForm {

	@NotNull
	private Long idTopic;
	private Integer minutes = 1;

	public Long getIdTopic() {
		return idTopic;
	}

	public Integer getTimeInMinutes() {
		return minutes;
	}
}
