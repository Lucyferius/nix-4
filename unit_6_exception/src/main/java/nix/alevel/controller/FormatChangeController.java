package nix.alevel.controller;

import nix.alevel.entity.Format;
import nix.alevel.exception.InvalidFormatException;
import nix.alevel.util.FormatDesigner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FormatChangeController {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final FormatDesigner formatDesigner = new FormatDesigner();
    public Format changeFormat(Format format) throws IOException {
        boolean t = true;
        while (t){
            System.out.println("Do you want to change format: YES - 1, NO - 0");
            switch (bufferedReader.readLine()){
                case "0":{
                    t = false;
                    return format;
                }
                case "1":{
                    try {
                        Format newFormat = enterFormat();
                        t= false;
                        return newFormat;
                    }catch (IOException | InvalidFormatException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
            }

        }
        throw new InvalidFormatException("Invalid input");
    }
    private static Format enterFormat() throws IOException {
        System.out.println("Enter format using symbols d,dd,m,mm,mmm,yy,yyy,'/','-',':',00");
        String newFormat = bufferedReader.readLine();
        return formatDesigner.setFormatDesign(newFormat);
    }
    public static Format setDefaultFormat(){
        Format format = new Format();
        format.setIsDayD(true);
        format.setIsMonthM(true);
        format.setIsYearYYYY(true);
        format.setTagSlash(true);
        format.setIsTime(true);
        format.setIsHours(true);
        format.setIsFullTime(true);
        format.setIsSecondsMinutes(true);
        format.setIsMilliseconds(true);
        return format;
    }
}
