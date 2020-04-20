package br.com.compasso.votacao.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Topic;

@Service
public class TopicSessionBusiness {

	private SessionService sessionService;
	private TopicService topicService;
			
	public TopicSessionBusiness(SessionService sessionService, TopicService topicService) {
		this.sessionService = sessionService;
		this.topicService = topicService;
	}
	
	@Transactional
	public Topic initialize(Topic topic, int minutes) {
		sessionService.start(topic, minutes);
		return topicService.save(topic);
	}
	
	
}
