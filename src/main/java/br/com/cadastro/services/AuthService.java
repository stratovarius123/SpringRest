package br.com.cadastro.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	BCryptPasswordEncoder bcript;
	
	@Autowired
	EmailService emailService;
	
	Random randon = new Random();
	
	public void sendNewPassword(String email){
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null ){
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPassword = newPassword();
		cliente.setSenha(bcript.encode(newPassword));
		
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente,newPassword);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0 ; i < 10 ; i++){
			vet[i] = randomChar();
		}
		return vet.toString();
	}

	private char randomChar() {
		int opt = randon.nextInt(3);
		if(opt  == 0){
			return (char) (randon.nextInt(10)+48);
		}else if(opt == 1){
			return (char) (randon.nextInt(26)+65);
		}else{
			return (char) (randon.nextInt(26)+97);
		}
	}
	
}
