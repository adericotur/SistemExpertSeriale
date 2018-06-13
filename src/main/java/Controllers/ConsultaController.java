package Controllers;

import ConnectionLayer.ConexiuneProlog;
import Datafx.ExtendedAnimatedFlowContainer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.context.ViewContext;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.LinkAction;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@FXMLController(value = "/consultaController.fxml")
public class ConsultaController {
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private Label intrebareLabel;

    @FXML
    private JFXButton[] responseButtonsArray;

    @FXML
    private HBox hbox;

    private ConexiuneProlog conexiuneProlog;

    private List<String> solutii = new ArrayList<>();

//    @FXML
//    @ActionTrigger("actionButton")
//    private JFXButton actionButton;

    @PostConstruct
    public void init() {
        Objects.requireNonNull(flowContext, "flowContext");

        FlowHandler contentFlowHandler = (FlowHandler) flowContext.getRegisteredObject("ContentFlowHandler");
        this.conexiuneProlog = (ConexiuneProlog) flowContext.getRegisteredObject("ConexiuneProlog");
        conexiuneProlog.controller = this;
        try {
            conexiuneProlog.expeditor.trimiteMesajSicstus("comanda(consulta)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        numeFisierTF.focusedProperty().addListener((o, oldVal, newVal) -> {
//            if (!newVal) {
//                numeFisierTF.validate();
//            }
//        });






//        Objects.requireNonNull(context, "context");
//        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
//        contentFlow.withLink(ConsultaController.class, actionButton.getId(), RezultateController.class);

    }

    public void setIntrebare(String intrebare) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                intrebareLabel.setText(intrebare);
            }
        });

        //intrebareLabel.setText(intrebare);
    }

    public void setButoaneRaspuns(String raspunsuri) {
        raspunsuri = raspunsuri.trim();
        raspunsuri = raspunsuri.substring(1,raspunsuri.length()-1);
        raspunsuri = raspunsuri.trim();
//        raspunsuri += " nu_stiu nu_conteaza";
        String[] raspunsuriArray = raspunsuri.split(" ");


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hbox.getChildren().removeAll(hbox.getChildren());
                for (int i = 0; i < raspunsuriArray.length; i++) {
                    JFXButton newButton = new JFXButton(raspunsuriArray[i]);
                    newButton.setAlignment(Pos.CENTER);
                    newButton.getStyleClass().clear();
                    newButton.getStyleClass().add("button-raised");
                    newButton.setPrefWidth(80.0);
                    newButton.setPrefHeight(40.0);
                    newButton.setOnAction(action -> {
                        String raspuns = newButton.getText();
                        try {
                            conexiuneProlog.expeditor.trimiteSirSicstus(raspuns);
                            //remove existing buttons
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    });
                    hbox.getChildren().add(newButton);
                }
            }
        });

    }

    public void pushEcranSolutie(String solutie) {
        solutii.add(solutie);

        Platform.runLater(() -> {

//            intrebareLabel.setText(solutie);
//            hbox.getChildren().removeAll(hbox.getChildren());
//
//            JFXButton newButton = new JFXButton("Cum?");
//            newButton.setAlignment(Pos.CENTER);
//            newButton.getStyleClass().clear();
//            newButton.getStyleClass().add("button-raised");
//            newButton.setPrefWidth(80.0);
//            newButton.setPrefHeight(40.0);
//
//            newButton.setOnAction(event -> {
//                String cumString = "";
//                try {
//                    conexiuneProlog.expeditor.trimiteSirSicstus(cumString);
//                    //remove existing buttons
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            });
//
//            hbox.getChildren().add(newButton);

            solutii.forEach(sol -> {
                System.out.println("solutie de boss\n");
            });





//        ViewFlowContext newflowContext = new ViewFlowContext();
//        Flow rezultateFlow = new Flow(RezultateController.class);
//
//        final FlowHandler xflowHandler = rezultateFlow.createHandler(newflowContext);
//        newflowContext.register("ContentFlowHandler", xflowHandler);
//        newflowContext.register("ContentFlow", rezultateFlow);
//
//
//        try {
//            xflowHandler.start(new ExtendedAnimatedFlowContainer(Duration.millis(320), SWIPE_LEFT));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }



//        JFXButton newButton = new JFXButton("Rezultate");
//        newButton.setAlignment(Pos.CENTER);
//        newButton.setPrefWidth(80.0);
//        newButton.setPrefHeight(40.0);
//        hbox.getChildren().add(newButton);

//        Objects.requireNonNull(context, "context");
//        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
//        contentFlow.withGlobalLink(newButton.getId(), RezultateController.class);






//        FlowHandler myFlow = new Flow(RezultateController.class).createHandler();
//
//        try {
//            hbox.getChildren().add(myFlow.start());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//





        });
    }


}
