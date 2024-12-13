package oncall.domain;

import java.util.HashMap;
import java.util.Map;

public class WorkCalender {
    private Map<WorkDate, Worker> calender = new HashMap<>();

    public void addSchedule(WorkDate workDate, Worker worker) {
        calender.put(workDate, worker);
    }
}
