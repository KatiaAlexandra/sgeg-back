package utez.edu.mx.sgeg.modules.questions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.categories.model.Category;
import utez.edu.mx.sgeg.modules.options.model.Option;
import utez.edu.mx.sgeg.modules.sections.model.Section;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_section", referencedColumnName = "id_section")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "id_survey", referencedColumnName = "id_survey")
    private Survey survey;

    @Column(name = "question_text", nullable = false, length = 250)
    private String questionText;

    @Column(name = "mandatory", nullable = false)
    private boolean mandatory;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category category;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Option> options;

    @ManyToMany(mappedBy = "relatedQuestions")
    private List<Option> relatedOptions;
}
