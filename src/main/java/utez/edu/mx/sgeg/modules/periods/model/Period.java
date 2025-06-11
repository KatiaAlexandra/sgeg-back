package utez.edu.mx.sgeg.modules.periods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    private long id;

    @Column(name = "start_month", nullable = false, length = 250)
    private String startMonth;

    @Column(name = "end_month", nullable = false, length = 250)
    private String endMonth;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @OneToMany(mappedBy = "period")
    @JsonIgnore
    private List<Survey> surveys;
}
