package nix.alevel.controller;

import lombok.SneakyThrows;
import nix.alevel.entity.Date;
import nix.alevel.entity.Format;
import nix.alevel.exception.InvalidFormatException;
import nix.alevel.util.DataConvertor;
import nix.alevel.util.FormatDesigner;
import nix.alevel.util.dataoperations.DateOperationsImpl;
import nix.alevel.util.dataoperations.service.DataOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainController {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final FormatDesigner formatDesigner = new FormatDesigner();
    private static final FormatChangeController formatChangeController = new FormatChangeController();
    private static final DateInputController dateInputController = new DateInputController();
    private static final DataOperations dateOperations = new DateOperationsImpl();
    @SneakyThrows
    public static void run(){
        Format format = FormatChangeController.setDefaultFormat();
        Date dateFirst, dateSecond;
        System.out.println("Default input format: " + format.toString());
        while (true){
            System.out.println("1. Find difference between two dates\n" +
                    "2. Add dates\n" +
                    "3. Subtract dates\n" +
                    "4. Sort dates");
            switch (bufferedReader.readLine()){
                case "1":{
                    try {
                        format = formatChangeController.changeFormat(format);
                    }catch (InvalidFormatException e){
                        System.err.println(e.getMessage());
                    }
                    try {
                        dateFirst = dateInputController.enterDate(format);
                        try {
                            format = formatChangeController.changeFormat(format);
                        }catch (InvalidFormatException e){
                            System.err.println(e.getMessage());
                        }
                        dateSecond = dateInputController.enterDate(format);
                        Map<String, Long> map =  dateOperations.findDifference(dateFirst, dateSecond);
                        System.out.println("----result of comparing----");
                        dateOperations.printDifference(map);
                    }catch (InvalidFormatException | IndexOutOfBoundsException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case "2":{
                    try {
                        format = formatChangeController.changeFormat(format);
                    }catch (InvalidFormatException e){
                        System.err.println(e.getMessage());
                    }
                    try {
                        dateFirst = dateInputController.enterDate(format);
                        try {
                            format = formatChangeController.changeFormat(format);
                        }catch (InvalidFormatException e){
                            System.err.println(e.getMessage());
                        }
                        dateSecond = dateInputController.enterDate(format);
                        Date res = dateOperations.addDate(dateFirst, dateSecond);
                        System.out.println("----result of adding dates----");
                        DataConvertor.printDate(res, format);
                    }catch (InvalidFormatException | IndexOutOfBoundsException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case "3":{
                    try {
                        format = formatChangeController.changeFormat(format);
                    }catch (InvalidFormatException e){
                        System.err.println(e.getMessage());
                    }
                    try {
                        dateFirst = dateInputController.enterDate(format);
                        try {
                            format = formatChangeController.changeFormat(format);
                        }catch (InvalidFormatException e){
                            System.err.println(e.getMessage());
                        }
                        dateSecond = dateInputController.enterDate(format);
                        Date res = dateOperations.subtractTwoDate(dateFirst, dateSecond);
                        System.out.println("----result of subtracting dates----");
                        DataConvertor.printDate(res, format);
                    }catch (InvalidFormatException | IndexOutOfBoundsException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case "4":{
                    List<Date> dates = new ArrayList<>();
                    List<Date> result;
                    System.out.println("Begin of entering dates...");
                    boolean check =true, cycle = true;
                    while (check){
                        try {
                            format = formatChangeController.changeFormat(format);
                        }catch (InvalidFormatException e){
                            System.err.println(e.getMessage());
                        }
                        try {
                            dateFirst = dateInputController.enterDate(format);
                            dates.add(dateFirst);
                        }catch (InvalidFormatException | IndexOutOfBoundsException e){
                            System.err.println(e.getMessage());
                        }
                        System.out.println("Do you want stop? If YES - 0, NO - press any other button");
                        System.out.print("Your choice: ");
                        if(bufferedReader.readLine().equals("0")) check =false;
                        else continue;
                    }
                    while (cycle){
                        System.out.println("1.Sort asc (\\|/)" +
                                "\n2.Sort desc ( /|\\)" +
                                "\n0.Go to main loop");
                        switch (bufferedReader.readLine()){
                            case "1":
                                result = dateOperations.sortAsc(dates);
                                printList(result, format);
                                break;
                            case "2":
                                result = dateOperations.sortDes(dates);
                                printList(result, format);
                                break;
                            case "0": cycle = false;
                        }
                    }

                    break;
                }
                default:
                    System.err.println("Invalid case input. Try again");
            }
        }
    }
    private static void printList(List<Date> dates, Format format){
        for (Date d: dates) {
            System.out.println("{");
            DataConvertor.printDate(d, format);
            System.out.println("}");
        }
    }

}
