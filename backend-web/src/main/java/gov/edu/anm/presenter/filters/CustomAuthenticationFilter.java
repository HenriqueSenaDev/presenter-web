package gov.edu.anm.presenter.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gov.edu.anm.presenter.services.CustomUserDetails;
import gov.edu.anm.presenter.utils.AuthUtils;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username,
				password);

		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(AuthUtils.getTokenSecret().getBytes());

		String access_token = JWT.create()
				.withSubject(customUserDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
						customUserDetails.getAuthorities().stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.sign(algorithm);

		String refresh_token = JWT.create()
				.withSubject(customUserDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		// response.setHeader("access_token", access_token);
		// response.setHeader("refresh_token", refresh_token);
		Map<String, String> data = new HashMap<>();
		data.put("access_token", access_token);
		data.put("refresh_token", refresh_token);
		data.put("user", customUserDetails.getUsername());
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), data);
	}

}
