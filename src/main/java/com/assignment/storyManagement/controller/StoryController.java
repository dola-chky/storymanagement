package com.assignment.storyManagement.controller;
import com.assignment.storyManagement.model.Story;
import com.assignment.storyManagement.repository.StoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Get stories by title
    @RequestMapping(value = "/stories/search/{title}", method = RequestMethod.GET)
    public List<Story> getAllStoriesByTitle(@PathVariable(value = "title") String title) {
        return storyRepository.findByTitle(title);
    }

    //Create a new story from json request body
    @RequestMapping(value = "/story/", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStoryByJson(@RequestBody Story story) {
        String createdBy = getLoggedInUser();
        story.setCreatedBy(createdBy);
        try{
            Story newStory = storyRepository.save(story);
            return new ResponseEntity<Story>(HttpStatus.CREATED);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Create a new story from plain text request body
    @RequestMapping(value = "/story/", method = RequestMethod.POST,consumes = TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createStoryByText(@RequestBody String storyStr) throws IOException{
        String jsonStr = jsonStringConverter(storyStr);
        Story newStory = null;

        String createdBy = getLoggedInUser();

        ObjectMapper mapper = new ObjectMapper();
        Story story = mapper.readValue(jsonStr, Story.class);

        if(story != null){
            story.setCreatedBy(createdBy);
            try{
                newStory = storyRepository.save(story);
                return new ResponseEntity<Story>(HttpStatus.CREATED);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
                    + "\n"+"Published Date:"+ story.getPublishedDate() + ","
                    + "\n"+"Created By:"+ story.getCreatedBy();
            response.setContentType("text/plain");
        }else{
            str = null;
        }
        response.setCharacterEncoding("UTF-8");
        return str;
    }

    // Update a story by json
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStoryByJson(@PathVariable(value = "storyId") Long storyId,
                                   @RequestBody Story story){

        String createdBy = getLoggedInUser();

        Story oldStory = storyRepository.findById(storyId)
                .orElse(null);

        if(oldStory != null) {
            if(createdBy.equals(oldStory.getCreatedBy())){
                oldStory.setTitle(story.getTitle());
                oldStory.setStoryBody(story.getStoryBody());
                oldStory.setPublishedDate(story.getPublishedDate());
                Story updatedStory = storyRepository.save(oldStory);
                return new ResponseEntity<Story>(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }

        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    // Update a story by plain text
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.PUT, consumes = TEXT_PLAIN_VALUE)
    public ResponseEntity<?> updateStoryByText(@PathVariable(value = "storyId") Long storyId,
                             @RequestBody String storyStr) throws IOException{

        Story updatedStory;
        String jsonStr = jsonStringConverter(storyStr);

        String createdBy = getLoggedInUser();

        ObjectMapper mapper = new ObjectMapper();
        Story newStory = mapper.readValue(jsonStr, Story.class);
        newStory.setStoryId(storyId);

        Story oldStory = storyRepository.findById(newStory.getStoryId()).orElse(null);
        if(oldStory != null){
            if(createdBy.equals(oldStory.getCreatedBy())) {
                oldStory.setTitle(newStory.getTitle());
                oldStory.setStoryBody(newStory.getStoryBody());
                oldStory.setPublishedDate(newStory.getPublishedDate());
                updatedStory = storyRepository.save(oldStory);
                return new ResponseEntity<Story>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    // Delete a story
    @RequestMapping(value = "/story/{storyId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStory(@PathVariable(value = "storyId") Long storyId) {
        Story story = storyRepository.findById(storyId)
                .orElse(null);

        String createdBy = getLoggedInUser();

        if(story != null){
            if(createdBy.equals(story.getCreatedBy())) {
                storyRepository.delete(story);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userName;
    }
}
