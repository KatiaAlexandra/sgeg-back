package utez.edu.mx.sgeg.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utez.edu.mx.sgeg.modules.users.models.IUserRepository;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }

}

