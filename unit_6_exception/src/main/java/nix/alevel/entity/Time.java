package nix.alevel.entity;

import nix.alevel.exception.InvalidFormatException;

public class Time {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private long milliseconds = 0;

    public Time(String seconds, String minutes, String hours, String milliseconds) {
        this.hours = Integer.parseInt(hours);
        this.minutes = Integer.parseInt(minutes);
        this.seconds = Integer.parseInt(seconds);
        this.milliseconds = Integer.parseInt(milliseconds);
        if (this.hours > 24 || this.minutes > 60 || this.seconds > 60 ||
                this.hours < 0 || this.minutes < 0 || this.seconds < 0 ||
                this.milliseconds<0 || this.milliseconds>1000) {
            throw new InvalidFormatException("Invalid data format");
        }
    }
    public Time(){}
    public int getHours(){
        return hours;
    }
    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public long getMilliseconds(){
        return milliseconds;
    }
    public int getFullMinutes(){
        return this.hours * 60 + this.minutes;
    }

    public int getFullSeconds(){
        return this.hours * 60 * 60 + this.minutes * 60 + this.seconds;
    }

    public long getFullMilliseconds(){
        return this.hours * 3600000L + this.minutes * 60000L + this.seconds * 1000L + milliseconds;
    }

    public void setHours(int hours){
        if(hours<0 || hours>24) throw new InvalidFormatException("Invalid hours input");
        this.hours = hours;
    }
    public void setMinutes(int minutes){
        if(minutes<0 || minutes>60) throw new InvalidFormatException("Invalid minutes input");
        this.minutes = minutes;
    }
    public void setSeconds(int seconds){
        if(seconds<0 || seconds>60) throw new InvalidFormatException("Invalid seconds input");
        this.seconds = seconds;
    }
    public void setMilliseconds(long milliseconds){
        if(milliseconds<0 || milliseconds>1000) throw new InvalidFormatException("Invalid milliseconds input");
        this.milliseconds = milliseconds;
    }
    public String toString(){
        return this.hours + ":" + this.minutes + ":" + this.seconds+":" + this.milliseconds;
    }
}
