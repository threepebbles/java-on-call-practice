package oncall.view;

import oncall.domain.DayOfWeek;
import oncall.domain.WorkCalender;
import oncall.domain.WorkInfo;
import oncall.util.DateUtil;

public class OutputView {
    public static void printWorkCalender(WorkCalender calender) {
        for (WorkInfo workInfo : calender.getCalender()) {
            int month = workInfo.getWorkDate().getMonth();
            int day = workInfo.getWorkDate().getDay();
            DayOfWeek dayOfWeek = workInfo.getWorkDate().getDayOfWeek();
            String dayOfWeekFormat = dayOfWeek.getName();
            if (!DateUtil.isWeekend(dayOfWeek) && DateUtil.isRestDay(month, day, calender.getStartDayOfWeek())) {
                dayOfWeekFormat += "(휴일)";
            }
            System.out.printf("%d월 %d일 %s %s%n", month, day, dayOfWeekFormat, workInfo.getWorker().getName());
        }
    }
}
