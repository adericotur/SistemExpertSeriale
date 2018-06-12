package Controllers;

import io.datafx.controller.FXMLController;

import javax.annotation.PostConstruct;

@FXMLController(value = "/homeController.fxml")
public class HomeController {

    @PostConstruct
    public void init() {
        System.out.println("eu sunt HomeController");
    }
}
