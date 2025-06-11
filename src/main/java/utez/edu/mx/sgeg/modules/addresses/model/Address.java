package utez.edu.mx.sgeg.modules.addresses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.persons.model.Person;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private long id;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "zip_code", nullable = false, length = 5)
    private String zipCode;

    @Column(name = "outer_number", nullable = false, length = 5)
    private String outerNumber;

    @Column(name="inner_number", nullable = false, length = 5)
    private String innerNumber;

    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;



}
