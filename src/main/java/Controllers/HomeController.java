package Controllers;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;

import javax.annotation.PostConstruct;

@FXMLController(value = "/homeController.fxml")
public class HomeController {

    @FXML
    JFXButton deschide;

    @PostConstruct
    public void init() {

        System.out.println("eu sunt HomeController");

        deschide.setOnAction(event -> {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "reguli_filme.txt");
            try{
                pb.start();
            }catch(Exception e){
                e.printStackTrace();
            }

        });


    }
}
