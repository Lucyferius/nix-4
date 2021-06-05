package com.alevel.hibernate.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", nullable = false, unique = true)
    private String topicName;

    @OneToMany(mappedBy = "topic")
    private Set<Lesson> lessons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

}
