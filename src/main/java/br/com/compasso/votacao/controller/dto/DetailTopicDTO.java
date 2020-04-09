package br.com.compasso.votacao.controller.dto;

import br.com.compasso.votacao.entity.Topic;

public class DetailTopicDTO {

	private Long id;
	private String title;
	private String description;
	
	public DetailTopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.description = topic.getDescription();
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

}
