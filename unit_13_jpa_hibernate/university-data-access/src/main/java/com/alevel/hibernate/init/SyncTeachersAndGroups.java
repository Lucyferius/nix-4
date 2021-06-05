package com.alevel.hibernate.init;

import com.alevel.hibernate.model.entity.Group;
import com.alevel.hibernate.model.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SyncTeachersAndGroups {
    public void initLinks(EntityManager en){
        try {
            en.getTransaction().begin();

            TypedQuery<Teacher> queryTeachers = en.createQuery("from Teacher", Teacher.class);
            List<Teacher> teachers = queryTeachers.getResultList();
            TypedQuery<Group> queryGroups = en.createQuery("from Group", Group.class);
            List<Group> groups = queryGroups.getResultList();

            int size = teachers.size();
            if(teachers.size() > groups.size())
                size = groups.size();

            for (int i = 0; i < size; i++){
                teachers.get(i).getGroups().add(groups.get(i));
                groups.get(i).getTeachers().add(teachers.get(i));
                if(i!=0) {
                    teachers.get(i).getGroups().add(groups.get(i - 1));
                    groups.get(i-1).getTeachers().add(teachers.get(i));
                }
            }

            /*
            The example of creating cortege in java class

            Course course1 = new Course();
            course1.setCourseName("Java");
            en.persist(course1);
            */

            en.getTransaction().commit();
        }catch (Exception e) {
            en.getTransaction().rollback();
        }
    }
}
