package nix.alevel.entity;

import lombok.Getter;
import nix.alevel.exception.InvalidFormatException;

@Getter
public class Date{
    private int century;
    private int year = 2021;
    private int month = 1;
    private int days = 1;
    private Time time = new Time("00", "00", "00", "00");
    //private BigInteger dataInDays = BigInteger.valueOf(1);
    private boolean isLeap = false;
    public Date(){}

    public void setYears(int years){
        if(years<0) throw new InvalidFormatException("Invalid years input");
        this.year = years;
        isLeap = checkYearIsLeap(year);
        century = centuryFromYear(year);
    }
    public void setMonth(int month){
        if(month<0 || month >12) throw new InvalidFormatException("Invalid month input");
        this.month = month;
    }
    public void setDays(int days) {
        if (days < 0) throw new InvalidFormatException("Invalid days input");
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
                || month == 12) {
            if (days > 31) throw new InvalidFormatException("Invalid days input");
            this.days = days;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (days > 30) throw new InvalidFormatException("Invalid days input");
            this.days = days;
        }
        if (month == 2) {
            if (isLeap) if (days > 29) throw new InvalidFormatException("Invalid days input");
            if (!isLeap) if (days > 28) throw new InvalidFormatException("Invalid days input, year isn`t leap");
            this.days = days;
        }
    }
    public void setTime(Time time){
        this.time = time;
    }

    public boolean getIsLeap(){
        return isLeap;
    }

    public String toString(){
        return days+"/"+month+"/"+year+" "+time.toString();
    }

    private int centuryFromYear(int year) {
        if (year % 100 == 0)  return year / 100;
        else return (year / 100) + 1;
    }
    private boolean checkYearIsLeap(int year){
        if(!(year%4 == 0)) return false;
        else {
            if(!(year%100 == 0)) return true;
            else return (year%400 == 0);
        }
    }

}
