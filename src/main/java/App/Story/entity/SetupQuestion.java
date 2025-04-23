package App.Story.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class SetupQuestion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String question;

    @NonNull
    private String textToReplace;

    private String defaultAnswer;
    @NonNull
    private String answer;

    @OneToOne
    @JoinColumn(name="dialogue_Id")
    private Dialogue dialogue;

    public SetupQuestion() {

    }
}
