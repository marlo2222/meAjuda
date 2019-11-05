package com.meAjuda.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.UsuarioService;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.login(username);
		System.out.println(usuario.getPrimeiroNome());
		if (usuario == null){
			throw new UsernameNotFoundException("usuario não encontrado");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, grantedAuthorities);
	}

}
