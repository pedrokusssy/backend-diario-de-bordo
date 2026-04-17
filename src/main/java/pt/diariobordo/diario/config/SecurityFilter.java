package pt.diariobordo.diario.config; // Ajusta para o teu pacote real

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// Ajusta estes imports para os teus pacotes corretos
import pt.diariobordo.diario.repository.UsuarioRepository;
import pt.diariobordo.diario.service.TokenService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Recupera o token do pedido (Request)
        var token = this.recoverToken(request);

        if (token != null) {
            // 2. Valida o token e extrai o e-mail
            var email = tokenService.validateToken(token);

            if (email != null && !email.isEmpty()) {
                // 3. Busca o utilizador na Base de Dados
                UserDetails usuario = usuarioRepository.findByEmail(email);

                // 4. Se existir, diz ao Spring Security: "Este gajo está autenticado!"
                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // 5. Continua o fluxo normal da API
        filterChain.doFilter(request, response);
    }

    // O SEGREDO ESTÁ NESTE MÉTODO:
    // No teu SecurityFilter.java
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) return authHeader.replace("Bearer ", "");

        // O teu contorno: também procura no parâmetro "token"
        return request.getParameter("token");
    }

}