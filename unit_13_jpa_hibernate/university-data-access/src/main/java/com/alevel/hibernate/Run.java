package com.alevel.hibernate;


import com.alevel.hibernate.dao.StudentDAO;
import com.alevel.hibernate.dao.TeacherDAO;
import com.alevel.hibernate.init.SyncTeachersAndGroups;
import com.alevel.hibernate.model.dto.NearLesson;
import com.alevel.hibernate.model.entity.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;

public class Run {
    //private static final Logger logger = LogManager.getLogger(Run.class.getName());
    private final static Logger logger = LoggerFactory.getLogger(Run.class);
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            logger.info("Creating database and start linking teachers and groups");
            var init = new SyncTeachersAndGroups();
            init.initLinks(entityManager);
            logger.info("Create links between teachers and groups");

            TeacherDAO teacherDAO = new TeacherDAO(entityManager);
            StudentDAO studentDAO = new StudentDAO(entityManager);
            Long id = 2L;
            try {
                logger.info("Start finding the nearest lesson group for student with id: " + id);
                NearLesson nearLesson = studentDAO.getNearLesson(id);
                System.out.println("near lesson = \n" + nearLesson.toString());
                logger.info("The nearest lesson group for student with id: " + id +" is\n" + nearLesson);
            }catch (RuntimeException e){
                logger.error(e.getMessage());
            }

            try {
                logger.info("Start finding the best group of teacher with id: " + id);
                Group bestGroupOfTeacher = teacherDAO.getTheBestGroupByTeacherId(id);
                System.out.println("group = " + bestGroupOfTeacher.getGroupName());
                logger.info("The best group of teacher with id: " + id +" is " + bestGroupOfTeacher.getGroupName());
            }catch (RuntimeException e){
                logger.error(e.getMessage());
            }
            entityManager.close();
        }
    }
}