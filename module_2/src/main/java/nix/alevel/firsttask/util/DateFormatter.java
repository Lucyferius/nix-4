package nix.alevel.firsttask.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {
    private static final DateFormat FIRST_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private static final DateFormat SECOND_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateFormat THIRD_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
    private static final DateFormat RESULT_FORMAT = new SimpleDateFormat("yyyyMMdd");

    // Паттерны для проверки логики даты, ибо SimpleDateFormat воспринимает дату 2020/13/1 допустимой
    // и переводит ее в 2021/1/1
    public static final String FIRST_PERMITTED_FORMAT = "^([0-9][0-9][0-9][0-9])/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])$"; //  yyyy/MM/dd
    public static final String SECOND_PERMITTED_FORMAT = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9][0-9][0-9][0-9])$";//  dd/MM/yyyy
    public static final String THIRD_PERMITTED_FORMAT = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-([0-9][0-9][0-9][0-9])$"; //  MM-dd-yyyy

    public static List<String> findPermittedDates(List<String> dates){
        List<String> permittedDates = new ArrayList<>();
        for(int i = 0; i<dates.size(); i++){
            if(isValid(dates.get(i), FIRST_PERMITTED_FORMAT)){
                permittedDates.add(parseDateResult(FIRST_FORMAT, dates.get(i)));
            }
            else if(isValid(dates.get(i), SECOND_PERMITTED_FORMAT)){
                permittedDates.add(parseDateResult(SECOND_FORMAT, dates.get(i)));
            }
            else if(isValid(dates.get(i), THIRD_PERMITTED_FORMAT)){
                permittedDates.add(parseDateResult(THIRD_FORMAT, dates.get(i)));
            }
        }
        return permittedDates;
    }

    private static String parseDateResult(DateFormat format, String dateInput){
        String dateResult = null;
        try {
            Date date = format.parse(dateInput);
            dateResult = RESULT_FORMAT.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateResult;
    }
    public static boolean isValid(String date, String datePattern) {
        Pattern pattern = Pattern.compile(datePattern);
        boolean result = false;
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            result = true;
        }
        return result;
    }
}
