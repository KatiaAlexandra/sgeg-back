package utez.edu.mx.sgeg.modules.families.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.areas.model.Area;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_family")
    private long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @OneToMany(mappedBy = "family")
    @JsonIgnore
    private List<Area> areas;
}
