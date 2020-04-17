package br.com.compasso.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.votacao.controller.dto.SessionDTO;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@GetMapping
	public Page<SessionDTO> lista(@RequestParam(required = false) String topicTitle,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
	
		if(topicTitle == null) {
			Page<Session> sessions = sessionService.findAll(pageable);
			return SessionDTO.convert(sessions);
		} else {
			Page<Session> sessions = sessionService.findByTopicTitleContains(topicTitle, pageable);
			return SessionDTO.convert(sessions);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SessionDTO> detail(@PathVariable Long id) {
		Session session = sessionService.getOne(id);
		if (!session.equals(null)) {
			return ResponseEntity.ok(new SessionDTO(sessionService.getOne(id)));
		}

		return ResponseEntity.notFound().build();
	}

}
