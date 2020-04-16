package br.com.compasso.votacao.builder;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.VoteEnum;

public class SessionBuilder {

	private Session session;

	public SessionBuilder about(String title, String description) {
		this.session = new Session(new Topic(title, description), 1);
		return this;
	}

	public SessionBuilder vote(Associate associate, VoteEnum value) {
		this.session.addVoteToList(new Vote(associate, this.session, value));
		return this;
	}

	public Session build() {
		return this.session;
	}
	
	

}
