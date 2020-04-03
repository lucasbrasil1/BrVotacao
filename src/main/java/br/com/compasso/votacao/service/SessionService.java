package br.com.compasso.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	public void addToListVote(Vote vote) {
		vote.getSession().addVoteToList(vote);
	}

	public Session get(Long idSession) {
		return sessionRepository.getOne(idSession);
	}

	public List<Session> getAll() {
		return sessionRepository.findAll();
	}

	public Session convert(SessionForm form, ScheduleService scheduleService) {
		Schedule schedule = scheduleService.get(form.getIdSchedule());
		Integer time = form.getTimeInMinutes();
		return new Session(schedule, time);
	}

	public void save(Session session) {
		sessionRepository.save(session);
	}
	
}
