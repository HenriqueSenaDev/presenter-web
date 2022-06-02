package gov.edu.anm.presenter;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.entities.AppRole;
import gov.edu.anm.presenter.services.EventService;
import gov.edu.anm.presenter.services.TeamService;
import gov.edu.anm.presenter.services.UserService;

@SpringBootApplication
public class PresenterWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresenterWebApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, EventService eventService, TeamService teamService) {
		return args -> {
			// AppRoles
			userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
			userService.saveRole(new AppRole(null, "ROLE_STUDENT"));
			// EventRoles
			eventService.saveEventRole(new EventRole(null, "JUROR"));
			eventService.saveEventRole(new EventRole(null, "MEMBER"));

			// AppUsers
			userService.saveUser(new AppUser(null, "luiz", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "joyce", "student", new HashSet<>()));

			// Event
			eventService.saveEvent(new Event(null, "Meio do Ano", 3344, 565656, new HashSet<>(), new HashSet<>()));
			eventService.saveEvent(new Event(null, "Natal", 3377, 565653, new HashSet<>(), new HashSet<>()));

			// AppRoles to AppUsers
			userService.addRoleToUser("luiz", "ROLE_ADMIN");
			userService.addRoleToUser("joyce", "ROLE_STUDENT");

			// Teams
			teamService.saveTeam(new Team(null, "Procariontes", "Semáforo", "2º INF", 30.0, 3, null, false, null));
			teamService.saveTeam(new Team(null, "Elemento X", "Diassis Poupas", "2º INF", 10.0, 2, null, false, null));

			// Event participations
			eventService.addParticipation(1L, 1L, 1L, 1L);
			eventService.addParticipation(2L, 1L, 2L, 2L);

		};
	}

}
