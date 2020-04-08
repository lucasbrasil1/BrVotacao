package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime begining = LocalDateTime.now();
	private LocalDateTime ending;
	@OneToOne
	private Topic topic;
	private Integer minutes = 1;

	public Session() {}

	public Session(Topic topic, Integer minutes) {
		this.topic = topic;
		this.minutes = minutes;
		this.ending = begining.plusMinutes(minutes);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getBegining() {
		return begining;
	}

	public void setBegining(LocalDateTime begining) {
		this.begining = begining;
	}

	public LocalDateTime getEnding() {
		return ending;
	}

	public void setEnding(LocalDateTime ending) {
		this.ending = ending;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setSchedule(Topic topic) {
		this.topic = topic;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	

}