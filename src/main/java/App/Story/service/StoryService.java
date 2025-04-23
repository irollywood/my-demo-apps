package App.Story.service;


import App.Story.entity.Story;
import App.Story.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public List<Story> getAllStories()
    {
        return storyRepository.findAll();
    }

    public Optional<Story> getStoryById(Integer id) {
        return storyRepository.findById(id);
    }
}
