package br.com.compasso.votacao.controller.form;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.compasso.votacao.entity.Topic;

public class TopicForm {

	@NotNull @Length(min = 5)
	private String title;
	@NotNull @Length(min = 10)
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
