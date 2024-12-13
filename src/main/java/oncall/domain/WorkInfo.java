package oncall.domain;

public class WorkInfo {
    private final WorkDate workDate;
    private final Worker worker;

    public WorkInfo(WorkDate workDate, Worker worker) {
        this.workDate = workDate;
        this.worker = worker;
    }

    public int calculateOrder() {
        return this.workDate.getDay();
    }

    public WorkDate getWorkDate() {
        return workDate;
    }

    public Worker getWorker() {
        return worker;
    }
}
