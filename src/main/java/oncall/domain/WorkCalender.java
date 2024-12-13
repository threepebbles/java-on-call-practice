package oncall.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorkCalender {
    private final int month;
    private final DayOfWeek startDayOfWeek;
    private final List<WorkInfo> calender = new ArrayList<>();

    public WorkCalender(int month, DayOfWeek startDayOfWeek) {
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
    }

    public void addSchedule(WorkDate workDate, Worker worker) {
        calender.add(new WorkInfo(workDate, worker));
        calender.sort(Comparator.comparing(WorkInfo::calculateOrder));
    }

    public DayOfWeek getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public List<WorkInfo> getCalender() {
        return calender;
    }
}
