package oncall;

import oncall.controller.MainController;
import oncall.service.OnCallService;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new OnCallService());
        mainController.run();
    }
}
