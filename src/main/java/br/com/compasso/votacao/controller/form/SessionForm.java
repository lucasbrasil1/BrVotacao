package br.com.compasso.votacao.controller.form;

public class SessionForm {

	private Long idSchedule;
	private Integer timeInMinutes = 1;

	public Long getIdSchedule() {
		return idSchedule;
	}

	public Integer getTimeInMinutes() {
		return timeInMinutes;
	}
}
