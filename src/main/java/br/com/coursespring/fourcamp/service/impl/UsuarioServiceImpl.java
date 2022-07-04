package br.com.coursespring.fourcamp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coursespring.fourcamp.model.Usuario;
import br.com.coursespring.fourcamp.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario =  repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário n encontrado"));
      String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
      
    return User.builder()
    		.username(usuario.getLogin())
    		.password(usuario.getSenha())
    		.roles(roles)
    		.build();
    
    
    }

}
