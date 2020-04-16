package br.com.compasso.votacao.controller.dto;

import org.springframework.data.domain.Page;

import br.com.compasso.votacao.entity.Topic;

public class TopicDTO {

	private Long id;
	private String title;
	private String description;
	
	public TopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.description = topic.getDescription();
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

	public static Page<TopicDTO> convert(Page<Topic> topics) {
		return topics.map(TopicDTO::new);
	}

}
