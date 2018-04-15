package br.com.cadastro.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	MailSender mailsender;
	
	@Autowired
	JavaMailSender javamailsender;
	
	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		Log.info("Simulando Envio de Email....");
		mailsender.send(mensagem);
		Log.info("Email Enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		Log.info("Simulando Envio de Email....");
		javamailsender.send(msg);
		Log.info("Email Enviado");
	}
}
