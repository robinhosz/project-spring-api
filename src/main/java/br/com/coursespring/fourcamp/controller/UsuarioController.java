package br.com.coursespring.fourcamp.controller;

import br.com.coursespring.fourcamp.dto.CredenciaisDTO;
import br.com.coursespring.fourcamp.dto.TokenDTO;
import br.com.coursespring.fourcamp.exceptions.SenhaInvalidaException;
import br.com.coursespring.fourcamp.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.coursespring.fourcamp.model.Usuario;
import br.com.coursespring.fourcamp.service.impl.UsuarioServiceImpl;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);

	}

	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
		Usuario usuario = Usuario.builder().login(credenciaisDTO.getLogin()).senha(credenciaisDTO.getSenha()).build();
		UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
		String token = jwtService.gerarToken(usuario);
		return new TokenDTO(usuario.getLogin(), token);
		}catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		}
	}
}
