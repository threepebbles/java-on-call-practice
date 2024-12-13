package oncall.domain;

public enum Course {
    WEEK_DAY("평일"),
    WEEKEND("휴일");
    private String description;

    Course(String description) {
        this.description = description;
    }
}
