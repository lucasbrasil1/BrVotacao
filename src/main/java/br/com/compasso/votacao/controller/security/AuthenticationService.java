package br.com.compasso.votacao.controller.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.repository.AssociateRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private AssociateRepository associateRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Associate> associate = associateRepository.findByEmail(username);
		if(associate.isPresent())
			return associate.get();
		
		throw new UsernameNotFoundException("Usuário ou senha inválidos.");
	}

}
