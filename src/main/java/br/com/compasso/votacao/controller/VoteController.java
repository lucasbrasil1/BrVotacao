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

import br.com.compasso.votacao.controller.dto.DetailTopicDTO;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	@PostMapping
	@Transactional
	private ResponseEntity<DetailTopicDTO> vote(@RequestBody VoteForm form, UriComponentsBuilder uriBuilder) {
		Vote vote = voteService.sendVote(form);
		
		URI uri = uriBuilder.path("/vote/{$id}").buildAndExpand(vote.getTopicId()).toUri();
		return ResponseEntity.created(uri).body(new DetailTopicDTO(vote.getTopic()));
	}
	
	
}
