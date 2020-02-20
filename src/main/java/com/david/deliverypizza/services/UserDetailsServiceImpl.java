package com.david.deliverypizza.services;

import com.david.deliverypizza.model.Usuario;
import com.david.deliverypizza.repository.UsuarioRepository;
import com.david.deliverypizza.autentication.security.Perfil;
import com.david.deliverypizza.autentication.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<Perfil> perfis = new HashSet<>();
        perfis.add(Perfil.ADMIN);
        return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha(), perfis);
    }
}