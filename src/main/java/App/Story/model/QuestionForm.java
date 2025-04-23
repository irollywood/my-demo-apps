package App.Story.model;

import App.Story.entity.SetupQuestion;
import java.util.List;

public class QuestionForm {
    private List<SetupQuestion> questions;

    public QuestionForm(List<SetupQuestion> questions) {
        this.questions = questions;
    }

    public List<SetupQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SetupQuestion> questions) {
        this.questions = questions;
    }
}
