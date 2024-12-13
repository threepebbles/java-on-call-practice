package oncall.domain;

public class Holiday {
    private final int month;
    private final int day;

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
