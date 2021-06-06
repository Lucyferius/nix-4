package com.alevel.hibernate.dao;

import com.alevel.hibernate.exeption.ResourceWasNotFoundException;
import com.alevel.hibernate.model.dto.NearLesson;
import com.alevel.hibernate.model.entity.Lesson;
import com.alevel.hibernate.model.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentDAO {
    private final EntityManager entityManager;
    public StudentDAO(EntityManager en){
        this.entityManager = en;
    }
    public NearLesson getNearLesson( Long id) throws ResourceWasNotFoundException {
        Student student = entityManager.find(Student.class, id);
        if(student == null) throw new ResourceWasNotFoundException(id, Student.class);
        Long groupId = student.getGroup().getId();

        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l FROM Lesson l " +
                        "JOIN l.group g " +
                        "WHERE g.id = :groupId and l.sqlDate >= current_date " +
                        "ORDER BY l.sqlDate asc , l.sqlTime asc ",
                Lesson.class);

        query.setMaxResults(1);
        query.setParameter("groupId", groupId);
        return entityToRecord(query.getSingleResult());
    }
    private static NearLesson entityToRecord(Lesson entity) {
        return new NearLesson(
                entity.getId(),
                entity.getTeacher(),
                entity.getTopic(),
                entity.getSqlDate().toLocalDate(),
                entity.getSqlTime().toLocalTime(),
                entity.getGroup(),
                entity.getGroup().getCourse()
        );
    }

}
