package br.com.compasso.votacao.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.TopicStatusEnum;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	public SessionService(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public Session getOne(Long idSession) {
		Optional<Session> optional = sessionRepository.findById(idSession);
		if(optional.isEmpty())
			throw new IllegalArgumentException("Sessão "+ idSession+ " não encontrada!");
		return optional.get();
	}

	public List<Session> getAll() {
		return sessionRepository.findAll();
	}

	public List<Session> getAllActive() {
		return sessionRepository.findByEndingLessThan(LocalDateTime.now());
	}

	public void save(Session session) {
		sessionRepository.save(session);
	}

	public void throwExcepitonIfSessionHasExpired(Session session) {
		if (hasEnded(session)) {
			throw new IllegalStateException("Sessão já expirou!");
		}
	}

	public boolean hasEnded(Session session) {
		return LocalDateTime.now().isAfter(session.getEnding());
	}

	public void checkForEndedSessionsCron() {
		getAllActive().forEach(session -> {
			if (hasEnded(session))
				endSession(session);
		});
	}

	@Transactional
	public void start(Topic topic, int minutes) {
		Session session = new Session(topic, minutes);
		save(session);
	}	

	public void endSession(Session session) {
		save(getSessionResults(session));
	}

	public boolean checkForAssociateVote(Vote vote, Session session) {
		Collection<Vote> votes = session.getVotes();
		for (Vote v : votes) {
			if (v.getAssociate().getId().equals(vote.getAssociate().getId())) {
				throw new IllegalArgumentException("Associado " + vote.getAssociate().getName() + " já votou!");
			}
		}
		return true;
	}

	public Session getSessionResults(Session session) {
		if (session.getVotesYes() > session.getVotesNo())
			session.setStatus(TopicStatusEnum.APROVADO);
		else if(session.getVotesYes() < session.getVotesNo())
			session.setStatus(TopicStatusEnum.NEGADO);
		else
			session.setStatus(TopicStatusEnum.EMPATADO);
		return session;
	}

	public void associateAndSessionVerifier(Vote vote, Session session) {
		throwExcepitonIfSessionHasExpired(session);
		checkForAssociateVote(vote, session);
	}

	public void addToList(Vote vote) {
		Session session = vote.getSession();
		associateAndSessionVerifier(vote, vote.getSession());
		session.addVoteToList(vote);
		
	}
}
