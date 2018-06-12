package Controllers;

import ConnectionLayer.ConexiuneProlog;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import java.util.Objects;

@FXMLController(value = "/rezultateController.fxml")
public class RezultateController {
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;


    @PostConstruct
    public void init() {
        Objects.requireNonNull(flowContext, "flowContext");
//        FlowHandler contentFlowHandler = (FlowHandler) flowContext.getRegisteredObject("ContentFlowHandler");
//        ConexiuneProlog conexiuneProlog = (ConexiuneProlog) flowContext.getRegisteredObject("ConexiuneProlog");
        System.out.println("eu sunt RezultateController");
    }


}
