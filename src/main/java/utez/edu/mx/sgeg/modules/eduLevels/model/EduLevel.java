package utez.edu.mx.sgeg.modules.eduLevels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="edu_level")
public class EduLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_edu_level")
    private long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @OneToMany(mappedBy = "eduLevel")
    @JsonIgnore
    private List<EduInfo> eduInfoList;
}
