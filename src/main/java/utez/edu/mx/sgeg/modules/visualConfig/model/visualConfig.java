package utez.edu.mx.sgeg.modules.visualConfig.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visual_config")
public class visualConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visual_config")
    private long id;

    @Column(name="primary_color", nullable=false, length = 250)
    private String primaryColor;

    @Column(name="secondary_color", nullable=false, length = 250)
    private String secondaryColor;

    @Lob
    @Column(name = "logo", columnDefinition = "TEXT")
    private String logo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date")
    private Date modificationDate;

    @Column(name = "modified_by", nullable = false, length = 250)
    private String modifiedBy;
}
