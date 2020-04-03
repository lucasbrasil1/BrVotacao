package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.ScheduleDTO;
import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@GetMapping
	public List<ScheduleDTO> lista(){
		List<Schedule> schedules = scheduleRepository.findAll();
		return ScheduleDTO.convert(schedules);
	}
	
	@PostMapping
	public ResponseEntity<ScheduleDTO> cadastrar(@RequestBody ScheduleForm form, UriComponentsBuilder uriBuilder) {
		Schedule schedule = form.convert();
		scheduleRepository.save(schedule);
		
		URI uri = uriBuilder.path("/pautas/{$id}").buildAndExpand(schedule.getId()).toUri();
		return ResponseEntity.created(uri).body(new ScheduleDTO(schedule));
	}
	
}
