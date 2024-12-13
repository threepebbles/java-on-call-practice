package oncall.domain;

import java.util.Objects;

public enum DayOfWeek {
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    private String name;
    private int order;

    DayOfWeek(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public static Boolean isExist(String name) {
        for (DayOfWeek dow : DayOfWeek.values()) {
            if (Objects.equals(dow.name, name)) {
                return true;
            }
        }
        return false;
    }

    public static DayOfWeek value(String name) {
        for (DayOfWeek dow : DayOfWeek.values()) {
            if (Objects.equals(dow.name, name)) {
                return dow;
            }
        }
        throw new IllegalArgumentException("[ERROR] 올바르지 않은 요일명입니다.");
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
