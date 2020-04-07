package br.com.compasso.votacao.controller.form;

import br.com.compasso.votacao.entity.Topic;

public class TopicForm {

	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Topic convert() {
		return new Topic(title, description);
	}

}
