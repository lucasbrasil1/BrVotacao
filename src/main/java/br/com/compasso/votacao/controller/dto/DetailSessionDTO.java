package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Vote;

public class DetailSessionDTO {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime begining;
	private LocalDateTime ending;
	private String status;
	private Integer secondsLeft;
	private List<Vote> votesList;

	public DetailSessionDTO(Session session) {
		this.id = session.getId();
		this.title = session.getSchedule().getTitle();
		this.description = session.getSchedule().getDescription();
		this.begining = session.getBegining();
		this.ending = session.getEnding();
		this.status = session.getStatus().name();
		this.votesList = session.getVotes();
		this.secondsLeft = setSecondsLeft(session);
	}

	private Integer setSecondsLeft(Session session) {
		// IMPLEMENTAR
		return 60;
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

	public List<Vote> getVotesList() {
		return votesList;
	}

	public Integer getSecondsLeft() {
		return secondsLeft;
	}

}
