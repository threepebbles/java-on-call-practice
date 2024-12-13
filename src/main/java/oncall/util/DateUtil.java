package oncall.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.domain.Holiday;

public class DateUtil {
    private static final List<Holiday> HOLIDAYS = new ArrayList<Holiday>() {{
        add(new Holiday(1, 1));
        add(new Holiday(3, 1));
        add(new Holiday(5, 5));
        add(new Holiday(6, 6));
        add(new Holiday(8, 15));
        add(new Holiday(10, 3));
        add(new Holiday(10, 9));
        add(new Holiday(12, 25));
    }};
    private static final Map<Integer, Integer> MONTH_TO_DAY = new HashMap<Integer, Integer>() {
        {
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }
    };

    public static Boolean isMonth(int month) {
        return month >= 1 && month <= 12;
    }

    public static Boolean isDay(int month, int day) {
        if (!isMonth(month)) {
            return false;
        }
        return day >= 1 && day <= MONTH_TO_DAY.get(month);
    }

    public static Boolean isHoliday(int month, int day) {
        return HOLIDAYS.stream().anyMatch(h -> h.getMonth() == month && h.getDay() == day);
    }
}
