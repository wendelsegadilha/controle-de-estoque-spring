package xyz.wendelsegadilha.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.wendelsegadilha.app.dao.UsuarioDao;
import xyz.wendelsegadilha.app.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Transactional(readOnly = false)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

}
