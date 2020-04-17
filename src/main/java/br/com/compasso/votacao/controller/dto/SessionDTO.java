package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.TopicStatusEnum;

public class SessionDTO {

	private Long id;
	private Integer minutes;
	private LocalDateTime beginAt;
	private LocalDateTime endAt;
	private String topicTitle;
	private String description;
	private int votesYes;
	private int votesNo;
	private TopicStatusEnum status;

	public SessionDTO(Session session) {
		this.id = session.getId();
		this.minutes = session.getMinutes();
		this.beginAt = session.getBegining();
		this.endAt = session.getEnding();
		this.topicTitle = session.getTopic().getTitle();
		this.description = session.getTopic().getDescription();
		this.votesNo = session.getVotesNo();
		this.votesYes = session.getVotesYes();
		this.status = session.getStatus();
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public int getVotesYes() {
		return votesYes;
	}

	public int getVotesNo() {
		return votesNo;
	}

	public TopicStatusEnum getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public LocalDateTime getBeginAt() {
		return beginAt;
	}

	public String getScheduleTitle() {
		return topicTitle;
	}

	public String getDescription() {
		return description;
	}

	public static Page<SessionDTO> convert(Page<Session> sessions) {
		return sessions.map(SessionDTO::new);
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

}
