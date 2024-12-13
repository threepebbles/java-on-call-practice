package oncall.domain;

public class Holiday {
    private int month;
    private int day;

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
