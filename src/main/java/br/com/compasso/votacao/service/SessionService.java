package br.com.compasso.votacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.enumeration.SessionStatusEnum;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	public Session get(Long idSession) {
		Optional<Session> session = sessionRepository.findById(idSession);
		if(session.isPresent())
			return session.get();
		throw new NullPointerException("Session ID not found!");
	}

	public List<Session> getAll() {
		return sessionRepository.findAll();
	}

	public Session convert(SessionForm form, TopicService topicService) {
		Topic topic = topicService.getOne(form.getIdSchedule());
		Integer time = form.getTimeInMinutes();
		return new Session(topic, time);
	}

	public void save(Session session) {
		sessionRepository.save(session);
	}

	public void timeChecker(Session session) {
		if(currentTimeIsAfterEndTime(session)) {
			throw new IllegalStateException("Sessão já expirou!");
		}
	}
	
	public boolean currentTimeIsAfterEndTime(Session session) {
		return LocalDateTime.now().isAfter(session.getEnding());
	}
	

	public void endSession(Session session) {
		if (currentTimeIsAfterEndTime(session))
			session.setStatus(SessionStatusEnum.FINALIZADA);
		else
			session.setStatus(SessionStatusEnum.EM_VOTACAO);
		sessionRepository.save(session);
	}
}
