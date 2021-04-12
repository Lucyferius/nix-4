package nix.alevel;


import nix.alevel.entity.Date;
import nix.alevel.entity.Format;
import nix.alevel.exception.InvalidFormatException;
import nix.alevel.util.DataConvertor;
import nix.alevel.util.FormatDesigner;
import nix.alevel.util.dataoperations.DateOperationsImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        FormatDesigner formatDesigner =new FormatDesigner();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        DateOperationsImpl dateOperations = new DateOperationsImpl();
        Format format = formatDesigner.setFormatDesign("dd/mm/yyyy");
        while (true){
            switch (bufferedReader.readLine()) {
                case "1":
                    String s = bufferedReader.readLine();
                    format = formatDesigner.setFormatDesign(s);
                    System.out.println(format.toString());
                    break;
                case "2":
                    String data = bufferedReader.readLine();
                    Date date = DataConvertor.convert(data, format);
                    System.out.println("data1 : ");
                    DataConvertor.printDate(date, format);
                    String data2 = bufferedReader.readLine();
                    Date date2 = DataConvertor.convert(data2, format);
                    System.out.println("data2 : ");
                    DataConvertor.printDate(date2, format);

                    Date res = dateOperations.addDate(date, date2);
                    DataConvertor.printDate(res, format);

                case "3":
                    s = bufferedReader.readLine();
                    format = formatDesigner.setFormatDesign(s);
                    System.out.println(format.toString());

                    data = bufferedReader.readLine();
                    date = DataConvertor.convert(data, format);
                    System.out.println("data1 : ");
                    DataConvertor.printDate(date, format);

                    s = bufferedReader.readLine();
                    format = formatDesigner.setFormatDesign(s);
                    System.out.println(format.toString());

                    data2 = bufferedReader.readLine();
                    date2 = DataConvertor.convert(data2, format);
                    System.out.println("data2 : ");
                    DataConvertor.printDate(date2, format);

                    Map<String, Long> map = dateOperations.findDifference(date, date2);
                    dateOperations.printDifference(map);

                case "4":
                    s = bufferedReader.readLine();
                    format = formatDesigner.setFormatDesign(s);
                    System.out.println(format.toString());

                    data = bufferedReader.readLine();
                    date = DataConvertor.convert(data, format);
                    System.out.println("data1 : ");
                    DataConvertor.printDate(date, format);

                    s = bufferedReader.readLine();
                    format = formatDesigner.setFormatDesign(s);
                    System.out.println(format.toString());

                    data2 = bufferedReader.readLine();
                    date2 = DataConvertor.convert(data2, format);
                    System.out.println("data2 : ");
                    DataConvertor.printDate(date2, format);

                    res = dateOperations.subtractTwoDate(date, date2);
                    DataConvertor.printDate(res,format);
            }

        }
    }
}
