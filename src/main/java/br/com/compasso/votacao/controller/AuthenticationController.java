package br.com.compasso.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.votacao.controller.dto.TokenDTO;
import br.com.compasso.votacao.controller.form.LoginForm;
import br.com.compasso.votacao.controller.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.convert();

		try {
			Authentication authenticate = authManager.authenticate(loginData);
			
			String token = tokenService.generateToken(authenticate);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
