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

	public Associate get(Long idAssociate) {
		Optional<Associate> associate = associateRepository.findById(idAssociate);
		if(associate.isPresent())
			return associate.get();
		throw new NullPointerException("Associate not found!");
	}
	
	
}
