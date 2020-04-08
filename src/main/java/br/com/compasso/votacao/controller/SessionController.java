package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailTopicDTO;
import br.com.compasso.votacao.controller.dto.SessionDTO;
import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.service.TopicService;
import br.com.compasso.votacao.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private TopicService topicService;

	@GetMapping
	public List<SessionDTO> lista(){
		List<Session> sessions = sessionService.getAll();
		return SessionDTO.convert(sessions);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SessionDTO> detail(@PathVariable Long id) {
		Optional<Session> session = sessionService.getOne(id);
		if (session.isPresent()) {
			return ResponseEntity.ok(new SessionDTO(session.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<SessionDTO> cadastraSessao(@RequestBody @Valid SessionForm form, UriComponentsBuilder uriBuilder){
		Session session = sessionService.convert(form, topicService);
		sessionService.send(session);
		
		URI uri = uriBuilder.path("/session/{$id}").buildAndExpand(session.getId()).toUri();
		return ResponseEntity.created(uri).body(new SessionDTO(session));
	}
	
}
