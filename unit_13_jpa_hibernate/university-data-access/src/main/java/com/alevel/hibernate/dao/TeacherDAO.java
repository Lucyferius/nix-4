package com.alevel.hibernate.dao;

import com.alevel.hibernate.exeption.ResourceWasNotFoundException;
import com.alevel.hibernate.model.entity.Group;
import com.alevel.hibernate.model.entity.Teacher;
import com.alevel.hibernate.util.TheBestGroupFinder;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private final EntityManager entityManager;
    public TeacherDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public Group getTheBestGroupByTeacherId(Long id) throws  ResourceWasNotFoundException{

/*
        String sqlQuery = "SELECT students.group_id, array_agg(marks.mark ORDER BY marks.mark ASC) FROM students " +
                " INNER JOIN marks ON marks.id = students.mark_id " +
                " WHERE students.group_id IN (SELECT id FROM groups INNER JOIN groups_teachers ON groups_teachers.group_id = groups.id" +
                " WHERE groups_teachers.teacher_id=:id) GROUP BY students.group_id";
*/
        var bestGroupFinder = new TheBestGroupFinder();
        double bestMark = 0;
        Group bestGroup = null;

        Teacher teacher = entityManager.find(Teacher.class, id);
        if(teacher == null) throw new RuntimeException("The is no teacher in database with id: " + id);

        List<Group> groupSet = new ArrayList<>(teacher.getGroups());

        if(groupSet.isEmpty())
            throw new RuntimeException("Teacher should have at least 1 group, teacher: " + teacher.getFirstName() + " " + teacher.getLastName());
        for (Group group: groupSet){
            if(group.getStudents().isEmpty())
                throw new RuntimeException("Group couldn`t be empty group: " + group.getGroupName());
            double medianMarkInGroup =  bestGroupFinder.calculateMedianMark(new ArrayList<>(group.getStudents()));
            if(medianMarkInGroup > bestMark) {
                bestMark = medianMarkInGroup;
                bestGroup = group;
            }
        }
        return bestGroup;
    }
}
