package utez.edu.mx.sgeg.modules.gender.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.persons.model.Person;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender")
    private int id;

    @Column(name="gender_name")
    private String gender;

    @Column(name="gender_code")
    private String genderCode;

    @OneToMany(mappedBy = "gender")
    @JsonIgnore
    private List<Person> person;

}
