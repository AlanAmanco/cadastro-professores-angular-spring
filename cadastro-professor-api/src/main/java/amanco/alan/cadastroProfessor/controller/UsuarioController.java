package amanco.alan.cadastroProfessor.controller;

import amanco.alan.cadastroProfessor.config.security.TokenService;
import amanco.alan.cadastroProfessor.model.entity.LoginResponseDTO;
import amanco.alan.cadastroProfessor.model.entity.Usuario;
import amanco.alan.cadastroProfessor.model.entity.UsuarioDTO;
import amanco.alan.cadastroProfessor.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity salvar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        if(repository.findByUsername(usuarioDTO.getUsername()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPasswor = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());

        Usuario novoUsuario = new Usuario();

        novoUsuario.setUsername(usuarioDTO.getUsername());
        novoUsuario.setPassword(encryptedPasswor);

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO usuarioDTO){

        var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(),usuarioDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
