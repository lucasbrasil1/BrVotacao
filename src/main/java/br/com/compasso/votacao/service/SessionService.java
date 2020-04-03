package br.com.compasso.votacao.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.SessionStatusEnum;

@Service
public class SessionService {

	public void start(Session session) {
		session.setBegining(LocalDateTime.now());
		LocalDateTime end = session.getBegining().plusMinutes(session.getMinutes());
		session.setEnding(end);
		session.setStatus(SessionStatusEnum.EM_VOTACAO);
	}
	
}
