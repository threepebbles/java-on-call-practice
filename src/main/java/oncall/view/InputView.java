package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.util.DateUtil;
import oncall.util.RetryHandler;

public class InputView {
    private static final String ENTER_MONTH_AND_DAY_SCREEN = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String ENTER_WEEK_DAY_WORKER = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String ENTER_WEEKEND_WORKER = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public static MonthAndDayOfWeekRequest scanMonthAndDayOfWeek() {
        return (MonthAndDayOfWeekRequest) RetryHandler.retryUntilSuccessWithReturn(() -> {
            System.out.print(ENTER_MONTH_AND_DAY_SCREEN);
            String inp = Console.readLine();
            validateMonthAndDayOfWeek(inp);
            List<String> parsed = Arrays.stream(inp.split(",", -1)).toList();
            return new MonthAndDayOfWeekRequest(Integer.parseInt(parsed.get(0)), DayOfWeek.value(parsed.get(1)));
        });
    }

    private static void validateMonthAndDayOfWeek(String inp) {
        List<String> parsed = Arrays.stream(inp.split(",", -1)).toList();
        if (parsed.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 포맷입니다.");
        }
        validateInteger(parsed.get(0));
        int month = Integer.parseInt(parsed.get(0));
        if (!(DateUtil.isMonth(month) && DayOfWeek.isExist(parsed.get(1)))) {
            throw new IllegalArgumentException("[ERROR] 월과 시작 요일을 올바르게 입력하지 않았습니다.");
        }
    }

    private static void validateInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닙니다.");
        }
    }
}
