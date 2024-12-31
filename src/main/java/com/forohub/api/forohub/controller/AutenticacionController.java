package com.forohub.api.forohub.controller;

import com.forohub.api.forohub.domain.usuario.*;
import com.forohub.api.forohub.infra.security.DatosJWTToken;
import com.forohub.api.forohub.infra.security.SecurityConfigurations;
import com.forohub.api.forohub.infra.security.SecurityFilter;
import com.forohub.api.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SecurityConfigurations securityConfigurations;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        try {
            Authentication authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo(),
                    datosAutenticacionUsuario.contrasena());
            var usuarioAutenticado = authenticationManager.authenticate(authtoken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datosRegistrarUsuario){
        String encryptedPassword = securityConfigurations.passwordEncoder().encode(datosRegistrarUsuario.contrasena());
        Usuario usuario = new Usuario(datosRegistrarUsuario);
        usuario.setContrasena(encryptedPassword);
        usuario = usuarioRepository.save(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(),usuario.getNombre(),usuario.getCorreo(),encryptedPassword);

        return ResponseEntity.ok(datosRespuestaUsuario);
    }
}
