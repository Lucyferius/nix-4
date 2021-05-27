package example;

import annotation.CsvCell;
import lombok.ToString;

@ToString
public class Car {
    @CsvCell("name")
    private String name;

    @CsvCell("color")
    private String color;

    @CsvCell("year")
    private int age;

    private int averageSpeed;

    private String owner;
}
