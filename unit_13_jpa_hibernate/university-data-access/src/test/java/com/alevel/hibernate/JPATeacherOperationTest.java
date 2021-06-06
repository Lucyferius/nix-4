package com.alevel.hibernate;

import com.alevel.hibernate.dao.TeacherDAO;
import com.alevel.hibernate.exeption.ResourceWasNotFoundException;
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
        subject = new TeacherDAO(session);
    }
    @Test
    @DisplayName("find the best group")
    void testCreateResource() {
        assertThrows(ResourceWasNotFoundException.class, ()->subject.getTheBestGroupByTeacherId(1L));

        SyncTeachersAndGroups sync = new SyncTeachersAndGroups();
        sync.initLinks(session);

        Group bestGroupOfTeacher = assertDoesNotThrow(()->subject.getTheBestGroupByTeacherId(1L));
        assertNotNull(bestGroupOfTeacher);
        assertEquals(bestGroupOfTeacher.getId(), 1L);

        assertThrows(ResourceWasNotFoundException.class, ()->subject.getTheBestGroupByTeacherId(-1L));
    }
}
