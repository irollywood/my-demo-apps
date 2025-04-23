package App.Story.repository;

import App.Story.entity.SetupQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo  extends JpaRepository<SetupQuestion, Integer>
{

    SetupQuestion findSetupQuestionByDialogueId(Long id);
}
