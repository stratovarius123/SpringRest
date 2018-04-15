package br.com.cadastro.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		Log.info("Simulando Envio de Email....");
		Log.info(mensagem.toString());
		Log.info("Email Enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		Log.info("Simulando Envio de Email html....");
		Log.info(msg.toString());
		Log.info("Email Enviado");
	}

}
