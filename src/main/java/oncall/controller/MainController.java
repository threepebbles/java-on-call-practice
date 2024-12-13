package oncall.controller;

import java.util.List;
import oncall.domain.WorkCalender;
import oncall.domain.Worker;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.MonthAndDayOfWeekRequest;
import oncall.view.OutputView;

public class MainController {
    private final OnCallService onCallService;

    public MainController(OnCallService onCallService) {
        this.onCallService = onCallService;
    }

    public void run() {
        MonthAndDayOfWeekRequest monthAndDayOfWeek = InputView.scanMonthAndDayOfWeek();
        List<Worker> weekdayWorkers = InputView.scanWeekdayWorkers().getWorkers();
        List<Worker> restDayWorkers = InputView.scanRestDayWorkers().getWorkers();

        WorkCalender calender = onCallService.createWorkCalender(monthAndDayOfWeek.getMonth(),
                monthAndDayOfWeek.getDayOfWeek(), weekdayWorkers, restDayWorkers);
        OutputView.printWorkCalender(calender);
    }
}
