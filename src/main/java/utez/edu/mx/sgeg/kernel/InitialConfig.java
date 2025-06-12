package utez.edu.mx.sgeg.kernel;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import utez.edu.mx.sgeg.modules.gender.model.Gender;
import utez.edu.mx.sgeg.modules.gender.model.IGenderRepository;
import utez.edu.mx.sgeg.modules.persons.model.IPersonRepository;
import utez.edu.mx.sgeg.modules.persons.model.Person;
import utez.edu.mx.sgeg.modules.roles.model.IRoleRepository;
import utez.edu.mx.sgeg.modules.roles.model.Role;
import utez.edu.mx.sgeg.modules.users.models.IUserRepository;
import utez.edu.mx.sgeg.modules.users.models.User;

@Component
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {

    private final IGenderRepository genderRepository;
    private final IRoleRepository roleRepository;
    private final IPersonRepository personRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Insertar géneros si no existen
        Gender male = genderRepository.findByGenderCode("1").orElseGet(() -> {
            Gender g = new Gender();
            g.setGender("MASCULINO");
            g.setGenderCode("1");
            return genderRepository.save(g);
        });

        Gender female = genderRepository.findByGenderCode("2").orElseGet(() -> {
            Gender g = new Gender();
            g.setGender("FEMENINO");
            g.setGenderCode("2");
            return genderRepository.save(g);
        });

        // Insertar roles si no existen
        insertRoleIfNotExists("MASTER");
        insertRoleIfNotExists("ADMIN_UNIVERSITY");
        insertRoleIfNotExists("STUDY");
        insertRoleIfNotExists("FOLLOW_UP");

        // Crear usuario por defecto solo si no existe
        if (!userRepository.findByUsername("master").isPresent()) {
            Person person = new Person();
            person.setName("Usuario");
            person.setP_surname("Master");
            person.setM_surname("Sistema");
            person.setCurp("XAXX010101HNEXXXA4"); 
            person.setHomePhone("7771234567");
            person.setPhoneNumber("7777654321");
            person.setBirthdate(Date.valueOf("1990-01-01"));
            person.setBirthplace("CUERNAVACA");
            person.setGender(male);
            person = personRepository.save(person);

            Role masterRole = roleRepository.findByName("MASTER")
                .orElseThrow(() -> new RuntimeException("Rol MASTER no encontrado"));

            User user = new User();
            user.setUsername("master");
            user.setPassword(passwordEncoder.encode("master")); // Contraseña cifrada
            user.setStatus(true);
            user.setPerson(person);
            user.setRole(masterRole);

            userRepository.save(user);
        }
    }

    private void insertRoleIfNotExists(String roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            return roleRepository.save(role);
        });
    }
}
