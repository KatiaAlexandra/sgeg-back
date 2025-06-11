package utez.edu.mx.sgeg.modules.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @Column(name="username", nullable = false, length = 250)
    private String username;


    @Column(name = "password", nullable = false, length = 10)
    private String password;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

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


    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<Survey> surveys;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<EduInfo> eduInfoList;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<Answer> answers;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<EduInfo> getEduInfoList() {
        return eduInfoList;
    }

    public void setEduInfoList(List<EduInfo> eduInfoList) {
        this.eduInfoList = eduInfoList;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
