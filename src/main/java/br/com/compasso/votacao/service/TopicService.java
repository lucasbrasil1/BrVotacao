package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.TopicStatusEnum;
import br.com.compasso.votacao.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public Optional<Topic> getOne(Long id) {
		return topicRepository.findById(id);
	}

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public void save(Topic topic) {
		topicRepository.save(topic);
	}

	public List<Vote> findVotesByScheduleId(Long id) {
		return getOne(id).get().getVotes();
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
	
	public Topic getTopicResults(Topic topic) {
		if(topicAproved(topic))
			topic.setStatus(TopicStatusEnum.APROVADO);
		else 
			topic.setStatus(TopicStatusEnum.NEGADO);
		return topic;
	}

	private boolean topicAproved(Topic topic) {
		return topic.getVotesYes() > topic.getVotesNo();
	}

	public void getTopicResultsBySession(Session session) {
		save(getTopicResults(session.getTopic()));
	}
}
