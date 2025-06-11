package utez.edu.mx.sgeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utez.edu.mx.sgeg.modules.users.service.UserService;

@SpringBootApplication
public class SgegApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgegApplication.class, args);
	}

}
