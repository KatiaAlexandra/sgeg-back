package utez.edu.mx.sgeg.modules.token.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token,String> {
    boolean existsByTokenAndRevokedTrue(String token);
    Optional<Token> findByToken(String token);
    List<Token> findByRevokedFalse();

}
