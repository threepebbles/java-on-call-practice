package oncall.domain;

public class Holiday {
    private final int month;
    private final int day;
    private final String description;

    public Holiday(int month, int day, String description) {
        this.month = month;
        this.day = day;
        this.description = description;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }
}
