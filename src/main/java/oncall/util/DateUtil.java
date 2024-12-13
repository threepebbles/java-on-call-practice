package oncall.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.domain.DayOfWeek;
import oncall.domain.Holiday;

public class DateUtil {
    public static final List<Holiday> HOLIDAYS = new ArrayList<Holiday>() {{
        add(new Holiday(1, 1, "신정"));
        add(new Holiday(3, 1, "삼일절"));
        add(new Holiday(5, 5, "어린이날"));
        add(new Holiday(6, 6, "현충일"));
        add(new Holiday(8, 15, "광복절"));
        add(new Holiday(10, 3, "개천절"));
        add(new Holiday(10, 9, "한글날"));
        add(new Holiday(12, 25, "성탄절"));
    }};
    public static final Map<Integer, Integer> MONTH_TO_END_DAY = new HashMap<Integer, Integer>() {
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

    public static final List<DayOfWeek> DAY_OF_WEEKS = new ArrayList<DayOfWeek>() {{
        add(DayOfWeek.MONDAY);
        add(DayOfWeek.TUESDAY);
        add(DayOfWeek.WEDNESDAY);
        add(DayOfWeek.THURSDAY);
        add(DayOfWeek.FRIDAY);
        add(DayOfWeek.SATURDAY);
        add(DayOfWeek.SUNDAY);
    }};

    public static Boolean isMonth(int month) {
        return month >= 1 && month <= 12;
    }

    public static Boolean isDay(int month, int day) {
        if (!isMonth(month)) {
            return false;
        }
        return day >= 1 && day <= MONTH_TO_END_DAY.get(month);
    }

    public static Boolean isHoliday(int month, int day) {
        return HOLIDAYS.stream().anyMatch(h -> h.getMonth() == month && h.getDay() == day);
    }

    public static Boolean isWeekend(int month, int day, DayOfWeek startDayOfWeek) {
        if (!isMonth(month) || !isDay(month, day)) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 월, 일입니다.");
        }
        DayOfWeek target = calculateDayOfWeek(day, startDayOfWeek);
        return target == DayOfWeek.SATURDAY || target == DayOfWeek.SUNDAY;
    }

    public static Boolean isWeekend(DayOfWeek target) {
        return target.equals(DayOfWeek.SATURDAY) || target.equals(DayOfWeek.SUNDAY);
    }

    public static Boolean isRestDay(int month, int day, DayOfWeek startDayOfWeek) {
        return isHoliday(month, day) || isWeekend(month, day, startDayOfWeek);
    }

    public static DayOfWeek calculateDayOfWeek(int day, DayOfWeek startDayOfWeek) {
        return DAY_OF_WEEKS.get(
                ((day - 1) % DAY_OF_WEEKS.size() + startDayOfWeek.getOrder() - DayOfWeek.MONDAY.getOrder())
                        % DAY_OF_WEEKS.size());
    }
}
