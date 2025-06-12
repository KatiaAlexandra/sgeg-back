package utez.edu.mx.sgeg.modules.gender.model;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Integer>{
    Optional<Gender> findByGenderCode(String code);
}

