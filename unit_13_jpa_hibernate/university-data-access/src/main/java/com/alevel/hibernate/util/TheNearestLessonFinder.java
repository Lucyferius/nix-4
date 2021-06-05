package com.alevel.hibernate.util;

import com.alevel.hibernate.model.dto.NearLesson;
import com.alevel.hibernate.model.entity.Lesson;
import com.alevel.hibernate.model.entity.Student;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TheNearestLessonFinder {
    public NearLesson findNearestLessonForStudent(Student student){
        Set<Lesson> lessons = student.getGroup().getLessons();
        return lessons.stream().min(new LessonComparator()).map(TheNearestLessonFinder::entityToRecord).get();
    }
    private static class LessonComparator implements Comparator<Lesson> {

        @Override
        public int compare(Lesson o1, Lesson o2) {
            if(o1.getSqlDate().compareTo(o2.getSqlDate())>0){
                return 1;
            }else if(o1.getSqlDate().compareTo(o2.getSqlDate())<0){
                return -1;
            }else if(o1.getSqlDate().compareTo(o2.getSqlDate())==0)
                return o1.getSqlTime().compareTo(o2.getSqlTime());
            throw new RuntimeException("Invalid to compare");
        }
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
