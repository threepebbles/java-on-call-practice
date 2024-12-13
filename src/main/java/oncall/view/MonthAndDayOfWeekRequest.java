package oncall.view;

import oncall.domain.DayOfWeek;

public class MonthAndDayOfWeekRequest {
    private final int month;
    private final DayOfWeek dayOfWeek;

    public MonthAndDayOfWeekRequest(int month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
