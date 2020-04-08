package br.com.compasso.votacao;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.service.VoteService;

public class VoteTest {

	Associate associateLucas;
	Associate associateLarissa;
	Associate associateMarcos;
	Associate associateCarol;
	Associate associateDougras;

	Topic topic1;
	Topic topic2;
	Topic topic3;
	Topic topic4;
	
	@Autowired
	private VoteService service;

	public void main() {
		createAssociates();
		createTopics();

		Session session = new Session(topic1, 1);

	
	}

	private void createTopics() {
		topic1 = new Topic("agua", "mais agua");
		topic2 = new Topic("agua", "mais agua");
		topic3 = new Topic("agua", "mais agua");
		topic4 = new Topic("agua", "mais agua");

	}

	private void createAssociates() {
		associateLucas = new Associate(Long.getLong("1"), "Lucas");
		associateLarissa = new Associate(Long.getLong("2"), "Larissa");
		associateMarcos = new Associate(Long.getLong("3"), "Marcos");
		associateCarol = new Associate(Long.getLong("4"), "Carol");
		associateDougras = new Associate(Long.getLong("5"), "Dougras");
	}

}
