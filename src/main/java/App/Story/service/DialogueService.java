package App.Story.service;

import App.Story.entity.Dialogue;
import App.Story.entity.SetupQuestion;
import App.Story.repository.DialogueRepository;
import App.Story.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DialogueService
{
    @Autowired
    private DialogueRepository dialogueRepository;
    @Autowired
    private QuestionRepo questionRepo;

    public Optional<Dialogue> getDialogueById(Integer id) {
        return dialogueRepository.findById(id);
    }

    public List<SetupQuestion> getQuestions(Integer id)
    {
        List<Dialogue> dialogues = getAllDialogues(id);
        return getQuestions(dialogues);
    }

    public List<Dialogue> getAllDialogues(Integer id) {
        return dialogueRepository.findDialoguesByStoryId(id);
    }

    public List<SetupQuestion> getQuestions(List<Dialogue> dialogues)
    {
        List<SetupQuestion> questions = new ArrayList<>();
        for (Dialogue dialogue : dialogues)
        {
            var question = questionRepo.findSetupQuestionByDialogueId(dialogue.getId());
            if (question != null)
                questions.add(question);
        }
        return questions;
    }

    public List<Dialogue> buildDialogueFromQuestions(Integer id, List<SetupQuestion> userAnswers)
    {
        var dialogues = getAllDialogues(id);
        var questions = getQuestions(dialogues);

        for(SetupQuestion question : questions)
        {
            Dialogue dialogue = dialogues.stream()
                    .filter(d -> d.getId().equals(question.getDialogue().getId()))
                    .findFirst()
                    .orElse(null);

            String answer = userAnswers.stream()
                    .filter(u -> u.getId().equals(question.getId()))
                    .findFirst()
                    .get()
                    .getAnswer();

            String line = dialogue != null ? dialogue.getLine() : null;
            if(line != null)
            {
                String replacement = question.getTextToReplace();
                String defaultAnswer = question.getDefaultAnswer();

                UpdateDialogueModel udm = new UpdateDialogueModel(line,replacement,answer,defaultAnswer);

                if(!question.getTextToReplace().contains(","))
                {
                    dialogue.setLine(updateDialogueLineWithUserInput(udm));
                }else{
                    dialogue.setLine(multipleAnswerDialogue(udm));
                }
            }
        }
        return dialogues;
    }

    public String updateDialogueLineWithUserInput(UpdateDialogueModel udm)
    {
        if (udm.line.indexOf(udm.replacement) > 0) {
            String replaced;
            if (!udm.answer.isEmpty()) {
                replaced = udm.line.replaceAll(udm.replacement, udm.answer);
            } else {
                replaced = udm.line.replaceAll(udm.replacement, udm.defaultAnswer);
            }
            return replaced;
        }
        return udm.line;
    }

    public String multipleAnswerDialogue(UpdateDialogueModel udm)
    {
        String[] arrayReplacment = udm.replacement.split(",");
        String[] arrayAnswer = udm.defaultAnswer.split(",");
        String[] defaultAnswer = udm.answer.split(",");
        String replaced = "";

        for(int i = 0; i < arrayReplacment.length; i++)
        {
            UpdateDialogueModel updateDialogueModel = new UpdateDialogueModel(udm.line,arrayReplacment[i],arrayAnswer[i],defaultAnswer[i]);
            replaced = updateDialogueLineWithUserInput(updateDialogueModel);
        }

        return replaced;
    }

    private static class UpdateDialogueModel
    {
        private String line;
        private String replacement;
        private String answer;
        private String defaultAnswer;

        public UpdateDialogueModel(String line, String replacement, String answer, String defaultAnswer) {
            this.line = line;
            this.replacement = replacement;
            this.answer = answer;
            this.defaultAnswer = defaultAnswer;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getReplacement() {
            return replacement;
        }

        public void setReplacement(String replacement) {
            this.replacement = replacement;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getDefaultAnswer() {
            return defaultAnswer;
        }

        public void setDefaultAnswer(String defaultAnswer) {
            this.defaultAnswer = defaultAnswer;
        }

    }
}


