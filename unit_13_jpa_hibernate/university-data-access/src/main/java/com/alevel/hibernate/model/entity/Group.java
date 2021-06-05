package com.alevel.hibernate.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "groups")
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "group")
    private Set<Lesson> lessons = new HashSet<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Student> getStudents() {
        return students;
    }
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
