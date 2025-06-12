package utez.edu.mx.sgeg.modules.eduInfo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.areas.model.Area;
import utez.edu.mx.sgeg.modules.degreeStatus.model.DegreeStatus;
import utez.edu.mx.sgeg.modules.eduLevels.model.EduLevel;
import utez.edu.mx.sgeg.modules.mode.model.Mode;
import utez.edu.mx.sgeg.modules.universities.model.University;
import utez.edu.mx.sgeg.modules.users.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "edu_info")
public class EduInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edu_info")
    private long id;

    @Column(name = "final_avg", nullable = false)
    private double finalAvg;

    @Column(name = "has_another_degree", nullable = false)
    private boolean hasAnotherDegree;

    @ManyToOne
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_edu_level", referencedColumnName = "id_edu_level")
    private EduLevel eduLevel;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_university", referencedColumnName = "id_university")
    private University university;

    @Column(name = "graduated_identifier", nullable = false, length = 10)
    private String graduatedIdentifier;

    @ManyToOne
    @JoinColumn(name = "id_mode", referencedColumnName = "id_mode")
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "id_degree_status", referencedColumnName = "id_degree_status")
    private DegreeStatus degreeStatus;
}
