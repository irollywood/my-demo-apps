package App.Story.controller;

import App.Story.entity.Dialogue;
import App.Story.model.QuestionForm;
import App.Story.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("dialogue")
public class DialogueController {

    @Autowired
    private DialogueService dialogueService;


    @GetMapping("/{id}")
    public String getDialogue(@PathVariable Integer id, Model model) {
        List<Dialogue> dialogues = dialogueService.getAllDialogues(id);
        if (!dialogues.isEmpty()) {
            model.addAttribute("dialogue", dialogues);
            return "dialogue";
        }
        return "redirect:/";
    }

    @PostMapping("/build")
    public String getDialogueFromQuestions(@ModelAttribute QuestionForm questionform , Model model)
    {
        Integer storyId = Math.toIntExact(questionform.getQuestions().get(0).getDialogue().getStory().getId());
        List<Dialogue> dialogues = dialogueService.buildDialogueFromQuestions(storyId,questionform.getQuestions());
        if (!dialogues.isEmpty()) {
            model.addAttribute("dialogue", dialogues);
            return "dialogue";
        }
        return "redirect:/";
    }
}
