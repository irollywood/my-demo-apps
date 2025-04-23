package App.Story.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Dialogue
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String speaker;
    private String line;
    private String avatar;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="story_Id")
    private Story story;

}
