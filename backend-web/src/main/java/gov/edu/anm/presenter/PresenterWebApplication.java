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
			userService.saveUser(new AppUser(null, "daniel", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "kim", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "joyce", "student", new HashSet<>()));
			userService.saveUser(new AppUser(null, "silvio", "student", new HashSet<>()));

			// Event
			eventService.saveEvent(new Event(null, "Meio do Ano", 3344, 565656, new HashSet<>(), new HashSet<>()));
			eventService.saveEvent(new Event(null, "Natal", 3377, 565653, new HashSet<>(), new HashSet<>()));

			// AppRoles to AppUsers
			userService.addRoleToUser("luiz", "ROLE_ADMIN");
			userService.addRoleToUser("daniel", "ROLE_ADMIN");
			userService.addRoleToUser("kim", "ROLE_ADMIN");
			userService.addRoleToUser("joyce", "ROLE_STUDENT");
			userService.addRoleToUser("silvio", "ROLE_STUDENT");

			// Teams
			teamService.saveTeam(new Team("Procationtes", "Semáforo", "2º INF", false));
			teamService.saveTeam(new Team("Liro Joseph", "Diassis Poupas", "2º INF", false));

			// Event participations
			eventService.addParticipation(3344, 565656, 1L, 1L);
			eventService.addParticipation(3344, 1, 4L, 2L);
			eventService.addParticipation(3344, 1, 5L, 2L);

			// Teams Avaliations
			// teamService.addAvaliation(1L, 1L, 10.0);
			teamService.addAvaliation(2L, 1L, 10.0);
		};
	}

}
