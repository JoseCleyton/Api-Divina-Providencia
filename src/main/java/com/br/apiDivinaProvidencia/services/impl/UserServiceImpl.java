package com.br.apiDivinaProvidencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		User user = this.userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		String roles[] = user.isAdmin() ? new String[] { "USER", "ADMIN" } : new String[] { "USER" };

		return org.springframework.security.core.userdetails.User.builder().username(user.getLogin())
				.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();

	}

	public User authenticate(User user) throws PasswordInvalid {
		this.loadUserByUsername(user.getLogin());
		User u = this.userRepository.findByLogin(user.getLogin());
		if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
			return u;
		}
		throw new PasswordInvalid("Senha inválida !!! ");

	}

	public User save(User user) {
		return this.userRepository.save(user);
	}
	public User update(User user) {
		return this.userRepository.save(user);
	}
	
	public User getUserByLogin(String login) {
		return this.userRepository.findByLogin(login);
	}

}
