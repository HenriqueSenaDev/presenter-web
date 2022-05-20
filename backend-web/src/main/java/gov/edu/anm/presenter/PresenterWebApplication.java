package gov.edu.anm.presenter;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Role;
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
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_STUDENT"));

			userService.saveUser(new AppUser(null, "luiz", "admin", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "joyce", "student", new ArrayList<>()));

			userService.addRoleToUser("luiz", "ROLE_ADMIN");
			userService.addRoleToUser("joyce", "ROLE_STUDENT");
		};
	}

}
