package br.com.compasso.votacao.service;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.dto.VoteDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.VoteRepository;

@Service
public class VoteBusiness {

	private AssociateService associateService;
	private SessionService sessionService;
	private VoteRepository voteRepository;

	public VoteBusiness(AssociateService associateService, SessionService sessionService,
			VoteRepository voteRepository) {
		this.associateService = associateService;
		this.sessionService = sessionService;
		this.voteRepository = voteRepository;
	}

	public VoteDTO sendVote(VoteForm form) {
		Vote vote = form.convert(associateService, sessionService);
		sessionService.verificationRoutine(vote);
		return new VoteDTO(voteRepository.save(vote));
	}

}
