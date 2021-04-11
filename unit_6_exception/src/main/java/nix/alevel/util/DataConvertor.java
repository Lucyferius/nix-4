package nix.alevel.util;

import nix.alevel.entity.Date;
import nix.alevel.entity.Format;
import nix.alevel.entity.Month;
import nix.alevel.entity.Time;
import nix.alevel.exception.InvalidFormatException;

import java.util.Locale;


public class DataConvertor {
    private static final FormatDesigner formatDesigner = new FormatDesigner();

    public static Time convertStringToTime(String str) throws IllegalArgumentException, InvalidFormatException{
        Time resultTime = new Time();
        int hour = 0, min = 0, sec = 0, mills = 0;
        String[] time = str.split(":");
        if(time.length ==4) {
            hour= Integer.parseInt(time[0]);
            min = Integer.parseInt(time[1]);
            sec = Integer.parseInt(time[2]);
            mills = Integer.parseInt(time[3]);
        }
        if(time.length ==3) {
            min= Integer.parseInt(time[0]);
            sec = Integer.parseInt(time[1]);
            mills = Integer.parseInt(time[2]);
        }
        if(time.length == 2){
            min = Integer.parseInt(time[0]);
            sec = Integer.parseInt(time[1]);
        }

        resultTime.setHours(hour);
        resultTime.setMinutes(min);
        resultTime.setSeconds(sec);
        resultTime.setMilliseconds(mills);

        return resultTime;
    }

    public static Date convert(String title, Format format) throws  IllegalArgumentException {
        Date resultDate = new Date();
        String[] dataTimeRegex = formatDesigner.split(title);
        String[] date = {"", "", ""};
        if (format.getIsTime() && dataTimeRegex.length == 1)
            throw new InvalidFormatException("Invalid time input");
        else if (format.getIsFullTime() && format.getIsMilliseconds() && !(formatDesigner.checkFullTime(dataTimeRegex[1])==4))
            throw new InvalidFormatException("Invalid time input");
        else if (format.getIsFullTime() && !format.getIsMilliseconds() && !(formatDesigner.checkFullTime(dataTimeRegex[1]) ==3))
            throw new InvalidFormatException("Invalid time input");

        if (dataTimeRegex[0].contains(format.getTag())) {
            String [] order = dataTimeRegex[0].split(format.getTag());
            if(order.length>3) throw new InvalidFormatException("Invalid data input, look at format");
            for (int i =0; i<order.length; i++) {
                if(order[i].isEmpty()) continue;
                date[i] = order[i];
            }

        } else {
            resultDate.setYears(Integer.parseInt(dataTimeRegex[0]));
            return resultDate;
        }

        //первым должен установиться год, чтобы понимать високосный или нет
        for (int i = format.getOrder().length - 1; i >= 0; i--) {
            String tmp = format.getOrder()[i];
            if (tmp.equals(Format.YEAR_YYYY)) {
                if(!date[i].isEmpty())
                resultDate.setYears(Integer.parseInt(date[i]));
                break;
            }
            if (tmp.equals(Format.YEAR_YY)) {
                if(!date[i].isEmpty()) {
                    int year = Integer.parseInt(date[i]);
                    if (year >= 0 && year <10)
                        if(date[i].charAt(0)!='0') throw new InvalidFormatException("Invalid input, please try again");

                    if (year >= 0 && year <= 21) resultDate.setYears(
                            Integer.parseInt("20".concat(date[i])));
                    if (year > 21 && year <= 99) resultDate.setYears(
                            Integer.parseInt("19".concat(date[i])));
                }
            }
        }

        for(int i = format.getOrder().length-1; i>=0; i--) {
            String tmp = format.getOrder()[i];
            if (tmp.equals(Format.MONTH_M)) {
                if (!date[i].isEmpty()) resultDate.setMonth(Integer.parseInt(date[i]));
                break;
            }
            if (tmp.equals(Format.MONTH_MMM)) {
                if (!date[i].isEmpty()) {
                    Month month = Month.byName(date[i]);
                    if(month==null)  throw new InvalidFormatException("Invalid input, please try again");
                    resultDate.setMonth(month.getMonthNumber());
                }
            }
            if (tmp.equals(Format.MONTH_MM)) {
                if (!date[i].isEmpty()) {
                    int m = Integer.parseInt(date[i]);
                    if(m<10) if (date[i].charAt(0) != '0') throw new InvalidFormatException("Invalid input, please try again");
                    resultDate.setMonth(Integer.parseInt(date[i]));
                    break;
                }
            }
        }

        for(int i = format.getOrder().length-1; i>=0; i--) {
            String tmp = format.getOrder()[i];
            if(tmp.equals(Format.DAY_D)){
                if(!date[i].isEmpty()) resultDate.setDays(Integer.parseInt(date[i])); break;}
            if(tmp.equals(Format.DAY_DD)){
                if(!date[i].isEmpty()) {
                    int d = Integer.parseInt(date[i]);
                    if(d<10)
                        if (date[i].charAt(0) != '0') throw new InvalidFormatException("Invalid input, please try again");
                    resultDate.setDays(d);
                    break;
                }
            }
        }

        if(format.getIsTime()){
            if(dataTimeRegex.length ==2)
                resultDate.setTime(convertStringToTime(dataTimeRegex[1]));
            else throw new InvalidFormatException("Invalid input, please try again");
        }
        return resultDate;
    }

    public static void printDate(Date date, Format format){
        String[] order = {"", "", ""};
        Time time ;
        String[] timeOrder = {"", "", "", ""};
        for (int i =0; i < format.getOrder().length; i++){
            String tmp = format.getOrder()[i];
            switch (tmp){
                case Format.DAY_D: order[i] = String.valueOf(date.getDays()); break;
                case Format.DAY_DD:
                    if(date.getDays()<10) order[i] = "0".concat(String.valueOf(date.getDays()));
                    else order[i] = String.valueOf(date.getDays());
                    break;
                case Format.MONTH_M: order[i] = String.valueOf(date.getMonth()); break;
                case Format.MONTH_MM:
                    if(date.getMonth()<10) order[i] = "0".concat(String.valueOf(date.getMonth()));
                    else order[i] = String.valueOf(date.getMonth());
                    break;
                case Format.MONTH_MMM:
                    Month month = Month.byOrdinal(date.getMonth());
                    if(month!=null) order[i] = month.getName();
                    else throw new InvalidFormatException("Month not found");
                    break;
                case Format.YEAR_YY:
                    char[] year = String.valueOf(date.getYear()).toCharArray();
                    order[i] = String.valueOf(year[2]).concat( String.valueOf(year[3]));
                    break;
                case Format.YEAR_YYYY: order[i] = String.valueOf(date.getYear()); break;

            }
        }
        time = date.getTime();
        StringBuilder stringBuilder = new StringBuilder();
        if(time.getHours()<10) timeOrder[0] = "0"+time.getHours();
            else timeOrder[0] = String.valueOf(time.getHours());
        if(time.getMinutes()<10) timeOrder[1] = "0"+time.getMinutes();
            else timeOrder[1] = String.valueOf(time.getMinutes());
        if(time.getSeconds()<10) timeOrder[2] = "0"+time.getSeconds();
            else timeOrder[2] = String.valueOf(time.getSeconds());
        if(time.getMilliseconds()<10) timeOrder[3] = "000"+time.getMilliseconds();
            else if(time.getMilliseconds()<100) timeOrder[3] = "00"+time.getMilliseconds();
            else if(time.getMilliseconds()<1000) timeOrder[3] = "0"+time.getMilliseconds();
            else timeOrder[3] = String.valueOf(time.getMilliseconds());

            stringBuilder.append(" ");
        if(format.getIsHours())
            stringBuilder.append(timeOrder[0]).append(":");
        if(format.getIsSecondsMinutes())
            stringBuilder.append(timeOrder[1]).append(":").append(timeOrder[2]);
        if(format.getIsMilliseconds())
            stringBuilder.append(":").append(timeOrder[3]);

        if(format.getMonthFormat().equals(Format.MONTH_MMM))System.out.println(order[0]+" "+order[1] + " " + order[2] + " " +stringBuilder.toString());
        else System.out.println(order[0]+format.getTag()+order[1] + format.getTag() + order[2] + " " +stringBuilder.toString());
        System.out.println("\tmemo: " +
                "h = " +time.getHours()+
                "; m = " +time.getMinutes()+
                "; s = " +time.getSeconds()+
                "; ms = " +time.getMilliseconds());

    }
}
