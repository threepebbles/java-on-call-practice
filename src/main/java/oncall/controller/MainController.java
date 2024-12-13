package oncall.controller;

import oncall.domain.Worker;
import oncall.view.InputView;
import oncall.view.MonthAndDayOfWeekRequest;
import oncall.view.WorkersRequest;

public class MainController {

    public MainController() {
    }

    public void run() {
        MonthAndDayOfWeekRequest request = InputView.scanMonthAndDayOfWeek();
        System.out.println(request.getMonth() + "," + request.getDayOfWeek());

        WorkersRequest weekDayWorkers = InputView.scanWeekDayWorkers();
        for (Worker w : weekDayWorkers.getWorkers()) {
            System.out.println(w.getName());
        }

        WorkersRequest restDayWorkers = InputView.scanRestDayWorkers();
        for (Worker w : restDayWorkers.getWorkers()) {
            System.out.println(w.getName());
        }
    }
}
