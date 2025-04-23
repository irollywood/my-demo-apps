package App.Story.controller;


import App.Story.entity.SetupQuestion;
import App.Story.model.QuestionForm;
import App.Story.service.DialogueService;
import App.Story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private DialogueService dialogueService;

    @GetMapping("/index")
    public String splashScreen() {
        return "index";
    }

    @GetMapping("/stories")
    public String getAllStories(Model model) {
        model.addAttribute("stories", storyService.getAllStories());
        return "stories";
    }

    @GetMapping("story/questions/{id}")
    public String getDialogueQuestion(@PathVariable Integer id, Model model)
    {
        List<SetupQuestion> questions = dialogueService.getQuestions(id);

        model.addAttribute("questionform", new QuestionForm(questions));
        return "questions";
    }
}
