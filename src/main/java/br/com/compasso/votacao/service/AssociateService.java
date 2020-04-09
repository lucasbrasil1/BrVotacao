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

	public Associate getOne(Long idAssociate) {
		Optional<Associate> optional = associateRepository.findById(idAssociate);
		if(optional.isEmpty())
			throw new IllegalArgumentException("Id do associado "+ idAssociate +" não foi encontrado!");
		return optional.get();
	}
	
	
}
