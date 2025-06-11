package utez.edu.mx.sgeg.modules.persons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.addresses.model.Address;
import utez.edu.mx.sgeg.modules.gender.model.Gender;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "m_surname", length = 50)
    private String m_surname;

    @Column(name = "p_surname", nullable = false, length = 50)
    private String p_surname;

    @Column(name = "curp",  nullable = false, length = 18)
    private String curp;

    @Column(name="home_phome", nullable = false, length = 10)
    private String homePhone;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "birthplace", nullable = false, length = 100)
    private String birthplace;

    @Temporal(TemporalType.DATE)
    @Column(name= "birthdate", nullable = false )
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "id_gender", referencedColumnName = "id_gender")
    private Gender gender;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Address> addresses;

    @OneToOne(mappedBy = "person")
    private UserEntity userEntity;
}
