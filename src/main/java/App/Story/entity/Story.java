package App.Story.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Story
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;

    @ToString.Exclude
    @OneToMany(mappedBy = "story")
    private List<Dialogue> dialogue;

}
