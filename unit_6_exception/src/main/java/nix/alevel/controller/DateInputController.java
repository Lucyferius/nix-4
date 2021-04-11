package nix.alevel.controller;

import nix.alevel.entity.Date;
import nix.alevel.entity.Format;
import nix.alevel.exception.InvalidFormatException;
import nix.alevel.util.DataConvertor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DateInputController {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public Date enterDate(Format format){
        boolean t = true;
        while (t){
            System.out.println("Enter the date strictly with current format: "+ format.toString());
            System.out.println("Enter date: ");
            try {
                String date = bufferedReader.readLine();
                try {
                    Date result = DataConvertor.convert(date,format);
                    t =false;
                    System.out.println("your date: ");
                    DataConvertor.printDate(result, format);
                    return result;
                }catch (InvalidFormatException e){
                    System.err.println(e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        throw new InvalidFormatException("Invalid input");
    }
}
