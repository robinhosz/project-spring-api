package br.com.coursespring.fourcamp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import br.com.coursespring.fourcamp.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);    //Pega a hora atual e soma os minutos, no caso 30 minutos que foi uq eu defini
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		//Pega a zona do brasil
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
		
	
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(FourcampApplication.class, args);
		JwtService service = contexto.getBean(JwtService.class);
		Usuario usuario = Usuario.builder().login("fulano").build();
		String token = service.gerarToken(usuario);
		System.out.println(token);
	}
	 
}
