package com.gestionEvent.handlerService.HandlerService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestionEvent.handlerService.HandlerService.entities.AccountCredentials;
import com.gestionEvent.handlerService.HandlerService.service.ClientService;
import com.gestionEvent.handlerService.HandlerService.service.JwtService;
import com.gestionEvent.handlerService.HandlerService.service.PrestataireService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
    @Autowired
	private JwtService jwtService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private PrestataireService prestataireService;

	@Autowired	
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword());	

		Authentication auth = authenticationManager.authenticate(creds);
		String username = auth.getName();

		Long idc = clientService.getIdUsername(username);
		
		Long idp = prestataireService.getIdUsername(username);

		String rolec = clientService.getRoleUsername(username);

		String rolep = prestataireService.getRoleUsername(username);

		// Generate token
		if (rolec!=null) {
			String jwts = jwtService.getToken(auth.getName(), idc, rolec);
		// Build response with the generated token
			return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		}else{
			String jwts = jwtService.getToken(auth.getName(), idp, rolep);
					// Build response with the generated token
			return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		}
			



	}
}
