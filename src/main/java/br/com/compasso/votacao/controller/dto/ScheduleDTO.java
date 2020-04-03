package br.com.compasso.votacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Schedule;

public class ScheduleDTO {

	private Long id;
	private String title;
	private String description;

	public ScheduleDTO(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.description = schedule.getDescription();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return title;
	}

	public String getDescricao() {
		return description;
	}

	public static List<ScheduleDTO> convert(List<Schedule> schedules) {
		return schedules.stream().map(ScheduleDTO::new).collect(Collectors.toList());
	}

}
