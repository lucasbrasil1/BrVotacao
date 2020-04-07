package br.com.compasso.votacao.controller.dto;

import br.com.compasso.votacao.entity.Topic;

public class DetailTopicDTO {

	private Long id;
	private String title;
	private String description;
	private String status;
	
	public DetailTopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.description = topic.getDescription();
		this.status = topic.getStatusString();
	}

	public String getStatus() {
		return status;
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
