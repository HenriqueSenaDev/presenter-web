package gov.edu.anm.presenter;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gov.edu.anm.presenter.entities.AppRole;
import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.services.EventService;
import gov.edu.anm.presenter.services.TeamService;
import gov.edu.anm.presenter.services.UserService;

@SpringBootApplication
public class PresenterWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresenterWebApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, EventService eventService, TeamService teamService) {
		return args -> {
//			userService.saveUser(new AppUser(null, "master", "masteranmadmin", new HashSet<>()));
//			userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
//			userService.saveRole(new AppRole(null, "ROLE_STUDENT"));
//			userService.addRoleToUser("master", "ROLE_ADMIN");
//			
//			eventService.saveEventRole(new EventRole(null, "JUROR"));
//			eventService.saveEventRole(new EventRole(null, "MEMBER"));
//			eventService.saveEvent(new Event(null, "Teste do Sistema Presenter", 3344, 565656, "Teste do Presenter Web no 2º Informática", new HashSet<>()));
//			eventService.addJurorParticipation(3344, 565656, 1L);
		};
	}

}
