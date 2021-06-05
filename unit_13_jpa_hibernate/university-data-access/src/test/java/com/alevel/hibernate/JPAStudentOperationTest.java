package com.alevel.hibernate;

import com.alevel.hibernate.dao.StudentDAO;
import com.alevel.hibernate.dao.TeacherDAO;
import com.alevel.hibernate.init.SyncTeachersAndGroups;
import com.alevel.hibernate.model.dto.NearLesson;
import com.alevel.hibernate.model.entity.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JPAStudentOperationTest extends ProgrammingCoursesTest{
    private StudentDAO subject;

    @BeforeEach
    void setUp() {
        subject = new StudentDAO(entityManager);
    }
    @Test
    @DisplayName("find the nearest lesson")
    void testCreateResource() {

        NearLesson nearLesson = subject.getNearLesson(1L);
        assertNotNull(nearLesson);

        assertThrows(RuntimeException.class, ()->subject.getNearLesson(-1L));
    }
}
