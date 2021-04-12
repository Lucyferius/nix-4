package nix.alevel.util.dataoperations;

import nix.alevel.entity.Date;
import nix.alevel.entity.Time;
import nix.alevel.exception.InvalidFormatException;
import nix.alevel.util.dataoperations.service.DataOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateOperationsImpl implements DataOperations {
    private static final int daysInYear = 365;
    private static final int hoursInDay = 24;
    private static final int monthInYear = 12;
    private static final int minutesInHour = 60;
    private static final int secInMin = 60;
    private static final long milliSecInSec = 1000L;
    private static final long milliSecInMinute = 6000L;
    private static final long milliSecInHour = 3600000L;
    private static final long milliSecInDay = 86400000L;
    private static final List<Integer> daysInMonthList = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }
    private long century(long year) {
        if (year % 100 == 0) return year / 100;
        else return (year / 100) + 1;
    }
    @Override
    public long countOfMSInDate(Date date) {
        int days = 0, daysInMonth;
        for (int i = 0; i < date.getYear(); i++){
            if (i != 0 && isLeapYear(i)) days += 366;
            else days += 365;
        }
        for (int i = 1; i < date.getMonth(); i++){
            if (isLeapYear(date.getYear()) && date.getMonth() == 2)
                days += 29;
            else {
                daysInMonth = daysInMonthList.get(i);
                days += daysInMonth;
            }
        }
        days += date.getDays();
        return milliSecInDay * days + milliSecInHour * date.getTime().getHours()
                + milliSecInMinute * date.getTime().getMinutes() + milliSecInSec * date.getTime().getSeconds() + date.getTime().getMilliseconds();
    }
    @Override
    public Map<String, Long> findDifference(Date firstDate, Date secondDate){
        if(firstDate == null || secondDate == null)  throw new InvalidFormatException("Invalid date input");

        long firstDateInMs = countOfMSInDate(firstDate);
        long secondDateInMs = countOfMSInDate(secondDate);
        long differenceInMs = 0;
        if(firstDateInMs>=secondDateInMs)  differenceInMs = firstDateInMs - secondDateInMs;
        else if (firstDateInMs<secondDateInMs)  differenceInMs = secondDateInMs - firstDateInMs;

        Map<String, Long> map = new HashMap<>();
        long years = (long) Math.floor(differenceInMs / (milliSecInDay* daysInYear));
        map.put("year", years);
        map.put("century",century(years));
        map.put("month", (long)Math.floor(differenceInMs / (milliSecInSec * minutesInHour * secInMin * hoursInDay * daysInYear*30)));
        map.put("day", (long)Math.floor(differenceInMs / milliSecInDay));
        map.put("hours", (long)Math.floor((differenceInMs / milliSecInHour)));
        map.put("minutes", (long) Math.floor((differenceInMs / milliSecInMinute)));
        map.put("seconds", (long) Math.floor((differenceInMs / milliSecInSec)));
        map.put("milliseconds", differenceInMs);
        return map;
    }
    @Override
    public void printDifference(Map <String, Long> map){
        System.out.println("Difference:   "+ map.get("century") + " centuries; " + map.get("year") + " years; "
                + map.get("month") + " month; " + map.get("day") + " days; " + map.get("hours") + " h; "
                + map.get("minutes") + " min; " + map.get("seconds") + " s; " +  map.get("milliseconds") + " ms;");
    }
    @Override
    public Date addDate(Date dateFirst, Date dateSecond) {
        Date newDate = new Date();
        Time newDateTime = new Time();
        int millisec = (int)dateFirst.getTime().getMilliseconds() + (int)dateSecond.getTime().getMilliseconds();
        newDateTime.setMilliseconds((int) (millisec % milliSecInSec));

        int seconds = dateFirst.getTime().getSeconds() + dateSecond.getTime().getSeconds() + (int) ( millisec / milliSecInSec);
        newDateTime.setSeconds(seconds % secInMin);

        int minutes = dateFirst.getTime().getMinutes() + dateSecond.getTime().getMinutes() + seconds / secInMin;
        newDateTime.setMinutes(minutes % minutesInHour);

        int hours = dateFirst.getTime().getHours() + dateSecond.getTime().getHours() + minutes / minutesInHour;
        newDateTime.setHours(hours % hoursInDay);
        int days = dateFirst.getDays() + dateSecond.getDays() + hours / hoursInDay;

        int years = dateFirst.getYear() + dateSecond.getYear();
        int month = dateFirst.getMonth() + dateSecond.getMonth();
        if (month > monthInYear) {
            int addYears = month / monthInYear;

            for (int i = 1; i <= addYears; i++) {
                if (isLeapYear(years + 1)) days -= 1;
            }
            years += addYears;
            month = month % monthInYear;
        }
        if (days <= daysInMonthList.get(month - 1)) {
            newDate.setDays(days);
            newDate.setMonth(month);
            newDate.setYears(years);
        } else {
            while (days > daysInMonthList.get(month - 1)) {
                if (month == 2 && isLeapYear(years)) days -= 1;
                days -= daysInMonthList.get(month - 1);
                month += 1;
                if (month > monthInYear) {
                    years += 1;
                    month = 1;
                }
            }
            newDate.setDays(days);
            newDate.setMonth(month);
            newDate.setYears(years);
        }
        newDate.setTime(newDateTime);
        System.out.println("time: " +newDateTime.toString());
        return newDate;
    }
    @Override
    public List<Date> sortAsc(List<Date> arr) {
        Date tmpDate;
        for (int i = 0; i < arr.size()-1; i++) {
            for (int j = 1; j < arr.size() - i; j++) {
                if (countOfMSInDate(arr.get(j)) < countOfMSInDate(arr.get(j - 1))) {
                    tmpDate = arr.get(j-1);
                    arr.set(j-1,arr.get(j));
                    arr.set(j, tmpDate);
                }
            }
        }
        return arr;
    }
    @Override
    public List<Date> sortDes(List<Date> arr) {
        Date tmpDate;
        for(int i = 0; i < arr.size()-1; ++i) {
            for(int j = 1; j < arr.size()-i; j++) {
                if(countOfMSInDate(arr.get(j)) > countOfMSInDate(arr.get(j-1))) {
                    tmpDate = arr.get(j-1);
                    arr.set(j-1,arr.get(j));
                    arr.set(j, tmpDate);
                }
            }
        }
        return arr;
    }
    @Override
    public Date subtractTwoDate(Date dateFirst, Date dateSecond){

        if(dateFirst == null || dateSecond == null) throw new InvalidFormatException("Invalid date input");

        long dateFirstInMS = countOfMSInDate(dateFirst);
        long dateSecondInMS = countOfMSInDate(dateSecond);

        if(dateFirstInMS< dateSecondInMS) throw new InvalidFormatException("Invalid date input, first date should be bigger");

        Date newDate = new Date();
        Time time = new Time();

        long ms = dateFirst.getTime().getMilliseconds() - dateSecond.getTime().getMilliseconds();
        long sec = dateFirst.getTime().getSeconds() - dateSecond.getTime().getSeconds() + ms/milliSecInSec ;
        long min = dateFirst.getTime().getMinutes() - dateSecond.getTime().getMinutes() + sec/secInMin;
        long hours = dateFirst.getTime().getHours() - dateSecond.getTime().getHours() + min/minutesInHour;
        long days = dateFirst.getDays() - dateSecond.getDays() + hours/hoursInDay;
        int years = dateFirst.getYear() - dateSecond.getYear();
        int month = dateFirst.getMonth() - dateSecond.getMonth();


        if(hours<0) days-=1;
        if(days > 0){
            newDate.setDays((int)days);
            newDate.setMonth(month);
            newDate.setYears(years);
        } else {
            while(days <= 0){
                if(month ==2 && isLeapYear(years)) days+=1;
                days += daysInMonthList.get(month - 1);
                month -=1;
                if(month <= 0) {
                    years-=1;
                    month =12;
                }
            }
            newDate.setDays((int)days);
            newDate.setMonth(month);
            newDate.setYears(years);

        }
        time.setMilliseconds(Math.abs(ms % milliSecInSec) == 0 ? 0:(milliSecInSec + ms) % milliSecInSec);
        if (ms<0) sec-=1;
        time.setSeconds(Math.abs(sec % secInMin) == 0? 0: (int) ((secInMin + sec) % secInMin));
        if (sec<0) min-=1;
        time.setMinutes(Math.abs(min % minutesInHour) ==0 ? 0: (int) ((minutesInHour + min) % minutesInHour));
        if(min<0)hours-=1;
        time.setHours(Math.abs(hours % hoursInDay) == 0 ? 0: (int) ((hoursInDay + hours) % hoursInDay));

        newDate.setTime(time);
        return newDate;
    }
}
