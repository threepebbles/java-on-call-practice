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
    public WorkCalender createWorkCalender(int month, DayOfWeek startDayOfWeek, List<Worker> weekDayWorkers,
                                           List<Worker> restDayWorkers) {
        int maxTry = weekDayWorkers.size() * restDayWorkers.size();
        while (maxTry > 0) {
            WorkCalender calender = createWorkCalenderBy(month, startDayOfWeek,
                    weekDayWorkers, restDayWorkers);
            if (isConsecutiveWorkerExist(calender)) {
                swapWorkerOrder(weekDayWorkers, restDayWorkers, calender);
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

    private WorkCalender createWorkCalenderBy(int month, DayOfWeek startDayOfWeek, List<Worker> weekDayWorkers,
                                              List<Worker> restDayWorkers) {
        WorkCalender calender = new WorkCalender(startDayOfWeek);
        List<WorkDate> weekDays = new ArrayList<>();
        List<WorkDate> restDays = new ArrayList<>();
        classifyWorkDate(month, startDayOfWeek, restDays, weekDays);
        distributeWeekDayWork(weekDayWorkers, weekDays, calender);
        distributeRestDayWork(restDayWorkers, restDays, calender);
        return calender;
    }

    private void distributeWeekDayWork(List<Worker> weekDayWorkers, List<WorkDate> weekDays, WorkCalender calender) {
        for (int i = 0, j = 0; i < weekDays.size(); i++, j = (j + 1) % (weekDayWorkers.size())) {
            calender.addSchedule(weekDays.get(i), weekDayWorkers.get(j));
        }
    }

    private void distributeRestDayWork(List<Worker> restDayWorkers, List<WorkDate> restDays, WorkCalender calender) {
        for (int i = 0, j = 0; i < restDays.size(); i++, j = (j + 1) % (restDayWorkers.size())) {
            calender.addSchedule(restDays.get(i), restDayWorkers.get(j));
        }
    }

    private void classifyWorkDate(int month, DayOfWeek startDayOfWeek, List<WorkDate> restDays,
                                  List<WorkDate> weekDays) {
        int endDay = DateUtil.MONTH_TO_END_DAY.get(month);
        for (int i = 1; i <= endDay; i++) {
            if (DateUtil.isRestDay(month, i, startDayOfWeek)) {
                restDays.add(new WorkDate(month, i, DateUtil.calculateDayOfWeek(i, startDayOfWeek)));
                continue;
            }
            weekDays.add(new WorkDate(month, i, DateUtil.calculateDayOfWeek(i, startDayOfWeek)));
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

    private void swapWorkerOrder(List<Worker> weekDayWorkers, List<Worker> restDayWorkers, WorkCalender calender) {
        List<String> workerNames = calender.getCalender().stream()
                .map(workInfo -> workInfo.getWorker().getName())
                .toList();
        WorkInfo workInfo = calender.getCalender().get(findFirstConsecutiveIndex(workerNames));
        Worker worker = workInfo.getWorker();
        int month = workInfo.getWorkDate().getMonth();
        int day = workInfo.getWorkDate().getDay();
        swapWorker(weekDayWorkers, restDayWorkers, calender, month, day, worker);
    }

    private void swapWorker(List<Worker> weekDayWorkers, List<Worker> restDayWorkers, WorkCalender calender,
                            int month, int day, Worker worker) {
        if (DateUtil.isRestDay(month, day, calender.getStartDayOfWeek())) {
            int i = restDayWorkers.indexOf(worker);
            int j = (i + 1) % (restDayWorkers.size());
            Collections.swap(restDayWorkers, i, j);
            return;
        }
        int i = weekDayWorkers.indexOf(worker);
        int j = (i + 1) % (weekDayWorkers.size());
        Collections.swap(weekDayWorkers, i, j);
    }
}
