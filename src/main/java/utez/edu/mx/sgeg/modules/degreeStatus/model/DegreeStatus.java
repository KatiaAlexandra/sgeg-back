package utez.edu.mx.sgeg.modules.degreeStatus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "degree_status")
public class DegreeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_degree_status")
    private int id;

    @Column(name = "degree_status", nullable = false, length = 100)
    private String degreeStatus;

    @Column(name = "code_degree_status", nullable = false, length = 5)
    private String codeDegreeStatus;

    @OneToMany(mappedBy = "degreeStatus")
    @JsonIgnore
    private List<EduInfo> eduInfoList;
}
