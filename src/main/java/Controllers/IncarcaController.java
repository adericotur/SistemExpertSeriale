package Controllers;

import ConnectionLayer.ConexiuneProlog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@FXMLController(value = "/incarcaController.fxml")
public class IncarcaController {
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @FXML
    private JFXTextField numeFisierTF;
    @FXML
    private JFXButton trimiteButton;

    private ConexiuneProlog conexiuneProlog;

    @PostConstruct
    public void init() {
        Objects.requireNonNull(flowContext, "flowContext");
        this.conexiuneProlog = (ConexiuneProlog) flowContext.getRegisteredObject("ConexiuneProlog");

        //TODO remove
        numeFisierTF.setText("reguli_filme.txt");

        numeFisierTF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                numeFisierTF.validate();
            }
        });

        trimiteButton.setOnAction(action -> {
            String numeFisier = numeFisierTF.getText();

            String dir=System.getProperty("user.dir");
            dir=dir.replace("\\", "/");
            try {
                conexiuneProlog.expeditor.trimiteMesajSicstus("director('"+dir+"')");
                conexiuneProlog.expeditor.trimiteMesajSicstus("incarca('"+numeFisier+"')");
            } catch (Exception ex) {
                System.out.println(ex);
                //Logger.getLogger(Fereastra.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
