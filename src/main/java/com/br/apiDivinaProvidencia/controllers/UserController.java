package com.br.apiDivinaProvidencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.apiDivinaProvidencia.documents.User;
import com.br.apiDivinaProvidencia.documents.dto.CredentialsDTO;
import com.br.apiDivinaProvidencia.documents.dto.TokenDTO;
import com.br.apiDivinaProvidencia.exception.PasswordInvalid;
import com.br.apiDivinaProvidencia.services.JwtService;
import com.br.apiDivinaProvidencia.services.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private JwtService jwtService;

	@PostMapping(path = "/save")
	public User save(@RequestBody User user) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(senhaCriptografada);
		return this.userService.save(user);
	}

	@PostMapping(path = "/auth")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDTO credentialsDTO)
			throws UsernameNotFoundException {
		try {
			User user = new User(credentialsDTO.getLogin(), credentialsDTO.getPassword());
			UserDetails userAutheticated = this.userService.auhtehticate(user);
			String token = this.jwtService.generatedToken(user);
			return ResponseEntity.ok(new TokenDTO(user.getLogin(), token));

		} catch (UsernameNotFoundException | PasswordInvalid e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
