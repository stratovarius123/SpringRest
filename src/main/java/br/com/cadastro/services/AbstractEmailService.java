package br.com.cadastro.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.cadastro.domain.Pedido;


public abstract class AbstractEmailService implements EmailService {

	@Autowired
	TemplateEngine engine;
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	JavaMailSender javaMail;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido){
		SimpleMailMessage messagem  = prepareSimpleMailMessageFromPedido(pedido); 
		sendEmail(messagem);	
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setTo(pedido.getCliente().getEmail());
		mensagem.setFrom(getSender());
		mensagem.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mensagem.setSentDate(new Date(System.currentTimeMillis()));
		mensagem.setText(pedido.toString());
		return mensagem;	
	}
	
	protected String htmlFromTemplatePedido(Pedido pedido){
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return  engine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try{
			MimeMessage messagem  = prepareMimeMessageFromPedido(pedido); 
			sendHtmlEmail(messagem);
		}
		catch(MessagingException e){
			sendOrderConfirmationEmail(pedido);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		
		MimeMessage messagem = javaMail.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(messagem,true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject(" Confirme o pedido ");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido),true);
		return messagem;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
}
