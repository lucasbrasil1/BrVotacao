package br.com.compasso.votacao.service;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.dto.VoteDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.VoteRepository;

@Service
public class VoteService {

//	@Autowired
//	private AssociateService associateService;
//
//	@Autowired
//	private SessionService sessionService;
//
//	@Autowired
//	private VoteRepository voteRepository;
	
	private AssociateService associateService;
	private SessionService sessionService;
	private VoteRepository voteRepository;
	
	public VoteService(AssociateService associateService, SessionService sessionService, VoteRepository voteRepository) {
		this.associateService = associateService;
		this.sessionService = sessionService;
		this.voteRepository = voteRepository;
	}
	
	public Vote sendVote(VoteForm form) {
		Vote vote = convertFromForm(form);

		sessionService.associateAndSessionVerifier(vote, vote.getSession());

		addVoteToSessionList(vote);
		saveVote(vote);
		return vote;
	}

	private VoteDTO saveVote(Vote vote) {
		return new VoteDTO(voteRepository.save(vote));
	}

	public void addVoteToSessionList(Vote vote) {
		sessionService.addToList(vote);
	}

	private Vote convertFromForm(VoteForm form) {
		Vote vote = form.convert(associateService, sessionService);
		return vote;
	}

}
