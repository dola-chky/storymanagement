package com.assignment.storyManagement.controller;
import com.assignment.storyManagement.model.Story;
import com.assignment.storyManagement.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StoryController {
    @Autowired
    StoryRepository storyRepository;

    // Get all stories
    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    //Create a new story
    @RequestMapping(value = "/story/", method = RequestMethod.POST)
    public Story createStory(@RequestBody Story story) {
        System.out.println(story.getTitle());
        System.out.println(story.getStoryBody());
        System.out.println(story.getPublishedDate());
        return storyRepository.save(story);
    }

    // Get a single story
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.GET)
    public Story getStoryByID(@PathVariable(value = "storyId") Long storyId) {
        System.out.println(storyId);
        return storyRepository.findById(storyId)
                .orElse(null);
    }

    //Get a single story in plain text format
    @RequestMapping(value = "story/text/{storyId}", method = RequestMethod.GET)
    @ResponseBody
    public String getStoryAsText(HttpServletResponse response, @PathVariable(value = "storyId") Long storyId) {
        Story story = storyRepository.findById(storyId).orElse(null);
        String str;
        if(story != null){
            str = "Title:" + story.getTitle()+","
                    + "\n"+"Body:" + story.getStoryBody() + ","
                    + "\n"+"Published Date:"+ story.getPublishedDate();
            response.setContentType("text/plain");
        }else{
            str = "";
        }
        response.setCharacterEncoding("UTF-8");
        return str;
    }

    //Get a single story in json format
    @RequestMapping(value = "story/json/{storyId}", method = RequestMethod.GET)
    public Story getStoryAsJson(@PathVariable(value = "storyId") Long storyId) {
        Story story = storyRepository.findById(storyId).orElse(null);
        return story;
    }
}
