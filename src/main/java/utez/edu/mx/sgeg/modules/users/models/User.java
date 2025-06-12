package utez.edu.mx.sgeg.modules.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utez.edu.mx.sgeg.modules.answers.model.Answer;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;
import utez.edu.mx.sgeg.modules.persons.model.Person;
import utez.edu.mx.sgeg.modules.roles.model.Role;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;
import utez.edu.mx.sgeg.modules.universities.model.University;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @Column(name="username", nullable = false, length = 250)
    private String username;


    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "adminUni")
    @JsonIgnore
    private List<University> universities;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Survey> surveys;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<EduInfo> eduInfoList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Answer> answers;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"ROLE_"+ role.getName());
    }

}
