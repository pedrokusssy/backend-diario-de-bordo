package pt.diariobordo.diario.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pt.diariobordo.diario.entity.enums.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter // Importante para podermos fazer usuario.setPerfil(novoFormando) no momento do registo
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role; // FORMANDO ou TUTOR

    /**
     * A MÁGICA DO POLIMORFISMO:
     * O perfil pode guardar tanto a classe Tutor quanto a classe Formando,
     * pois ambas herdam de Pessoa. O CascadeType.ALL garante que ao
     * salvar o Usuario, o perfil correspondente também é salvo na BD.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa perfil;

    /**
     * Define as permissões (autoridades) do utilizador.
     * Importante: O Spring Security espera que roles comecem com "ROLE_"
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.TUTOR) {
            // Um Tutor tem acesso às funções de tutor E de formando
            return List.of(
                    new SimpleGrantedAuthority("ROLE_TUTOR"),
                    new SimpleGrantedAuthority("ROLE_FORMANDO")
            );
        } else {
            // Um Formando só tem acesso às funções de formando
            return List.of(new SimpleGrantedAuthority("ROLE_FORMANDO"));
        }
    }

    @Override
    public String getUsername() {
        return email; // No nosso caso, o login é feito via email
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Configurações padrão de conta (geralmente mantemos true num sistema simples)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}