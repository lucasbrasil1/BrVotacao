package br.com.compasso.votacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

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

	public static List<TopicDTO> convert(List<Topic> topics) {
		return topics.stream().map(TopicDTO::new).collect(Collectors.toList());
	}

}
