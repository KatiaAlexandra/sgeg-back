package utez.edu.mx.sgeg.modules.sections.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.sgeg.modules.questions.model.Question;
import utez.edu.mx.sgeg.modules.surveys.model.Survey;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_survey", referencedColumnName = "id_survey")
    private Survey survey;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @OneToMany(mappedBy = "section")
    @JsonIgnore
    private List<Question> questions;
}
