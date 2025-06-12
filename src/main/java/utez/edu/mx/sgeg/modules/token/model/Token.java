package utez.edu.mx.sgeg.modules.token.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {
    @Id
    private String token;
    private String username;
    private Date expiration;
    private boolean revoked;
    public boolean isActive(){
        return !revoked && (expiration == null || expiration.after(new Date()));
    }
}
