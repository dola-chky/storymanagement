package com.assignment.storyManagement.controller;

import com.assignment.storyManagement.model.Story;
import com.assignment.storyManagement.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
