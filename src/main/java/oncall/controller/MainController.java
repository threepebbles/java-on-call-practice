package oncall.controller;

import java.util.List;
import oncall.domain.WorkCalender;
import oncall.domain.Worker;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.MonthAndDayOfWeekRequest;
import oncall.view.OutputView;
import oncall.view.WorkersRequest;

public class MainController {
    private final OnCallService onCallService;

    public MainController(OnCallService onCallService) {
        this.onCallService = onCallService;
    }

    public void run() {
        MonthAndDayOfWeekRequest monthAndDayOfWeek = InputView.scanMonthAndDayOfWeek();
        WorkersRequest request = InputView.scanWorkers();
        List<Worker> weekdayWorkers = request.getWeekdayWorkers();
        List<Worker> restDayWorkers = request.getRestDayWorkers();

        WorkCalender calender = onCallService.createWorkCalender(monthAndDayOfWeek.getMonth(),
                monthAndDayOfWeek.getDayOfWeek(), weekdayWorkers, restDayWorkers);
        OutputView.printWorkCalender(calender);
    }
}
