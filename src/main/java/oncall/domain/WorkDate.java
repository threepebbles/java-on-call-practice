package oncall.domain;

public class WorkDate {
    private final int month;
    private final int day;
    private final DayOfWeek dayOfWeek;

    public WorkDate(int month, int day, DayOfWeek dayOfWeek) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
