package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;

import br.com.compasso.votacao.entity.Session;

public class DetailSessionDTO {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime begining;
	private LocalDateTime ending;

	public DetailSessionDTO(Session session) {
		this.id = session.getId();
		this.title = session.getTopic().getTitle();
		this.description = session.getTopic().getDescription();
		this.begining = session.getBegining();
		this.ending = session.getEnding();
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

}
