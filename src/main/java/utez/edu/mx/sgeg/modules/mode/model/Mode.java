package utez.edu.mx.sgeg.modules.mode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.eduInfo.model.EduInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mode")
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mode")
    private int id;

    @Column(name = "mode", nullable = false, length = 10)
    private String mode;

    @Column(name = "code_mode", nullable = false, length = 5)
    private String codeMode;

    @OneToMany(mappedBy = "mode")
    @JsonIgnore
    private List<EduInfo> eduInfoList;
}
