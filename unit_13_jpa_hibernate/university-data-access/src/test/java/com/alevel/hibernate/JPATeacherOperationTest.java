package com.alevel.hibernate;

import com.alevel.hibernate.dao.TeacherDAO;
import com.alevel.hibernate.init.SyncTeachersAndGroups;
import com.alevel.hibernate.model.entity.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JPATeacherOperationTest extends ProgrammingCoursesTest{
    private TeacherDAO subject;

    @BeforeEach
    void setUp() {
        subject = new TeacherDAO(entityManager);
    }
    @Test
    @DisplayName("find the best group")
    void testCreateResource() {
        assertThrows(RuntimeException.class, ()->subject.getTheBestGroupByTeacherId(1L));

        SyncTeachersAndGroups sync = new SyncTeachersAndGroups();
        sync.initLinks(entityManager);

        Group bestGroupOfTeacher = subject.getTheBestGroupByTeacherId(1L);
        assertNotNull(bestGroupOfTeacher);
        assertEquals(bestGroupOfTeacher.getId(), 1L);

        assertThrows(RuntimeException.class, ()->subject.getTheBestGroupByTeacherId(-1L));
    }
}
