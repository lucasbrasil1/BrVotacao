package br.com.compasso.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.dto.VoteDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	private AssociateService associateService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private VoteRepository voteRepository;
	
	public Vote sendVote(VoteForm form) {
		Vote vote = convertFromForm(form);
		checkIfAssociateAlreadyVoted(vote);
		checkIfSessionDidNotExpired(vote);
		addVoteToVotesListOnTopic(vote);
		saveVote(vote);
		return vote;
	}

	private VoteDTO saveVote(Vote vote) {
		return new VoteDTO(voteRepository.save(vote));
	}

	private void addVoteToVotesListOnTopic(Vote vote) {
		vote.getTopic().addVoteToList(vote);
	}

	private Vote convertFromForm(VoteForm form) {
		Vote vote = form.convert(associateService, sessionService);
		return vote;
	}

	private void checkIfSessionDidNotExpired(Vote vote) {
		sessionService.timeChecker(vote.getSession());
	}

	private void checkIfAssociateAlreadyVoted(Vote vote) {
		topicService.checkForAssociateVote(vote);
	}

	public List<Vote> findAllVotes(Long id) {
		return topicService.findVotesByScheduleId(id);
	}
	
}
