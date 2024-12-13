package oncall.domain;

import java.util.Objects;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private String name;

    DayOfWeek(String name) {
        this.name = name;
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
}
