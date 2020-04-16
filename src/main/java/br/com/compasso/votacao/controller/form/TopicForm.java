package br.com.compasso.votacao.controller.form;

import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.compasso.votacao.entity.Topic;

public class TopicForm {

	@NotNull @Length(min = 5)
	private String title;
	@NotNull @Length(min = 10)
	private String description;
	@Positive
	private int minutes = 1;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Topic convert() {
		return new Topic(title, description);
	}

	public int getMinutes() {
		return minutes;
	}

}
