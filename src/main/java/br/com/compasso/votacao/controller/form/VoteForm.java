package br.com.compasso.votacao.controller.form;

import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.VoteEnum;
import br.com.compasso.votacao.repository.AssociateRepository;
import br.com.compasso.votacao.repository.SessionRepository;

public class VoteForm {

	private Long idAssociate;
	private Long idSession;
	private String vote;

	public Long getIdAssociate() {
		return idAssociate;
	}

	public void setIdAssociate(Long idAssociate) {
		this.idAssociate = idAssociate;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}


	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public Vote convert(AssociateRepository associateRepository, SessionRepository sessionRepository) {
		return new Vote(associateRepository.getOne(this.idAssociate), sessionRepository.getOne(idSession), VoteEnum.valueOf(this.vote));
	}
}
