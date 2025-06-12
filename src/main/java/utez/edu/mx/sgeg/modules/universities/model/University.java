package utez.edu.mx.sgeg.modules.universities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.areas.model.Area;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;
import utez.edu.mx.sgeg.modules.eduLevels.model.EduLevel;
import utez.edu.mx.sgeg.modules.users.models.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "univeristy")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_university")
    private long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name="university_code", nullable = false, length = 250)
    private String universityCode;

    @Column(name = "university_type", nullable = false, length = 250)
    private String universityType;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_educational_level", referencedColumnName = "id_edu_level")
    private EduLevel eduLevel;

    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id_user")
    private User adminUni;

    @ManyToMany
    @JoinTable(
            name = "uni_area",
            joinColumns = @JoinColumn(name = "id_university"),
            inverseJoinColumns = @JoinColumn(name = "id_area")
    )
    List<Area> areas;

    @OneToMany(mappedBy = "university")
    @JsonIgnore
    private List<EduInfo> eduInfoList;



}
