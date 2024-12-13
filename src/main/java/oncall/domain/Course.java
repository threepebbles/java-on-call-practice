package oncall.domain;

public enum Course {
    WEEKDAY("평일"),
    REST_DAY("휴일");
    private String description;

    Course(String description) {
        this.description = description;
    }
}
