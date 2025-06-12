package utez.edu.mx.sgeg.modules.auth.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

}
