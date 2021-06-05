package com.alevel.hibernate.util;

import com.alevel.hibernate.model.entity.Student;

import java.util.Comparator;
import java.util.List;

public class TheBestGroupFinder {
    public double calculateMedianMark(List<Student> students){
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getMark().getMark().compareTo(o2.getMark().getMark());
            }
        };
        students.sort(comparator);
        if(students.size()%2!=0) return students.get(students.size()/2).getMark().getMark();
        else return (students.get(students.size()/2-1).getMark().getMark()
                + students.get(students.size()/2 +1).getMark().getMark())/2;
    }
}
