package br.com.compasso.votacao.controller.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.compasso.votacao.controller.security.service.TokenService;
import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.repository.AssociateRepository;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private AssociateRepository associateRepository;
	
	public AuthenticationByTokenFilter(TokenService tokenService, AssociateRepository associateRepository) {
		this.tokenService = tokenService;
		this.associateRepository = associateRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valid = tokenService.isValidToken(token);
		if(valid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idAssociate = tokenService.getIdAssociate(token);
		Associate associate = associateRepository.findById(idAssociate).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(associate, null, associate.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
