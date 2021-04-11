package nix.alevel.entity;

public enum Month {
    JANUARY(1, "January"), FEBRUARY(2,"February"), MARCH(3,"March"),
    APRIL(4,"April"), MAY(5,"May"), JUNE(6,"June"),
    JULY(7,"July"), AUGUST(8,"August"), SEPTEMBER(9,"September"),
    OCTOBER(10,"October"), NOVEMBER(11,"November"), DECEMBER(12,"December");


    private String name;
    private int monthNumber;
    Month(int monthNumber, String name) {
        this.monthNumber = monthNumber;
        this.name = name;
    }
    public static Month byOrdinal(int ord) {
        for (Month m : Month.values()) {
            if (m.monthNumber == ord) {
                return m;
            }
        }
        return null;
    }
    public static Month byName(String name) {
        for (Month m : Month.values()) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
