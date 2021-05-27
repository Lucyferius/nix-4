package example;

import model.CsvTable;
import util.CsvMapper;
import util.CsvParser;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        String fileNameCars = "/cars.csv";
        String fileNameStudents = "/students.csv";
        URL urlCars = Test.class.getResource(fileNameCars);
        URL urlStud = Test.class.getResource(fileNameStudents);
        File fileCars = null, fileStudents = null;
        try {
            fileCars = new File(Objects.requireNonNull(urlCars).toURI());
            fileStudents = new File(Objects.requireNonNull(urlStud).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("CARS");
        CsvTable csvTableCars = CsvParser.parse(fileCars);
        List<Car> cars = CsvMapper.format(csvTableCars, Car.class);
        cars.forEach(System.out::println);

        System.out.println("\nSTUDENTS");

        CsvTable csvTableStudents = CsvParser.parse(fileStudents);
        List<Student> students = CsvMapper.format(csvTableStudents, Student.class);
        students.forEach(System.out::println);
        System.out.println("\nGet row with index 1: " +csvTableStudents.getRow(1));
        System.out.println("Get element with row 1 and column 1: " +csvTableStudents.getValue(1,1));
        System.out.println("Get element with row 1 and column course: " +csvTableStudents.getValue(1,"course"));
        System.out.println("Get header: " + csvTableStudents.getHeader());
        System.out.println("Get list of column age: "+ csvTableStudents.getAllColumn(2));
    }
}
