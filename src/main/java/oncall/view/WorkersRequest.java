package oncall.view;

import java.util.List;
import oncall.domain.Worker;

public class WorkersRequest {
    private final List<Worker> weekdayWorkers;
    private final List<Worker> restDayWorkers;

    public WorkersRequest(List<Worker> weekdayWorkers, List<Worker> restDayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.restDayWorkers = restDayWorkers;
    }

    public List<Worker> getWeekdayWorkers() {
        return weekdayWorkers;
    }

    public List<Worker> getRestDayWorkers() {
        return restDayWorkers;
    }
}
