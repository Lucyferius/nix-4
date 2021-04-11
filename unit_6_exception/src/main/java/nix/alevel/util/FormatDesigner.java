package nix.alevel.util;

import nix.alevel.entity.Format;
import nix.alevel.entity.Time;
import nix.alevel.exception.InvalidFormatException;


public class FormatDesigner {
    String container[] = {Format.DAY_D, Format.DAY_DD, Format.MONTH_M, Format.MONTH_MM,
            Format.MONTH_MMM, Format.YEAR_YY, Format.YEAR_YYYY};

    public Format setFormatDesign(String title){
        Format format = new Format();
        String[] date = new String[0];
        String[] dataTimeRegex = split(title);
        if (title.replaceAll("[dm/y0:\\- ]", "").length() != 0) {
            throw new InvalidFormatException("Invalid format input");
        }
        if(!title.contains("d") || !title.contains("m") || !title.contains("y"))
            throw new InvalidFormatException("Invalid format input");
        if (!title.contains("/") && !title.contains("-")) {
            if(dataTimeRegex[0].equals(Format.YEAR_YY)) format.setIsYearYY(true);
            if(dataTimeRegex[0].equals(Format.YEAR_YYYY)) format.setIsYearYYYY(true);
            return format;
        }
        if (title.contains("/")) {
            date = dataTimeRegex[0].split("/");
            format.setTagSlash(true);
        }
        if (title.contains("-")) {
            date = dataTimeRegex[0].split("-");
            format.setTagDash(true);
        }

        for (int j =0; j<date.length; j++){
            String tmp = date[j];
            switch (tmp){
                case Format.DAY_D: format.setIsDayD(true); break;
                case Format.DAY_DD: format.setIsDayDD(true); break;
                case Format.MONTH_M: format.setIsMonthM(true); break;
                case Format.MONTH_MM: format.setIsMonthMM(true); break;
                case Format.MONTH_MMM: format.setIsMonthMMM(true); break;
                case Format.YEAR_YY: format.setIsYearYY(true); break;
                case Format.YEAR_YYYY: format.setIsYearYYYY(true); break;
                default: throw new InvalidFormatException("Invalid format input");
            }
        }
        if(dataTimeRegex.length ==2) {
            format.setIsTime(true);
            if(checkFullTime(dataTimeRegex[1]) == 3) {
                format.setIsFullTime(true);
                format.setIsHours(true);
                format.setIsSecondsMinutes(true);
            }
            if(checkFullTime(dataTimeRegex[1]) == 2) {
                format.setIsFullTime(false);
                format.setIsSecondsMinutes(true);
            }
            if(checkFullTime(dataTimeRegex[1]) == 4) {
                format.setIsFullTime(true);
                format.setIsHours(true);
                format.setIsSecondsMinutes(true);
                format.setIsMilliseconds(true);
            }
        }
        if(date.length == 3)format.setOrder(date);
        if(date.length ==2) {
            for (int j =0; j<date.length; j++){
                format.setOrder(date[j], j);
            }
        }

        return format;
    }
    public int checkFullTime(String str) throws IllegalArgumentException, InvalidFormatException{
        String[] time = str.split(":");
        if(time.length == 3) return 3;
        if(time.length == 2) return 2;
        if(time.length == 4) return 4;
        throw new InvalidFormatException("Invalid time format input");
    }

    public String[] split(String title ){
        String newString = title.replaceAll(" ", "REX");
        return newString.split("REX");
    }
}
