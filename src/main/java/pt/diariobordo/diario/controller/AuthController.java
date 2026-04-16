package pt.diariobordo.diario.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.LoginRequestDTO;
import pt.diariobordo.diario.dto.LoginResponseDTO;
import pt.diariobordo.diario.dto.RegistoUsuarioDTO;
import pt.diariobordo.diario.dto.UsuarioPerfilDTO;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.entity.Pessoa;
import pt.diariobordo.diario.entity.Tutor;
import pt.diariobordo.diario.entity.Usuario;
import pt.diariobordo.diario.service.TokenService;
import pt.diariobordo.diario.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    private UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService,  TokenService tokenService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> getPerfil(@AuthenticationPrincipal Usuario userLogado) {
        Pessoa perfil = userLogado.getPerfil();
        String infoAdicional = "";

        // Se for Formando, pega o Hospital. Se for Tutor, pega a Especialidade.
        if (perfil instanceof Formando f) {
            infoAdicional = f.getHospitalOrigem();
        } else if (perfil instanceof Tutor t) {
            infoAdicional = t.getEspecialidade();
        }

        UsuarioPerfilDTO dto = new UsuarioPerfilDTO(
                perfil.getId(),
                perfil.getNome(),
                userLogado.getEmail(),
                perfil.getTelefone(),
                perfil.getNif(),
                infoAdicional,
                perfil.getCreatedAt(),
                userLogado.getRole().name()
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 1. Pegamos o utilizador logado
        Usuario usuario = (Usuario) auth.getPrincipal();

        // 2. Geramos o token JWT
        var token = tokenService.generateToken(usuario);

        // 3. Retornamos o DTO com o ID da PESSOA (não o ID do usuário de login)
        return ResponseEntity.ok(new LoginResponseDTO(
                token,
                usuario.getPerfil().getId(), // Aqui está a chave!
                usuario.getPerfil().getNome(),
                usuario.getRole().name()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registar(@RequestBody RegistoUsuarioDTO data) {
        try {
            usuarioService.registarNovoUsuario(data);
            return ResponseEntity.ok().body("Utilizador registado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}