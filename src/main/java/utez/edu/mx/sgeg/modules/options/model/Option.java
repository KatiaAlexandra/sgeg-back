package utez.edu.mx.sgeg.modules.options.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.questions.model.Question;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_question", referencedColumnName = "id_question")
    private Question question;

    @Column(name = "option_text", nullable = false, length = 250)
    private String optionText;

    @ManyToMany
    @JoinTable(
            name = "option_related_question",
            joinColumns = @JoinColumn(name = "id_option"),
            inverseJoinColumns = @JoinColumn(name = "id_question")
    )
    List<Question> relatedQuestions;
}
