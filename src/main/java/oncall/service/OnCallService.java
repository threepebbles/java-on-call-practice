package oncall.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import oncall.domain.DayOfWeek;
import oncall.domain.WorkCalender;
import oncall.domain.WorkDate;
import oncall.domain.WorkInfo;
import oncall.domain.Worker;
import oncall.util.DateUtil;

public class OnCallService {
    public WorkCalender createWorkCalender(int month, DayOfWeek startDayOfWeek, List<Worker> weekdayWorkers,
                                           List<Worker> restDayWorkers) {
        int maxTry = weekdayWorkers.size() * restDayWorkers.size();
        while (maxTry > 0) {
            WorkCalender calender = createWorkCalenderBy(month, startDayOfWeek,
                    weekdayWorkers, restDayWorkers);
            if (isConsecutiveWorkerExist(calender)) {
                swapWorkerOrder(weekdayWorkers, restDayWorkers, calender);
                maxTry--;
                continue;
            }
            return calender;
        }
        if (maxTry == 0) {
            throw new IllegalArgumentException("[ERROR] 연속 근무자가 없게 근무표를 작성할 수 없습니다.");
        }
        throw new IllegalArgumentException("[ERROR] 근무표를 작성할 수 없습니다.");
    }

    private WorkCalender createWorkCalenderBy(int month, DayOfWeek startDayOfWeek, List<Worker> weekdayWorkers,
                                              List<Worker> restDayWorkers) {
        WorkCalender calender = new WorkCalender(startDayOfWeek);
        List<WorkDate> weekdays = new ArrayList<>();
        List<WorkDate> restDays = new ArrayList<>();
        classifyWorkDate(month, startDayOfWeek, restDays, weekdays);
        distributeWeekdayWork(weekdayWorkers, weekdays, calender);
        distributeRestDayWork(restDayWorkers, restDays, calender);
        return calender;
    }

    private void classifyWorkDate(int month, DayOfWeek startDayOfWeek, List<WorkDate> restDays,
                                  List<WorkDate> weekdays) {
        int endDay = DateUtil.MONTH_TO_END_DAY.get(month);
        for (int i = 1; i <= endDay; i++) {
            if (DateUtil.isRestDay(month, i, startDayOfWeek)) {
                restDays.add(new WorkDate(month, i, DateUtil.calculateDayOfWeek(i, startDayOfWeek)));
                continue;
            }
            weekdays.add(new WorkDate(month, i, DateUtil.calculateDayOfWeek(i, startDayOfWeek)));
        }
    }

    private void distributeWeekdayWork(List<Worker> weekdayWorkers, List<WorkDate> weekdays, WorkCalender calender) {
        for (int i = 0, j = 0; i < weekdays.size(); i++, j = (j + 1) % (weekdayWorkers.size())) {
            calender.addSchedule(weekdays.get(i), weekdayWorkers.get(j));
        }
    }

    private void distributeRestDayWork(List<Worker> restDayWorkers, List<WorkDate> restDays, WorkCalender calender) {
        for (int i = 0, j = 0; i < restDays.size(); i++, j = (j + 1) % (restDayWorkers.size())) {
            calender.addSchedule(restDays.get(i), restDayWorkers.get(j));
        }
    }

    private boolean isConsecutiveWorkerExist(WorkCalender calender) {
        List<String> workerNames = calender.getCalender().stream()
                .map(workInfo -> workInfo.getWorker().getName())
                .toList();
        return findFirstConsecutiveIndex(workerNames) != -1;
    }

    private int findFirstConsecutiveIndex(List<String> workerNames) {
        for (int i = 1; i < workerNames.size(); i++) {
            if (Objects.equals(workerNames.get(i - 1), workerNames.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void swapWorkerOrder(List<Worker> weekdayWorkers, List<Worker> restDayWorkers, WorkCalender calender) {
        List<String> workerNames = calender.getCalender().stream()
                .map(workInfo -> workInfo.getWorker().getName())
                .toList();
        WorkInfo workInfo = calender.getCalender().get(findFirstConsecutiveIndex(workerNames));
        Worker worker = workInfo.getWorker();
        int month = workInfo.getWorkDate().getMonth();
        int day = workInfo.getWorkDate().getDay();
        DayOfWeek startDayOfWeek = calender.getStartDayOfWeek();

        if (DateUtil.isRestDay(month, day, startDayOfWeek)) {
            findAndSwapWorker(restDayWorkers, worker);
            return;
        }
        findAndSwapWorker(weekdayWorkers, worker);
    }

    private void findAndSwapWorker(List<Worker> workers, Worker worker) {
        int i = workers.indexOf(worker);
        int j = (i + 1) % (workers.size());
        Collections.swap(workers, i, j);
    }
}
