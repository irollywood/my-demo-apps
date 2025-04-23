package App.Story.repository;

import App.Story.entity.Dialogue;
import App.Story.entity.SetupQuestion;
import App.Story.entity.Story;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoryDataLoader implements CommandLineRunner
{
    private final StoryRepository storyRepository;
    private final DialogueRepository dialogueRepository;
    private final QuestionRepo questionRepository;
    
    public StoryDataLoader(StoryRepository storyRepository, DialogueRepository dialogueRepository, QuestionRepo questionRepository)
    {
        this.storyRepository = storyRepository;
        this.dialogueRepository = dialogueRepository;
        this.questionRepository = questionRepository;
    }
    
    @Override
    public  void run(String... args)
    {
        if(storyRepository.count() == 0)
        {
            createStories();
        }
    }
    
    private void createStories()
    {
        teamProjectStory();
        createStoryMomChild();
        concertStory();
    }

    private void createStoryMomChild()
    {
        String avatar1 = "/images/avatar_woman_neutral.png";
        String avatar2 = "/images/avatar_boy_neutral.png";
        String imgsrc = "/images/you-have-my-child.png";

        Story story = new Story();
        story.setTitle("My Crazy Test Story");
        story.setDescription("A true story about parent child relationship.");
        story.setCategory("Drama");
        story.setImage(imgsrc);
        storyRepository.save(story);

        List<Dialogue> dialogues = new ArrayList<>();
        List<SetupQuestion> questions = new ArrayList<>();
//NEXT
        Dialogue d1 = new Dialogue();
        d1.setStory(story);
        d1.setLine("Okay, <name> let's talk. We know what you have been doing. We found it in the room.");
        d1.setSpeaker("User");
        d1.setAvatar(avatar1);
        dialogues.add(d1);

        SetupQuestion question1 = new SetupQuestion();
        question1.setQuestion("Enter the name of a person");
        question1.setDefaultAnswer("Richy Rich");
        question1.setTextToReplace("<name>");
        question1.setDialogue(d1);
        questions.add(question1);

//NEXT
        Dialogue d2 = new Dialogue();
        d2.setStory(story);
        d2.setSpeaker("NPC");
        d2.setLine("Mother, what are you talking about? Found wha...");
        d2.setAvatar(avatar2);
        dialogues.add(d2);
//NEXT
        Dialogue d3 = new Dialogue();
        d3.setStory(story);
        d3.setSpeaker("User");
        d3.setAvatar(avatar1);
        d3.setLine("The <noun> !! we found the <noun>! Why do you have this?! We warned you about this so many times.");
        dialogues.add(d3);

        SetupQuestion question2 = new SetupQuestion();
        question2.setQuestion("Enter an object, something you want to keep a secret");
        question2.setDefaultAnswer("drugs");
        question2.setTextToReplace("<noun>");
        question2.setDialogue(d3);
        questions.add(question2);
//NEXT DIALOGUE
        Dialogue d4 = new Dialogue();
        d4.setStory(story);
        d4.setSpeaker("NPC");
        d4.setAvatar(avatar2);
        d4.setLine("I got it from my job. They had different kinds. I like the <adjective> ones.");
        dialogues.add(d4);

        SetupQuestion sq3 = new SetupQuestion();
        sq3.setQuestion("Enter an adjective");
        sq3.setDefaultAnswer("red");
        sq3.setTextToReplace("<adjective>");
        sq3.setDialogue(d4);
        questions.add(sq3);
//NEXT DIALOGUE
        Dialogue d5 = new Dialogue();
        d5.setStory(story);
        d5.setSpeaker("User");
        d5.setAvatar("/images/avatar_woman_disgusted.png");
        d5.setLine("Wow! I knew that <job> was not a good place to work.");
        dialogues.add(d5);

        SetupQuestion sq4 = new SetupQuestion();
        sq4.setQuestion("Enter the name of a place to work at");
        sq4.setDefaultAnswer("Burger King");
        sq4.setTextToReplace("<job>");
        sq4.setDialogue(d5);
        questions.add(sq4);
//NEXT
        Dialogue d6 = new Dialogue();
        d6.setStory(story);
        d6.setSpeaker("NPC");
        d6.setAvatar(avatar2);
        d6.setLine("Well mom, all I can say is <phrase>");
        dialogues.add(d6);

        SetupQuestion sq5 = new SetupQuestion();
        sq5.setQuestion("Enter a phrase");
        sq5.setDefaultAnswer("Put a sock in it");
        sq5.setTextToReplace("<phrase>");
        sq5.setDialogue(d6);
        questions.add(sq5);
//NEXT
        Dialogue d7 = new Dialogue();
        d7.setStory(story);
        d7.setSpeaker("User");
        d7.setAvatar("/images/avatar_woman_shocked.png");
        d7.setLine("What is that supposed to mean?!");
        dialogues.add(d7);
//NEXT
        Dialogue d8 = new Dialogue();
        d8.setStory(story);
        d8.setSpeaker("NPC");
        d8.setAvatar(avatar2);
        d8.setLine("Mom, don't act like you not from the south side of <city>. You know what it means.");
        dialogues.add(d8);

        SetupQuestion sq6 = new SetupQuestion();
        sq6.setQuestion("Enter a city");
        sq6.setDefaultAnswer("Detroit");
        sq6.setTextToReplace("<city>");
        sq6.setDialogue(d8);
        questions.add(sq6);
//NEXT
        Dialogue d9 = new Dialogue();
        d9.setStory(story);
        d9.setSpeaker("User");
        d9.setAvatar("/images/avatar_woman_disgusted.png");
        d9.setLine("Chile,...I almost picked this <object> up and smacked you with it. ");
        dialogues.add(d9);
        SetupQuestion sq7 = new SetupQuestion();
        sq7.setQuestion("Enter a random object under 3 lbs");
        sq7.setDefaultAnswer("book");
        sq7.setTextToReplace("<object>");
        sq7.setDialogue(d9);
        questions.add(sq7);

        dialogueRepository.saveAll(dialogues);
        questionRepository.saveAll(questions);

    }

    private void concertStory()
    {
        String imgsrc = "/images/concert-story.png";

        Story story1 = new Story();
        story1.setTitle("My Crazy Test Story");
        story1.setDescription("Coming soon...");
        story1.setCategory("Drama");
        story1.setImage(imgsrc);
        storyRepository.save(story1);
    }

    private  void teamProjectStory()
    {
        String avatar1 = "/images/001_wh_male_beard_face1.png";
        String avatar2 = "/images/002_wh_male_face1.png";
        String avatar3 = "/images/avatar_man_neutral_glasses.png";
        String imgsrc = "/images/team-project.png";
        String Speaker1 = "NPC";
        String Speaker2 = "User";

        Story story = new Story();
        story.setTitle("Group Project");
        story.setDescription("This is supposed to be a team effort.");
        story.setCategory("Drama");
        story.setImage(imgsrc);
        storyRepository.save(story);

        List<Dialogue> dialogues = new ArrayList<>();
        List<SetupQuestion> questions = new ArrayList<>();

        Dialogue d1 = new Dialogue();
        d1.setStory(story);
        d1.setSpeaker(Speaker1);
        d1.setAvatar(avatar1);
        d1.setLine("Alright team I rented this space for us to work on our project. I know that it use to be <place>, but I got a good deal.");
        dialogues.add(d1);

        SetupQuestion sq1 = new SetupQuestion();
        sq1.setQuestion("A place of business that no longer exists");
        sq1.setDefaultAnswer("Radio Shack");
        sq1.setTextToReplace("<place>");
        sq1.setDialogue(d1);
        questions.add(sq1);

        //NEXT
        Dialogue d2 = new Dialogue();
        d2.setStory(story);
        d2.setSpeaker(Speaker2);
        d2.setAvatar(avatar2);
        d2.setLine("Yeah I remember this place before it shutdown. This is where I met <female>.");
        dialogues.add(d2);

        SetupQuestion sq2 = new SetupQuestion();
        sq2.setQuestion("A female celebrity");
        sq2.setDefaultAnswer("Oprah");
        sq2.setTextToReplace("<female>");
        sq2.setDialogue(d2);
        questions.add(sq2);

        //NEXT
        Dialogue d3 = new Dialogue();
        d3.setStory(story);
        d3.setSpeaker(Speaker1);
        d3.setAvatar(avatar3);
        d3.setLine("Oh wow <female>! When was the last time you talked to her?");
        dialogues.add(d3);

        SetupQuestion sq3 = new SetupQuestion();
        sq3.setQuestion("A female celebrity (same as previous answer)");
        sq3.setDefaultAnswer("Oprah");
        sq3.setTextToReplace("<female>");
        sq3.setDialogue(d3);
        questions.add(sq3);

        //Next
        Dialogue d4 = new Dialogue();
        d4.setStory(story);
        d4.setSpeaker(Speaker2);
        d4.setAvatar(avatar2);
        d4.setLine("We haven't talked in  <timeperiod>.");
        dialogues.add(d4);

        SetupQuestion sq4 = new SetupQuestion();
        sq4.setQuestion("Fill in the blank. I have not seen them in ____________.");
        sq4.setDefaultAnswer("in a couple of years");
        sq4.setTextToReplace("<timeperiod>");
        sq4.setDialogue(d4);
        questions.add(sq4);

        //next
        Dialogue d5 = new Dialogue();
        d5.setStory(story);
        d5.setSpeaker(Speaker1);
        d5.setAvatar(avatar1);
        d5.setLine("Okay Team let's not get distracted. We were all told what materials to bring. Did everyone bring their items?");
        dialogues.add(d5);

        Dialogue d6 = new Dialogue();
        d6.setStory(story);
        d6.setSpeaker(Speaker2);
        d6.setAvatar(avatar2);
        d6.setLine("What exactly are we trying to build? Is it a <objectToBuild>?");
        dialogues.add(d6);

        SetupQuestion sq6 = new SetupQuestion();
        sq6.setQuestion("Imagine something you can build");
        sq6.setDefaultAnswer("time machine");
        sq6.setTextToReplace("<objectToBuild>");
        sq6.setDialogue(d6);
        questions.add(sq6);

        Dialogue d7 = new Dialogue();
        d7.setStory(story);
        d7.setSpeaker(Speaker1);
        d7.setAvatar(avatar1);
        d7.setLine("That is correct! We have <percentage> % of it completed so far.");
        dialogues.add(d7);

        SetupQuestion sq7 = new SetupQuestion();
        sq7.setQuestion("Enter a number between 1 and 100");
        sq7.setDefaultAnswer("72");
        sq7.setTextToReplace("<percentage>");
        sq7.setDialogue(d7);
        questions.add(sq7);

        Dialogue d8 = new Dialogue();
        d8.setStory(story);
        d8.setSpeaker(Speaker2);
        d8.setAvatar(avatar2);
        d8.setLine("I brought the <parts> . This is what connects all of the parts together.");
        dialogues.add(d8);
        SetupQuestion sq8 = new SetupQuestion();
        sq8.setQuestion("Name an object thats part of something you can build");
        sq8.setDefaultAnswer("conduit system");
        sq8.setTextToReplace("<parts>");
        sq8.setDialogue(d8);
        questions.add(sq8);

        Dialogue d9 = new Dialogue();
        d9.setStory(story);
        d9.setSpeaker(Speaker1);
        d9.setAvatar(avatar3);
        d9.setLine("I have the <part>  and the instructions to put it together.");
        dialogues.add(d9);
        SetupQuestion sq9 = new SetupQuestion();
        sq9.setQuestion("Name an object thats part of something you can build");
        sq9.setDefaultAnswer("capacitor");
        sq9.setTextToReplace("<part>");
        sq9.setDialogue(d9);
        questions.add(sq9);

        Dialogue d10 = new Dialogue();
        d10.setStory(story);
        d10.setSpeaker(Speaker2);
        d10.setAvatar(avatar1);
        d10.setLine("We are still missing something...wait <name> is still not here. Let me call them now.");
        dialogues.add(d10);
        SetupQuestion sq10 = new SetupQuestion();
        sq10.setQuestion("A person name");
        sq10.setDefaultAnswer("Jim");
        sq10.setTextToReplace("<name>");
        sq10.setDialogue(d10);
        questions.add(sq10);

        Dialogue d11 = new Dialogue();
        d11.setStory(story);
        d11.setSpeaker(Speaker2);
        d11.setAvatar(avatar1);
        String str = "Hey! Are you still coming to the meeting?...Well why not?....Oh sorry to hear that...Okay Goodbye";
        str += "Hey everyone bad news. They are not able to come, because \"<reason>\" ";
        d11.setLine(str);
        dialogues.add(d11);
        SetupQuestion sq11 = new SetupQuestion();
        sq11.setQuestion("Fill in the blank. Not able to come because ____(an excuse,or emergency)___");
        sq11.setDefaultAnswer("my dog was hit by a car");
        sq11.setTextToReplace("<reason>");
        sq11.setDialogue(d11);
        questions.add(sq11);

        Dialogue d12 = new Dialogue();
        d12.setStory(story);
        d12.setSpeaker(Speaker1);
        d12.setAvatar("/images/002_wh_male_face5.png");
        d12.setLine("I'm on Instagram Live watching them at a friends birthday party...dancing in the background!");
        dialogues.add(d12);

        Dialogue d13 = new Dialogue();
        d13.setStory(story);
        d13.setSpeaker(Speaker2);
        d13.setAvatar("/images/avatar_man_confused.png");
        d13.setLine("... that's messed up.");
        dialogues.add(d13);

        //END
        dialogueRepository.saveAll(dialogues);
        questionRepository.saveAll(questions);

    }
}
