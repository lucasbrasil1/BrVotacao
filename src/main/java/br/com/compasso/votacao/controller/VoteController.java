package br.com.compasso.votacao.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailSessionDTO;
import br.com.compasso.votacao.controller.dto.SessionDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.AssociateRepository;
import br.com.compasso.votacao.repository.SessionRepository;

@RestController
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetailSessionDTO> vote(@RequestBody VoteForm form, UriComponentsBuilder uriBuilder){
		Vote vote = form.convert(associateRepository, sessionRepository);
		Session session = vote.getSession();
		vote.setSession(session);
		session.getVotes().add(vote);
		sessionRepository.save(session);
		URI uri = uriBuilder.path("/session/{$id}").buildAndExpand(session.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetailSessionDTO(session));
	}
	
}
