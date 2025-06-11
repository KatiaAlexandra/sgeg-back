package utez.edu.mx.sgeg.modules.sent_surveys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sent_survey")
public class SentSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sent_survey")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_survey", referencedColumnName = "id_survey")
    private Survey survey;

    @Column(name = "sent_date", nullable = false)
    private Date sentDate;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;
}
