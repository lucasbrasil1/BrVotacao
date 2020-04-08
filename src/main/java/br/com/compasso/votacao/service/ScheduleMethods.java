package br.com.compasso.votacao.service;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleMethods {

	@Autowired
	private SessionService sessionService;
	
	@Scheduled(fixedRate = 10000)
	private void schedule() {
		sessionService.checkForEndedSessionsCron();
	}
	
	private void tryTimer() {
		Timer timer = new Timer();
//		timer.
	}
	
}
