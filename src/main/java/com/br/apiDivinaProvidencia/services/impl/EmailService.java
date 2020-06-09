package com.br.apiDivinaProvidencia.services.impl;

import com.br.apiDivinaProvidencia.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private JavaMailSender javaMailSender;

	private String from = "cleytoncomp@gmail.com";
	private String title = "Solicitação de alteração de senha";
	private String message = "Caso não tenha feito essa solicitação desconsidere essa mensagem! \nClique no link abaixo para realizar a alteração da senha. Link válido por 30 minutos.";
	private String link = "https://divinaprovidencia-dev.firebaseapp.com/auth/forgetPassword/";

	public void sendEmailForgetPassword(String login) {
		String token = this.jwtService.generatedTokenWithExpirationSendEmail(login, "30");
		if (this.userServiceImpl.userValid(login)) {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom(this.from);
			simpleMailMessage.setTo(login.trim());
			simpleMailMessage.setSubject(this.title);
			simpleMailMessage.setText(this.message + "\n" + link + token);

			this.javaMailSender.send(simpleMailMessage);

		}
	}
}
