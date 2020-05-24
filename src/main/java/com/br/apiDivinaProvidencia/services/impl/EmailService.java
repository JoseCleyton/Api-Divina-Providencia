package com.br.apiDivinaProvidencia.services.impl;

import com.br.apiDivinaProvidencia.documents.User;
import com.br.apiDivinaProvidencia.repository.UserRepository;
import com.br.apiDivinaProvidencia.services.JwtService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserServiceImpl userServiceImpl;


	public Mail sendEmailForgetPassword(String login) throws Exception, UsernameNotFoundException {
		User user = this.userServiceImpl.getUserByLogin(login);
		if (user != null) {
			String token = this.jwtService.generatedTokenWithExpirationSendEmail(user.getLogin(), "30");
			Email from = new Email("cleytoncomp@gmail.com");
			String title = "Recuperação de Senha";
			Email to = new Email(login);
			Content content = new Content("text/plain",
					"Olá, recebemos a sua solicitação de mudança de senha. Este e-mail contém o link que você precisa para mudar sua senha. Caso não tenha feito essa solicitação pode ignorar esse e-mail."
					+ "\n Link válido por 30 minutos... \n https://divinaprovidencia-dev.firebaseapp.com/auth/forgetPassword/"
							+ token);
			Mail mail = new Mail(from, title, to, content);

			SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			Request request = new Request();
			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
				return mail;
			} catch (IOException ex) {
				throw ex;
			}
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado !!! ");
		}

	}
	/*
	public static void main(String[] args) throws IOException {
	    Email from = new Email("cleytoncomp@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email("cleytinhow353@gmail.com");
	    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	  }
	*/
	
}
