package com.assignment.storyManagement.model;

import com.assignment.storyManagement.Utils.JsonDateDeserializer;
import com.assignment.storyManagement.Utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "story")
@EntityListeners(AuditingEntityListener.class)
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long storyId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String storyBody;

    @Column(nullable = false)
    private Date publishedDate;

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryBody() {
        return storyBody;
    }

    public void setStoryBody(String storyBody) {
        this.storyBody = storyBody;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getPublishedDate() {
        return publishedDate;
    }

    @JsonDeserialize(using= JsonDateDeserializer.class)
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Story{" +
                "storyId=" + storyId +
                ", title='" + title + '\'' +
                ", storyBody='" + storyBody + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
