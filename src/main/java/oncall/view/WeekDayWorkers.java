package oncall.view;

import java.util.List;
import oncall.domain.Worker;

public class WeekDayWorkers {
    private final List<Worker> workers;

    public WeekDayWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }
}
