package oncall.view;

import java.util.List;
import oncall.domain.Worker;

public class WorkersRequest {
    private final List<Worker> workers;

    public WorkersRequest(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }
}
