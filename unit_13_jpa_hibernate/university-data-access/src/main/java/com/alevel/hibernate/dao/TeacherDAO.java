package com.alevel.hibernate.dao;

import com.alevel.hibernate.exeption.ResourceWasNotFoundException;
import com.alevel.hibernate.model.entity.Group;
import com.alevel.hibernate.model.entity.Student;
import com.alevel.hibernate.model.entity.Teacher;
import com.alevel.hibernate.util.TheBestGroupFinder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDAO {
    private final EntityManager entityManager;
    public TeacherDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public Group getTheBestGroupByTeacherId(Long id) throws  ResourceWasNotFoundException{

        var bestGroupFinder = new TheBestGroupFinder();
        double bestMark = 0;
        Group bestGroup = null;

        Teacher teacher = entityManager.find(Teacher.class, id);
        if(teacher == null) throw new ResourceWasNotFoundException(id, Teacher.class);

        TypedQuery<Student> query2 = entityManager.createQuery("SELECT s" +
                        " FROM Student s" +
                        " LEFT JOIN  s.group.teachers t " +
                        " JOIN s.mark m " +
                        " WHERE t.id = :id " +
                        " ORDER BY s.group.id asc , m.mark asc ",
                Student.class);

        query2.setParameter("id", id);
        List<Student> students = query2.getResultList();
        for(Student s: students) {
            System.out.println(s.toString());
        }

        if(students.isEmpty())
            throw new ResourceWasNotFoundException("Teacher should have at least 1 group, teacher: " + teacher.getFirstName() + " " + teacher.getLastName());
        for (int i =0; i<students.size(); i++){
            long groupId = students.get(i).getGroup().getId();
            List<Student> studentFromOneGroup = students.stream().filter(student -> student.getGroup().getId()==groupId).collect(Collectors.toList());
            double medianMarkInGroup =  bestGroupFinder.calculateMedianMark(studentFromOneGroup);
            if(medianMarkInGroup > bestMark) {
                bestMark = medianMarkInGroup;
                bestGroup = students.get(i).getGroup();
            }
            i+=studentFromOneGroup.size();
        }
        return bestGroup;
    }
}
