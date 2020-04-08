package br.com.compasso.votacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.repository.AssociateRepository;

@Component
public class AssociateService {

	@Autowired
	private AssociateRepository associateRepository;

	public Optional<Associate> get(Long idAssociate) {
		return associateRepository.findById(idAssociate);
	}
	
	public Associate getOne(Long idAssociate) {
		return associateRepository.getOne(idAssociate);
	}
	
	
}
