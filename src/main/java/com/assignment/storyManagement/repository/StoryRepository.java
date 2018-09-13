package com.assignment.storyManagement.repository;

import com.assignment.storyManagement.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{

}
