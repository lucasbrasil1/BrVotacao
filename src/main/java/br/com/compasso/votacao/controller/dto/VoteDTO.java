package br.com.compasso.votacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Vote;

public class VoteDTO {

	private Long idAssociate;
	private String vote;

	public Long getIdAssociate() {
		return idAssociate;
	}

	public String getVote() {
		return vote;
	}

	public VoteDTO(Vote vote) {
		this.idAssociate = vote.getIdAssociate(vote);
		this.vote = vote.getVote().toString();
	}

	public static List<VoteDTO> convert(List<Vote> votes) {
		return votes.stream().map(VoteDTO::new).collect(Collectors.toList());
	}

}
