package utez.edu.mx.sgeg.modules.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.sgeg.modules.persons.model.IPersonRepository;
import utez.edu.mx.sgeg.modules.users.models.IUserRepository;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPersonRepository personRepository;

    public UserEntity SaveUser(){
        UserEntity user = new UserEntity();
        user.setUsername("Katia2");
        user.setPassword("admin");
        user.setPerson(personRepository.findById(1L).orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        user.setStatus(true);

        return userRepository.save(user);
    }
}
