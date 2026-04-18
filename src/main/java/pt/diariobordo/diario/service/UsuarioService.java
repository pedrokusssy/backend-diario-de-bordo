package pt.diariobordo.diario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.RegistoUsuarioDTO;
import pt.diariobordo.diario.entity.*;
import pt.diariobordo.diario.entity.enums.UserRole;
import pt.diariobordo.diario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registarNovoUsuario(RegistoUsuarioDTO dto) {

        // 1. Verifica se o e-mail já existe
        if (usuarioRepository.findByEmail(dto.email()) != null) {
            throw new RuntimeException("Já existe um utilizador com este e-mail.");
        }

        // 2. Cria a entidade base de credenciais
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.email());
        novoUsuario.setPassword(passwordEncoder.encode(dto.password())); // Encripta a senha!

        UserRole papel = UserRole.valueOf(dto.role().toUpperCase());
        novoUsuario.setRole(papel);

        // 3. O Polimorfismo em Ação: Decide qual "Pessoa" vai ser
        if (papel == UserRole.TUTOR) {
            Tutor tutor = new Tutor();
            tutor.setNome(dto.nome());
            tutor.setTelefone(dto.telefone());
            tutor.setNif(dto.nif());
          //  tutor.setEspecialidade(dto.especialidade());

            // Associa o Tutor ao Perfil do Utilizador
            novoUsuario.setPerfil(tutor);

        } else if (papel == UserRole.FORMANDO) {
            Formando formando = new Formando();
            formando.setNome(dto.nome());
            formando.setTelefone(dto.telefone());
            formando.setNif(dto.nif());
            formando.setHospitalOrigem(dto.hospitalOrigem());

            // Associa o Formando ao Perfil do Utilizador
            novoUsuario.setPerfil(formando);
        }

        // 4. Salva tudo de uma vez. O CascadeType.ALL fará os INSERTs na tabela
        // "usuarios", "pessoas" e "tutores"/"formandos" automaticamente!
        return usuarioRepository.save(novoUsuario);
    }
}