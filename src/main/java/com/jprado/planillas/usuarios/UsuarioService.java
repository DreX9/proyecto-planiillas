package com.jprado.planillas.usuarios;

import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;

    

    public List<Usuario> getAllUsuario() {
        return repository.findAll();
    }

    public Usuario getByIdUsuario(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Usuario updateAddUsuario(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().startsWith("$2a$")) {
            //hashear contraseña
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        //guardamos los datos
        return repository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }

    public Usuario buscUsuarioName(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
