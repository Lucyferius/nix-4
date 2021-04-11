package nix.alevel.entity;

import lombok.Setter;

@Setter
public class Format {
    public static final String DAY_D = "d";
    public static final String DAY_DD = "dd";
    public static final String MONTH_M = "m";
    public static final String MONTH_MM= "mm";
    public static final String MONTH_MMM = "mmm";
    public static final String YEAR_YY = "yy";
    public static final String YEAR_YYYY = "yyyy";
    public static final String TAG_SLASH = "/";
    public static final String TAG_DASH = "-";


    private Boolean isDayD = false, isDayDD = false, isMonthM = false, isMonthMM = false, isMonthMMM = false,
        isYearYY = false, isYearYYYY = false, tagSlash = false, tagDash = false,
            isTime = false, isFullTime  = false, isMilliseconds = false, isHours = false, isSecondsMinutes = false;
    private String[] order = {DAY_D, MONTH_M, YEAR_YYYY}; //default

    public String getDayFormat(){
        if(isDayDD) return DAY_DD;
        else return DAY_D;
    }
    public String getMonthFormat(){
        if(isMonthMMM) return MONTH_MMM;
        if(isMonthMM) return MONTH_MM;
        else return MONTH_M;
    }
    public String getYearFormat(){
        if(isYearYY) return YEAR_YY;
        else return YEAR_YYYY;
    }
    public String[] getOrder(){
        return order;
    }
    public void setOrder(String order, int index){
        this.order[index] = order;
    }
    public String getTag(){
        if(tagDash) return TAG_DASH;
        else return TAG_SLASH;
    }
    public Boolean getIsFullTime(){
        return isFullTime;
    }
    public Boolean getIsTime(){
        return isTime;
    }
    public Boolean getIsMilliseconds(){
        return isMilliseconds;
    }
    public Boolean getIsHours(){return isHours;};
    public Boolean getIsSecondsMinutes(){return isSecondsMinutes;}
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(tagSlash)
        stringBuilder.append(order[0]).append(TAG_SLASH).append(order[1]).append(TAG_SLASH).append(order[2]);
        if(tagDash)
            stringBuilder.append(order[0]).append(TAG_DASH).append(order[1]).append(TAG_DASH).append(order[2]);

        stringBuilder.append(" ");
        if(isHours)
            stringBuilder.append("00:");
        if(isSecondsMinutes)
            stringBuilder.append("00:00");
        if(isMilliseconds)
            stringBuilder.append(":00");

        return  stringBuilder.toString();
    }
}
