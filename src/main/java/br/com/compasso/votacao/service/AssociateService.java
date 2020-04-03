package br.com.compasso.votacao.service;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.repository.AssociateRepository;

@Service
public class AssociateService {

	private AssociateRepository associateRepository;

	public Associate get(Long idAssociate) {
		return associateRepository.getOne(idAssociate);
	}
	
	
}
