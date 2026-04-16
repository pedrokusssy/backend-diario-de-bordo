package pt.diariobordo.diario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pt.diariobordo.diario.entity.Tutor;
import pt.diariobordo.diario.entity.Usuario;
import pt.diariobordo.diario.entity.enums.UserRole;
import pt.diariobordo.diario.repository.UsuarioRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se a tabela de utilizadores está vazia
        if (usuarioRepository.count() == 0) {
            System.out.println("🌱 Base de dados vazia. A semear o primeiro utilizador...");

            // 1. Criar o Perfil (neste caso, um Tutor/Admin)
            Tutor tutor = new Tutor();
            tutor.setNome("Administrador do Sistema");
            tutor.setEspecialidade("Gestão Geral");

            // 2. Criar as Credenciais (Usuario)
            Usuario admin = new Usuario();
            admin.setEmail("admin@diario.pt"); // O teu email de login
            // IMPORTANTE: A senha tem de ser encriptada antes de ir para a BD!
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(UserRole.TUTOR);

            // 3. Ligar as duas partes (A Mágica da Herança)
            admin.setPerfil(tutor);

            // 4. Salvar na Base de Dados
            usuarioRepository.save(admin);

            System.out.println("✅ Utilizador de teste criado com sucesso!");
            System.out.println("➡️ Email: admin@diario.pt");
            System.out.println("➡️ Senha: 123456");
        }
    }
}