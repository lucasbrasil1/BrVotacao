package br.com.compasso.votacao;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.repository.AssociateRepository;
import br.com.compasso.votacao.service.AssociateService;

@SpringBootTest
public class AssociateTest {

	@Test
	public void testaSeAssociadoFoiBuscadoCorretamente() {
		
		AssociateRepository associateRepository = mock(AssociateRepository.class);
		Associate associate1 = new Associate(1l, "Lucas");
		AssociateService associateService = new AssociateService(associateRepository);
		
		associateService.getById(1l);
		
	}
	
	
	
	
}
