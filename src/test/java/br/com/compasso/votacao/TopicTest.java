package br.com.compasso.votacao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.repository.SessionRepository;
import br.com.compasso.votacao.repository.TopicRepository;
import br.com.compasso.votacao.service.SessionService;
import br.com.compasso.votacao.service.TopicService;

@SpringBootTest
public class TopicTest {

	@Test
	public void testaSeTopicoFoiCadastrado() {
		TopicRepository topicRepository = mock(TopicRepository.class);
		SessionRepository sessionRepository = mock(SessionRepository.class);
		SessionService sessionService = new SessionService(sessionRepository);
		TopicService topicService = new TopicService(topicRepository, sessionService);
		Topic topic = new Topic("Titulo", "DescricaoFeliz");
		topicService.initialize(topic, 2);
		
		
		
		
		
	}
	
}
