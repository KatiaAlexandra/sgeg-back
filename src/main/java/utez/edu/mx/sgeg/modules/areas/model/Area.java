package utez.edu.mx.sgeg.modules.areas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;
import utez.edu.mx.sgeg.modules.families.model.Family;
import utez.edu.mx.sgeg.modules.universities.model.University;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @Column(name = "code")
    private int code;

    @ManyToOne
    @JoinColumn(name = "id_family", referencedColumnName = "id_family")
    private Family family;

    @ManyToMany(mappedBy = "areas")
    private List<University> universities;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private List<EduInfo> eduInfoList;
}
