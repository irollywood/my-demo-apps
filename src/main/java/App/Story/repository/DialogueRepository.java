package App.Story.repository;

import App.Story.entity.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogueRepository extends JpaRepository<Dialogue, Integer> {

    List<Dialogue> findDialoguesByStoryId(Integer id);
}
