package com.first.starting;

import com.first.starting.run.Location;
import com.first.starting.run.run;
import com.first.starting.run.runRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class StartingApplication {
	private static  final Logger log = LoggerFactory.getLogger(StartingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StartingApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(runRepository runRepo){
		return args -> {
			run r1 = new run(1,"firstRun", LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),5, "OUTDOOR");
			run r2 = new run(2,"MornRun", LocalDateTime.now(),LocalDateTime.now().plus(2, ChronoUnit.HOURS),15, "INDOOR");
			run r3 = new run(3,"AfternoonRun", LocalDateTime.now(),LocalDateTime.now().plus(4, ChronoUnit.HOURS),50, "OUTDOOR");
			run r4 = new run(4,"nightRun", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS),7, "INDOOR");
			runRepo.create(r1);
			runRepo.create(r2);
			runRepo.create(r3);
			runRepo.create(r4);
		};
	}

}
