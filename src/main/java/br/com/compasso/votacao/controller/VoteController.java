package br.com.compasso.votacao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.VoteDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.service.VoteBusiness;

@RestController
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteBusiness voteBusiness;
	
	@PostMapping
	@Transactional
	private ResponseEntity<VoteDTO> vote(@RequestBody @Valid VoteForm form, UriComponentsBuilder uriBuilder) {
		VoteDTO voteDTO = voteBusiness.sendVote(form);
		
		URI uri = uriBuilder.path("/session/{$id}").buildAndExpand(voteDTO).toUri();
		return ResponseEntity.created(uri).body(voteDTO);
	}
	
	
}
