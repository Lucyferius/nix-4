package com.alevel.hibernate;

import com.alevel.hibernate.dao.StudentDAO;
import com.alevel.hibernate.exeption.ResourceWasNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JPAStudentOperationTest extends ProgrammingCoursesTest{
    private StudentDAO subject;

    @BeforeEach
    void setUp() {
        subject = new StudentDAO(session);
    }
    @Test
    @DisplayName("find the nearest lesson")
    void testCreateResource() {

        assertDoesNotThrow(()-> subject.getNearLesson(1L));

        assertThrows(ResourceWasNotFoundException.class, ()->subject.getNearLesson(-1L));
    }
}
