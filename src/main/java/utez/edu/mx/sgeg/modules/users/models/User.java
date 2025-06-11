package utez.edu.mx.sgeg.modules.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.answers.model.Answer;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;
import utez.edu.mx.sgeg.modules.persons.model.Person;
import utez.edu.mx.sgeg.modules.roles.model.Role;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;
import utez.edu.mx.sgeg.modules.universities.model.University;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @Column(name="username", nullable = false, length = 250)
    private String username;

    @Column(name = "password", nullable = false, length = 10)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles;

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


}
