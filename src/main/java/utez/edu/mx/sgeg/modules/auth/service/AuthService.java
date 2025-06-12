package utez.edu.mx.sgeg.modules.auth.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import utez.edu.mx.sgeg.modules.token.model.ITokenRepository;
import utez.edu.mx.sgeg.modules.token.model.Token;
import utez.edu.mx.sgeg.modules.users.models.IUserRepository;
import utez.edu.mx.sgeg.modules.users.models.User;
import utez.edu.mx.sgeg.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider; // tu utilidad para generar y validar JWT
    private final IUserRepository userRepository;
    private final ITokenRepository tokenRepository;

    public String login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtTokenProvider.generateToken(userDetails.getUsername(), getRoleFromUserDetails(userDetails));

        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setUsername(user.getUsername());

        Date expirationDate = new Date(System.currentTimeMillis() + 86400000);
        newToken.setExpiration(expirationDate);
        newToken.setRevoked(false);
        tokenRepository.save(newToken);
        return token;
    }

    private String getRoleFromUserDetails(UserDetails userDetails) {
        // Asumiendo que solo tienes un rol (o tomas el primero)
        return userDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse("USER");
    }

    public void logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);

        tokenRepository.findByToken(jwt).ifPresent(token -> {
            token.setRevoked(true);
            tokenRepository.save(token);
        });
    }

}
