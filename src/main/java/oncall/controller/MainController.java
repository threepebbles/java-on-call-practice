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
//        System.out.println(monthAndDayOfWeek.getMonth() + "," + monthAndDayOfWeek.getDayOfWeek());

        List<Worker> weekDayWorkers = InputView.scanWeekDayWorkers().getWorkers();
//        for (Worker w : weekDayWorkers) {
//            System.out.println(w.getName());
//        }

        List<Worker> restDayWorkers = InputView.scanRestDayWorkers().getWorkers();
//        for (Worker w : restDayWorkers) {
//            System.out.println(w.getName());
//        }
        WorkCalender calender = onCallService.createWorkCalender(monthAndDayOfWeek.getMonth(),
                monthAndDayOfWeek.getDayOfWeek(), weekDayWorkers, restDayWorkers);
        OutputView.printWorkCalender(calender);
    }
}
