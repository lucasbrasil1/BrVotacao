package br.com.compasso.votacao;

import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.TopicStatusEnum;
import br.com.compasso.votacao.enumeration.VoteEnum;
import br.com.compasso.votacao.repository.AssociateRepository;
import br.com.compasso.votacao.repository.SessionRepository;
import br.com.compasso.votacao.repository.VoteRepository;
import br.com.compasso.votacao.service.AssociateService;
import br.com.compasso.votacao.service.SessionService;
import br.com.compasso.votacao.service.VoteService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VoteTest {

	private Associate associate1;
	private Associate associate2;
	private Associate associate3;
	private Topic topic;
	private Session session;

	private SessionRepository sessionRepository;
	private VoteRepository voteRepository;
	private AssociateRepository associateRepository;
	private SessionService sessionService;
	private AssociateService associateService;
	private VoteService voteService;

	@Before
	public void createStuff() {
		associate1 = new Associate(1l, "Lucas");
		associate2 = new Associate(2l, "Lari");
		associate3 = new Associate(3l, "Penny");

		topic = new Topic("Title", "Description");

		session = new Session(topic, 1);

		sessionRepository = mock(SessionRepository.class);
		voteRepository = mock(VoteRepository.class);
		sessionService = new SessionService(sessionRepository);
		associateService = new AssociateService(associateRepository);
		voteService = new VoteService(associateService, sessionService, voteRepository);
	}

	@Test
	public void deveResultarEmStatusAprovado() {
		voteService.addVoteToSessionList(new Vote(associate1, session, VoteEnum.SIM));
		voteService.addVoteToSessionList(new Vote(associate2, session, VoteEnum.NAO));
		voteService.addVoteToSessionList(new Vote(associate3, session, VoteEnum.SIM));

		sessionService.endSession(session);

		assertEquals(TopicStatusEnum.APROVADO, session.getStatus());
		assertEquals(3, session.getVotes().size());
	}

	@Test
	public void deveResultarEmStatusNegado() {
		voteService.addVoteToSessionList(new Vote(associate1, session, VoteEnum.SIM));
		voteService.addVoteToSessionList(new Vote(associate2, session, VoteEnum.NAO));
		voteService.addVoteToSessionList(new Vote(associate3, session, VoteEnum.NAO));

		sessionService.endSession(session);

		assertEquals(TopicStatusEnum.NEGADO, session.getStatus());
		assertEquals(3, session.getVotes().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirVotosDoMesmoAssociado() {
		voteService.addVoteToSessionList(new Vote(associate1, session, VoteEnum.SIM));
		voteService.addVoteToSessionList(new Vote(associate1, session, VoteEnum.NAO));
		voteService.addVoteToSessionList(new Vote(associate3, session, VoteEnum.NAO));
		
		sessionService.endSession(session);
		
		assertEquals(2, session.getVotes().size());
	}
	
	@Test
	public void deveResultarEmEmpate() {
		voteService.addVoteToSessionList(new Vote(associate1, session, VoteEnum.SIM));
		voteService.addVoteToSessionList(new Vote(associate2, session, VoteEnum.NAO));
		
		sessionService.endSession(session);
		
		assertEquals(TopicStatusEnum.EMPATADO, session.getStatus());
	}
	
}
