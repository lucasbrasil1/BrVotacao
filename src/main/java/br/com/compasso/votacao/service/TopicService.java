package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.repository.TopicRepository;

@Service
public class TopicService {

	private TopicRepository topicRepository;
	
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	public Optional<Topic> getOne(Long id) {
		return topicRepository.findById(id);
	}

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}
	
	public Page<Topic> findAllWith(Pageable paginacao) {
		return topicRepository.findAll(paginacao);
	}

	public Page<Topic> findByTitleContains(String topicTitle, Pageable paginacao) {
		return topicRepository.findByTitleContains(topicTitle, paginacao);
	}

	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}
}
