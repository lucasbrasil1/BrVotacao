package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.SessionStatusEnum;

@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime begining;
	private LocalDateTime ending;
	@OneToOne
	private Schedule schedule;
	@Enumerated(EnumType.STRING)
	private SessionStatusEnum status = SessionStatusEnum.AGUARDANDO;
	private Integer minutes;
	@OneToMany
	private List<Vote> votes;

	public Session() {
	}

	public Session(Schedule schedule, Integer minutes) {
		this.schedule = schedule;
		this.minutes = minutes;
	}

	public SessionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SessionStatusEnum status) {
		this.status = status;
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

}
