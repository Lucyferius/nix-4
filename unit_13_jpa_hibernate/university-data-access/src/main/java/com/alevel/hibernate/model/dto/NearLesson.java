package com.alevel.hibernate.model.dto;

import com.alevel.hibernate.model.entity.Course;
import com.alevel.hibernate.model.entity.Group;
import com.alevel.hibernate.model.entity.Teacher;
import com.alevel.hibernate.model.entity.Topic;

import java.time.LocalDate;
import java.time.LocalTime;

public class NearLesson {
    private Long id;
    private Teacher teacher;
    private Topic topic;
    private LocalDate date;
    private LocalTime time;
    private Group group;
    private Course course;

    public NearLesson(Long id, Teacher teacher, Topic topic, LocalDate date, LocalTime time, Group group, Course course) {
        this.id = id;
        this.teacher = teacher;
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.group = group;
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NearLesson{" +
                "id=" + id +
                ",\nteacher=" + teacher.getFirstName() + " " + teacher.getLastName() +
                ",\ntopic=" + topic.getTopicName() +
                ",\ndate=" + date +
                ",\ntime=" + time +
                ",\ngroup=" + group.getGroupName() +
                ",\ncourse=" + course.getCourseName() +
                '}';
    }
}
