package utez.edu.mx.sgeg.modules.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sgeg.modules.auth.model.AuthRequest;
import utez.edu.mx.sgeg.modules.auth.service.AuthService;


import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Obtener todos los usuarios")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        Map<String, String> response = Map.of("token", token);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener todos los usuarios")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Sesión cerrada correctamente.");
    }

}