package com.br.apiDivinaProvidencia.services.impl;

import com.br.apiDivinaProvidencia.documents.User;
import com.br.apiDivinaProvidencia.repository.UserRepository;
import com.br.apiDivinaProvidencia.services.JwtService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;

	public void sendEmailForgetPassword(String email) throws Exception, UsernameNotFoundException {
		User user = this.userRepository.findByLogin(email);
		if (user != null) {
			String token = this.jwtService.generatedTokenWithExpirationSendEmail(user.getLogin(), "30");
			Email from = new Email("divinaprovidencia@gmail.com");
			String title = "Recuperação de Senha";
			Email to = new Email(email);
			Content content = new Content("text/plain",
					"Olá, recebemos a sua solicitação de mudança de senha. Este e-mail contém o link que você precisa para mudar sua senha. Caso não tenha feito essa solicitação pode ignorar esse e-mail. \n Link válido por 30 minutos... \n https://apidivinaprovidencia.herokuapp.com/auth/forgetPassword/"
							+ token);
			Mail mail = new Mail(from, title, to, content);

			SendGrid sg = new SendGrid("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");
			Request request = new Request();
			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
			} catch (IOException ex) {
				throw ex;
			}
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado !!! ");
		}
	}

}
