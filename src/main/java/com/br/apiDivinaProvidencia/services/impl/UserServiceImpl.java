package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.User;
import com.br.apiDivinaProvidencia.exception.PasswordInvalid;
import com.br.apiDivinaProvidencia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> user = this.userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		String roles[] = user.get().isAdmin() ? new String[] { "USER", "ADMIN" } : new String[] { "USER" };

		return org.springframework.security.core.userdetails.User.builder().username(user.get().getLogin())
				.password(passwordEncoder.encode(user.get().getPassword())).roles(roles).build();

	}

	public User authenticate(User user) throws PasswordInvalid {
		this.loadUserByUsername(user.getLogin());
		Optional<User> u = this.userRepository.findByLogin(user.getLogin());
		if (passwordEncoder.matches(user.getPassword(), u.get().getPassword())) {
			return u.get();
		}
		throw new PasswordInvalid("Senha inválida !!! ");

	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public User update(User user) {
		return this.userRepository.save(user);
	}

	public Optional<User> getUserByLogin(String login) {
		return this.userRepository.findByLogin(login.trim());
	}

	public boolean userValid(String login) {
		boolean toReturn = false;
		List<User> users = this.userRepository.findAll();
		for (User user : users) {
			System.out.println(user.getLogin());
			if (user.getLogin().equals(login.trim())) {
				toReturn = true;
				System.out.println("valido");
			}
		}
		return toReturn;
	}

}
