package br.com.compasso.votacao.controller;
import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailTopicDTO;
import br.com.compasso.votacao.controller.dto.TopicDTO;
import br.com.compasso.votacao.controller.form.TopicForm;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.service.SessionService;
import br.com.compasso.votacao.service.TopicService;
import br.com.compasso.votacao.service.TopicSessionBusiness;


@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	public TopicService topicService;
	
	@Autowired
	public SessionService sessionService;
	
	@Autowired
	public TopicSessionBusiness business;

	@GetMapping
	@Cacheable(value = "topicList")
	public Page<TopicDTO> lista(@RequestParam(required = false) String topicTitle,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		
		if(topicTitle == null) {
			Page<Topic> topics = topicService.findAllWith(pageable);
			return TopicDTO.convert(topics);
		} else {
			Page<Topic> topics = topicService.findByTitleContains(topicTitle, pageable);
			return TopicDTO.convert(topics);
		}
	}

	@GetMapping("/{id}")
	@Cacheable(key = "{id}")
	public ResponseEntity<DetailTopicDTO> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicService.getOne(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailTopicDTO(topic.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "topicList", allEntries = true)
	public ResponseEntity<TopicDTO> cadastrar(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = business.initialize(form.convert(), form.getMinutes());

		URI uri = uriBuilder.path("/topic/{$id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}

}
