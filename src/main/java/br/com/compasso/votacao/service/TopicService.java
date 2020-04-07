package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public Topic getOne(Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent())
			return topic.get();
		throw new NullPointerException("Id da pauta não encontrada");
	}

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public void save(Topic topic) {
		topicRepository.save(topic);
	}

	public List<Vote> findVotesByScheduleId(Long id) {
		return getOne(id).getVotes();
	}

	public boolean checkForAssociateVote(Vote vote) {
		List<Vote> votes = vote.getTopic().getVotes();
		for(Vote v : votes) {
			if(v.getAssociate().equals(vote.getAssociate())) {
				throw new IllegalArgumentException("Associado "+ vote.getAssociate().getName() +" já votou!");
			}
		}
		return true;
	}
	
	public String getTopicResults(Topic topic) {
		String message = "A pauta: "+topic.getTitle()+", foi ";
		if(topicAproved(topic))
			message += "aprovada";
		else 
			message += "negada";
		return message;
	}

	private boolean topicAproved(Topic topic) {
		return topic.getVotesYes() > topic.getVotesNo();
	}

	
}
