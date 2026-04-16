package pt.diariobordo.diario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.diariobordo.diario.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O "username" no nosso caso é o email
        UserDetails usuario = repository.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Utilizador não encontrado!");
        }

        return usuario;
    }
}
