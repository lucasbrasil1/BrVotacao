package br.com.compasso.votacao.service;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.VoteEnum;

@Service
public class VoteService {
	

	public Vote convert(VoteForm form, AssociateService associateService, SessionService sessionService) {
		return new Vote(
				associateService.get(form.getIdAssociate()),
				sessionService.get(form.getIdSession()),
				VoteEnum.valueOf(form.getVote()));
	}
	
}
