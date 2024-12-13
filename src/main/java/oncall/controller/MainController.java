package oncall.controller;

import java.util.List;
import oncall.domain.Course;
import oncall.domain.DayOfWeek;
import oncall.domain.WorkCalender;
import oncall.domain.WorkDate;
import oncall.domain.Worker;
import oncall.view.InputView;
import oncall.view.MonthAndDayOfWeekRequest;
import oncall.view.OutputView;
import oncall.view.WorkersRequest;

public class MainController {

    public MainController() {
    }

    public void run() {
        MonthAndDayOfWeekRequest monthAndDayOfWeek = InputView.scanMonthAndDayOfWeek();
        System.out.println(monthAndDayOfWeek.getMonth() + "," + monthAndDayOfWeek.getDayOfWeek());

        WorkersRequest weekDayWorkers = InputView.scanWeekDayWorkers();
        for (Worker w : weekDayWorkers.getWorkers()) {
            System.out.println(w.getName());
        }

        WorkersRequest restDayWorkers = InputView.scanRestDayWorkers();
        for (Worker w : restDayWorkers.getWorkers()) {
            System.out.println(w.getName());
        }
        WorkCalender calender = createWorkCalender(monthAndDayOfWeek.getMonth(), monthAndDayOfWeek.getDayOfWeek(),
                weekDayWorkers.getWorkers(), restDayWorkers.getWorkers());
        OutputView.printWorkCalender(calender);
    }

    public WorkCalender createWorkCalender(int month, DayOfWeek startDayOfWeek, List<Worker> weekDayWorkers,
                                           List<Worker> restDayWorkers) {
        // example
        WorkCalender calender = new WorkCalender(startDayOfWeek);
        calender.addSchedule(new WorkDate(month, 1, DayOfWeek.MONDAY), new Worker(Course.WEEK_DAY, "준팍"));
        calender.addSchedule(new WorkDate(month, 2, DayOfWeek.TUESDAY), new Worker(Course.WEEK_DAY, "도밥"));
        return calender;
    }
}
