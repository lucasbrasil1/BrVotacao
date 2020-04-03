package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;

import br.com.compasso.votacao.entity.Session;

public class DetailSessionDTO {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime begining;
	private LocalDateTime ending;
	private String status;

	public DetailSessionDTO(Session session) {
		this.id = session.getId();
		this.title = session.getSchedule().getTitle();
		this.description = session.getSchedule().getDescription();
		this.begining = session.getBegining();
		this.ending = session.getEnding();
		this.status = session.getStatus().name();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getBegining() {
		return begining;
	}

	public LocalDateTime getEnding() {
		return ending;
	}

	public String getStatus() {
		return status;
	}
}
