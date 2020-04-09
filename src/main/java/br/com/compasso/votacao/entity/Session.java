package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import br.com.compasso.votacao.enumeration.TopicStatusEnum;
import br.com.compasso.votacao.enumeration.VoteEnum;

@Component
@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime begining = LocalDateTime.now();

	private LocalDateTime ending;

	private Integer minutes = 1;

	@Enumerated(EnumType.STRING)
	private TopicStatusEnum status = TopicStatusEnum.EM_PAUTA;

	private Integer votesYes = 0;

	private Integer votesNo = 0;

	@OneToMany
	private Collection<Vote> votes = new ArrayList<Vote>();

	@OneToOne
	private Topic topic;

	public Session() {
	}

	public Session(Topic topic, Integer minutes) {
		this.topic = topic;
		this.minutes = minutes;
		this.ending = begining.plusMinutes(minutes);
	}

	public Collection<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
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

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public TopicStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TopicStatusEnum status) {
		this.status = status;
	}

	public Integer getVotesYes() {
		return votesYes;
	}

	public void setVotesYes(Integer votesYes) {
		this.votesYes = votesYes;
	}

	public Integer getVotesNo() {
		return votesNo;
	}

	public void setVotesNo(Integer votesNo) {
		this.votesNo = votesNo;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public void addVoteToList(Vote vote) {
		assignVoteValue(vote);
		votes.add(vote);
	}

	private void assignVoteValue(Vote vote) {
		if (voteToYes(vote))
			addVoteToYes();
		else
			addVoteToNo();
	}

	private void addVoteToNo() {
		this.votesNo++;
	}

	private void addVoteToYes() {
		this.votesYes++;
	}

	public boolean voteToYes(Vote vote) {
		return vote.getVote().equals(VoteEnum.SIM);
	}
}