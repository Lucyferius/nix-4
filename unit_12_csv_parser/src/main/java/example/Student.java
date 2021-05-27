package example;

import annotation.CsvCell;
import lombok.ToString;

@ToString
public class Student {
    @CsvCell("name")
    private String name;

    @CsvCell("mark")
    private double assessment;

    @CsvCell("course")
    private String course;

    @CsvCell("age")
    private int age ;

    private String town;
}
