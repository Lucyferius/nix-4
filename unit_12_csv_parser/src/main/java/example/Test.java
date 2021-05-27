package example;

import model.CsvTable;
import util.CsvMapper;
import util.CsvParser;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String fileNameCars = "cars.csv";
        String fileNameStudents = "students.csv";

        System.out.println("CARS");
        CsvTable csvTableCars = CsvParser.parse(fileNameCars);
        List<Car> cars = CsvMapper.format(csvTableCars, Car.class);
        cars.forEach(System.out::println);

        System.out.println("\nSTUDENTS");

        CsvTable csvTableStudents = CsvParser.parse(fileNameStudents);
        List<Student> students = CsvMapper.format(csvTableStudents, Student.class);
        students.forEach(System.out::println);
        System.out.println("\nGet row with index 1: " +csvTableStudents.getRow(1));
        System.out.println("Get element with row 1 and column 1: " +csvTableStudents.getValue(1,1));
        System.out.println("Get element with row 1 and column course: " +csvTableStudents.getValue(1,"course"));
        System.out.println("Get header: " + csvTableStudents.getHeader());
        System.out.println("Get list of column age: "+ csvTableStudents.getAllColumn(2));
    }
}
