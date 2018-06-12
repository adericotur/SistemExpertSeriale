package Controllers;

import ConnectionLayer.ConexiuneProlog;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import java.util.Objects;

@FXMLController(value = "/sideMenuController.fxml")
public class SideMenuController {
    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    @ActionTrigger("incarca")
    private Label incarca;
    @FXML
    @ActionTrigger("consulta")
    private Label consulta;
    @FXML
    @ActionTrigger("reinitiaza")
    private Label reinitiaza;
    @FXML
    @ActionTrigger("afisare_Fapte")
    private Label afisareFapte;
    @FXML
    @ActionTrigger("cum")
    private Label cum;
    @FXML
    @ActionTrigger("iesire")
    private Label iesire;
    @FXML
    private JFXListView<Label> sideList;

    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
        ConexiuneProlog conexiuneProlog = (ConexiuneProlog) context.getRegisteredObject("ConexiuneProlog");
        sideList.propagateMouseEventsToParent();
        sideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    if (newVal != null) {
                        try {
                            //id-urile label-urilor coincid cu comanda care trebuie trimisa la sicttus
                            System.out.println(newVal.getId());
                            //conexiuneProlog.expeditor.trimiteComandaSicstus(newVal.getId());
                        } catch (Exception e) {
                            JFXAlert alert = new JFXAlert((Stage) incarca.getScene().getWindow());
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.setOverlayClose(false);
                            JFXDialogLayout layout = new JFXDialogLayout();
                            layout.setHeading(new Label("Eroare !"));
                            layout.setBody(new Label(e.getMessage()));
                            JFXButton closeButton = new JFXButton("ACCEPT");
                            closeButton.getStyleClass().add("dialog-accept");
                            closeButton.setOnAction(event -> alert.hideWithAnimation());
                            layout.setActions(closeButton);
                            alert.setContent(layout);
                            alert.show();
                        }

                        try {
                            contentFlowHandler.handle(newVal.getId());
                        } catch (VetoException exc) {
                            exc.printStackTrace();
                        } catch (FlowException exc) {
                            exc.printStackTrace();
                        }
                    }
                });
            }).start();
        });

        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
        bindNodeToController(incarca, IncarcaController.class, contentFlow, contentFlowHandler);
        bindNodeToController(consulta, ConsultaController.class, contentFlow, contentFlowHandler);
    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }
}
