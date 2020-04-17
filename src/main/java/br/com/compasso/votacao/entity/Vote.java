package br.com.compasso.votacao.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.VoteEnum;

@Entity
public class Vote {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Associate associate;
	private Long sessionId;
	@Enumerated(EnumType.STRING)
	private VoteEnum vote;

	public Vote() {}
	
	public Vote(Associate associate, Long sessionId, VoteEnum vote) {
		this.associate = associate;
		this.sessionId = sessionId;
		this.vote = vote;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public void setVote(VoteEnum vote) {
		this.vote = vote;
	}

	public VoteEnum getVote() {
		return vote;
	}

	public void setVoto(VoteEnum vote) {
		this.vote = vote;
	}

	public Long getIdAssociate(Vote vote) {
		return vote.getAssociate().getId();
	}

}
