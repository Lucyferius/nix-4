package com.alevel.hibernate.dao;

import com.alevel.hibernate.model.dto.NearLesson;
import com.alevel.hibernate.model.entity.Student;
import com.alevel.hibernate.util.TheNearestLessonFinder;

import javax.persistence.EntityManager;

public class StudentDAO {
    private final EntityManager entityManager;
    public StudentDAO(EntityManager en){
        this.entityManager = en;
    }
    public NearLesson getNearLesson( Long id){
        Student student = entityManager.find(Student.class, id);
        if(student == null) throw  new RuntimeException("The is no student in database with id:" + id);
        var finder = new TheNearestLessonFinder();
        return finder.findNearestLessonForStudent(student);
    }

}
