package br.com.compasso.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<SessionDTO> lista(){
		List<Session> sessions = sessionService.getAll();
		return SessionDTO.convert(sessions);
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
