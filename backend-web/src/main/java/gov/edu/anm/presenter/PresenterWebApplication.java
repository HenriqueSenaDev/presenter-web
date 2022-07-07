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
			userService.saveUser(new AppUser(null, "master", "masteranmadmin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "daniel", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "kim", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "valma", "admin", new HashSet<>()));
			userService.saveUser(new AppUser(null, "joyce", "student", new HashSet<>()));
			userService.saveUser(new AppUser(null, "silvio", "student", new HashSet<>()));
			userService.saveUser(new AppUser(null, "antonio", "student", new HashSet<>()));
			userService.saveUser(new AppUser(null, "josé", "student", new HashSet<>()));

			// AppRoles to AppUsers
			userService.addRoleToUser("master", "ROLE_ADMIN");
			userService.addRoleToUser("daniel", "ROLE_ADMIN");
			userService.addRoleToUser("kim", "ROLE_ADMIN");
			userService.addRoleToUser("valma", "ROLE_ADMIN");
			userService.addRoleToUser("joyce", "ROLE_STUDENT");
			userService.addRoleToUser("silvio", "ROLE_STUDENT");
			userService.addRoleToUser("antonio", "ROLE_STUDENT");
			userService.addRoleToUser("josé", "ROLE_STUDENT");

			// Events
			eventService.saveEvent(new Event(null, "Meio do Ano", 3344, 565656,
					"Event na EEEP Alfredo Nunes de Melo no dia 27 de junho de 2022.", new HashSet<>()));

			// Teams
			teamService.saveTeam(new Team("Procationtes", "Semáforo", "2º INF", false));
			teamService.saveTeam(new Team("Liro Joseph", "Diassis Poupas", "2º INF", false));

			// Participations
			// Jurors
			eventService.addJurorParticipation(3344, 565656, 1L);
			eventService.addJurorParticipation(3344, 565656, 3L);
			eventService.addJurorParticipation(3344, 565656, 2L);

			// Members
			eventService.addMemberParticipation(3344, 5L, 1L);
			eventService.addMemberParticipation(3344, 6L, 2L);
			eventService.addMemberParticipation(3344, 7L, 2L);
			eventService.addMemberParticipation(3344, 8L, 2L);

			// Teams Avaliations
			teamService.addAvaliation(2L, 1L, 10.0);
		};
	}

}
