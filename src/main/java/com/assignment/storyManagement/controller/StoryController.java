package com.assignment.storyManagement.controller;
import com.assignment.storyManagement.model.Story;
import com.assignment.storyManagement.repository.StoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.assignment.storyManagement.Utils.JsonUtil.jsonStringConverter;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

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

    //Create a new story from json request bosy
    @RequestMapping(value = "/story/", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public Story createStoryByJson(@RequestBody Story story) {
        System.out.println(story.getTitle());
        System.out.println(story.getStoryBody());
        System.out.println(story.getPublishedDate());
        return storyRepository.save(story);
    }

    //Create a new story from plain text request body
    @RequestMapping(value = "/story/", method = RequestMethod.POST,consumes = TEXT_PLAIN_VALUE)
    public Story createStoryByText(@RequestBody String storyStr) throws IOException{
        String jsonStr = jsonStringConverter(storyStr);
        Story newStory = null;

        ObjectMapper mapper = new ObjectMapper();
        Story story = mapper.readValue(jsonStr, Story.class);
        System.out.println(story);

        if(story != null){
            newStory = storyRepository.save(story);
        }
        return newStory;

    }

    //Get a single story in json format
    @RequestMapping(value = "story/{storyId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Story getStoryAsJson(@PathVariable(value = "storyId") Long storyId) {
        Story story = storyRepository.findById(storyId).orElse(null);
        return story;
    }

    //Get a single story in plain text format
    @RequestMapping(value = "story/{storyId}", method = RequestMethod.GET, produces = TEXT_PLAIN_VALUE)
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

    // Update a story by json
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.PUT, consumes = {APPLICATION_JSON_VALUE})
    public Story updateStoryByJson(@PathVariable(value = "storyId") Long storyId,
                             @RequestBody Story story){

        Story oldStory = storyRepository.findById(storyId)
                .orElse(null);

        if(oldStory != null) {
            oldStory.setTitle(story.getTitle());
            oldStory.setStoryBody(story.getStoryBody());
            oldStory.setPublishedDate(story.getPublishedDate());
            Story updatedStory = storyRepository.save(oldStory);
            return updatedStory;
        }else{
            return null;
        }

    }

    // Update a story by plain text
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.PUT, consumes = {TEXT_PLAIN_VALUE})
    public Story updateStoryByText(@PathVariable(value = "storyId") Long storyId,
                             @RequestBody String storyStr) throws IOException{

        Story updatedStory;
        String jsonStr = jsonStringConverter(storyStr);

        ObjectMapper mapper = new ObjectMapper();
        Story newStory = mapper.readValue(jsonStr, Story.class);
        System.out.println(newStory);

        Story oldStory = storyRepository.findById(newStory.getStoryId()).orElse(null);
        if(oldStory != null){
            oldStory.setTitle(newStory.getTitle());
            oldStory.setStoryBody(newStory.getStoryBody());
            oldStory.setPublishedDate(newStory.getPublishedDate());
            updatedStory = storyRepository.save(oldStory);
            return updatedStory;
        }else{
            return null;
        }

    }


    // Delete a story
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Story> deleteStory(@PathVariable(value = "storyId") Long storyId) {
        Story story = storyRepository.findById(storyId)
                .orElse(null);

        if(story != null){
            storyRepository.delete(story);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
