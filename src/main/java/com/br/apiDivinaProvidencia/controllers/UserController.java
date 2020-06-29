package com.br.apiDivinaProvidencia.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.apiDivinaProvidencia.documents.User;
import com.br.apiDivinaProvidencia.documents.dto.CredentialsDTO;
import com.br.apiDivinaProvidencia.documents.dto.TokenDTO;
import com.br.apiDivinaProvidencia.documents.dto.UserDTO;
import com.br.apiDivinaProvidencia.exception.PasswordInvalid;
import com.br.apiDivinaProvidencia.exception.TokenInvalidException;
import com.br.apiDivinaProvidencia.services.JwtService;
import com.br.apiDivinaProvidencia.services.impl.EmailService;
import com.br.apiDivinaProvidencia.services.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private EmailService emailService;

	@PostMapping(path = "/save")
	public ResponseEntity<UserDTO> save(@RequestBody User user) {
		String passwordEncrypted = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(passwordEncrypted);
		User u = this.userService.save(user);
		UserDTO userDTO = new UserDTO(u.getId(), u.getLogin(), u.isAdmin());
		return ResponseEntity.ok(userDTO);
	}

	@PostMapping(path = "/auth")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDTO credentialsDTO)
			throws UsernameNotFoundException {
		try {
			User user = new User(credentialsDTO.getLogin(), credentialsDTO.getPassword());
			User userAutheticated = this.userService.authenticate(user);
			String token = this.jwtService.generatedToken(user);
			return ResponseEntity.ok(new TokenDTO(user.getLogin(), token));

		} catch (UsernameNotFoundException | PasswordInvalid e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	@PostMapping(path = "/auth/forgetPassword")
	public ResponseEntity<SimpleMailMessage> forgetPassword(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(this.emailService.sendEmailForgetPassword(user.getLogin())
				.orElseThrow(() -> new com.br.apiDivinaProvidencia.exception.UsernameNotFoundException()));
	}

	@PostMapping(path = "auth/forgetPassword/validateLink")
	public ResponseEntity<UserDTO> validateLink(@RequestBody String token)
			throws TokenInvalidException, com.br.apiDivinaProvidencia.exception.UsernameNotFoundException {

		if (this.jwtService.tokenIsValid(token).isPresent()) {
			String login = this.jwtService.getLoginUser(token).get();
			User user = this.userService.getUserByLogin(login)
					.orElseThrow(() -> new com.br.apiDivinaProvidencia.exception.UsernameNotFoundException());
			return ResponseEntity.ok(new UserDTO(user.getId(), user.getLogin(), user.isAdmin()));
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping(path = "auth/updatePassword")
	public ResponseEntity<UserDTO> updatePassword(@RequestBody User user) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(senhaCriptografada);
		User u = this.userService.update(user);
		UserDTO userDTO = new UserDTO(u.getId(), u.getLogin(), u.isAdmin());
		return ResponseEntity.ok(userDTO);
	}

}
