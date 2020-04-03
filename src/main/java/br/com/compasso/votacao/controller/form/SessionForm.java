package br.com.compasso.votacao.controller.form;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.ScheduleRepository;

public class SessionForm {

	private Long idSchedule;
	private Integer minutes;

	public Long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Session converter(ScheduleRepository scheduleRepository) {
		Schedule schedule = scheduleRepository.findById(idSchedule).get();
		return new Session(schedule, minutes);
	}

}
