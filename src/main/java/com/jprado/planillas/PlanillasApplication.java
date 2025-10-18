package com.jprado.planillas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jprado.planillas.usuarios.Usuario;
import com.jprado.planillas.usuarios.UsuarioRepository;

@SpringBootApplication
public class PlanillasApplication  implements CommandLineRunner{

	 private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public PlanillasApplication(UsuarioRepository usuarioRepository,
                                BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
	public static void main(String[] args) {
		SpringApplication.run(PlanillasApplication.class, args);
	}
	@Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            // configura otros campos requeridos (roles, etc.)
            usuarioRepository.save(admin);
            System.out.println("✅ Usuario admin creado por defecto: admin / admin123");
        }
    }

}
