package br.com.compasso.votacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TopicService topicService;

	public Optional<Session> getOne(Long idSession) {
		return sessionRepository.findById(idSession);
	}

	public List<Session> getAll() {
		return sessionRepository.findAll();
	}

	public Session convert(SessionForm form, TopicService topicService) {
		Topic topic = topicService.getOne(form.getIdTopic()).get();
		Integer time = form.getTimeInMinutes();
		return new Session(topic, time);
	}

	public void save(Session session) {
		sessionRepository.save(session);
	}

	public void timeChecker(Session session) {
		if (hasEnded(session)) {
			throw new IllegalStateException("Sessão já expirou!");
		}
	}

	public boolean hasEnded(Session session) {
		return LocalDateTime.now().isAfter(session.getEnding());
	}

	public void checkForEndedSessionsCron() {
		getAll().forEach(session -> {
			if (hasEnded(session))
				endSession(session);
		});
	}

	public void endSession(Session session) {
		topicService.getTopicResultsBySession(session);
	}

	public void send(Session session) {
		if (!topicAlreadyStarted(session.getTopic().getId())) {
			save(session);
		}
	}

	public boolean topicAlreadyStarted(Long id) {
		Optional<Session> findByTopic = sessionRepository.findByTopic_Id(id);
		if (findByTopic.isPresent())
			return true;
		return false;
	}

}
