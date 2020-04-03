package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailSessionDTO;
import br.com.compasso.votacao.controller.dto.SessionDTO;
import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.ScheduleRepository;
import br.com.compasso.votacao.repository.SessionRepository;
import br.com.compasso.votacao.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private ScheduleRepository pautaRepository;

	@Autowired
	private SessionService sessionService;
	
	@GetMapping
	public List<SessionDTO> lista(){
		List<Session> sessions = sessionRepository.findAll();
		return SessionDTO.convert(sessions);
	}
	
	@GetMapping("/{id}")
	public SessionDTO detail(@PathVariable Long id) {
		Session session = sessionRepository.getOne(id);
		return new SessionDTO(session);
	}
	
	@GetMapping("/{id}/start")
	public DetailSessionDTO startSession(@PathVariable Long id) {
		Session session = sessionRepository.getOne(id);
		sessionService.start(session);
		sessionRepository.save(session);
		return new DetailSessionDTO(session);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<SessionDTO> cadastraSessao(@RequestBody SessionForm form, UriComponentsBuilder uriBuilder){
		Session session = form.converter(pautaRepository);
		sessionRepository.save(session);
		
		URI uri = uriBuilder.path("/session/{$id}").buildAndExpand(session.getId()).toUri();
		return ResponseEntity.created(uri).body(new SessionDTO(session));
	}
	
}
