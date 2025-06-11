package utez.edu.mx.sgeg.modules.answers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.kernel.JsonConverter;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;
import utez.edu.mx.sgeg.modules.users.models.UserEntity;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private long id;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "jsonb")
    private Map<Long, Long> questionAnswer;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_survey", referencedColumnName = "id_survey")
    private Survey survey;
}
