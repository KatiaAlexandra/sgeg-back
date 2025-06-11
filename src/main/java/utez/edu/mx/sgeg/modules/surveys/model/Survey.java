package utez.edu.mx.sgeg.modules.surveys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.answers.model.Answer;
import utez.edu.mx.sgeg.modules.periods.model.Period;
import utez.edu.mx.sgeg.modules.questions.model.Question;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_survey")
    private long id;

    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "modification_date", nullable = false)
    private Date modificationDate;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @Column(name = "response_time", nullable = false)
    private int responseTime;

    @Column(name = "type", nullable = false)
    private boolean type;

    @ManyToOne
    @JoinColumn(name = "id_period", referencedColumnName = "id_period")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private List<Answer> answers;

}
