package Controllers;

import ConnectionLayer.ConexiuneProlog;
import Datafx.ExtendedAnimatedFlowContainer;
import com.jfoenix.controls.*;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.*;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@FXMLController(value = "/mainController.fxml")
public class MainController extends BaseController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @FXML
    private StackPane root;

    @FXML
    private StackPane titleBurgerContainer;

    @FXML
    private JFXHamburger titleBurger;

    @FXML
    private JFXDrawer drawer;

    private JFXPopup toolbarPopup;

    @FXML
    private JFXButton iesireButton;

    @FXML
    private JFXButton reinitiazaButton;

    @FXML
    private JFXButton afisareFapteButton;

    @PostConstruct
    public void init() throws Exception {
        // init the title hamburger icon
        Objects.requireNonNull(context, "context");
        ConexiuneProlog conexiuneProlog = (ConexiuneProlog) context.getRegisteredObject("ConexiuneProlog");

        iesireButton.setOnAction(action -> {
            try {
                conexiuneProlog.expeditor.trimiteMesajSicstus("exit");
                Platform.exit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        reinitiazaButton.setOnAction(action -> {
            try {
                conexiuneProlog.expeditor.trimiteMesajSicstus("comanda(reinitiaza)");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        afisareFapteButton.setOnAction(action -> {
            try {
                conexiuneProlog.expeditor.trimiteMesajSicstus("comanda(fapte)");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        drawer.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });
        titleBurgerContainer.setOnMouseClicked(e -> {
            if (drawer.isClosed() || drawer.isClosing()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        // create the inner flow and content
        flowContext = new ViewFlowContext();
        // set the default controller
        Flow innerFlow = new Flow(HomeController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(flowContext);
        flowContext.register("ContentFlowHandler", flowHandler);
        flowContext.register("ContentFlow", innerFlow);

        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        flowContext.register("ContentPane", drawer.getContent().get(0));
        flowContext.register("ConexiuneProlog", conexiuneProlog);

        // side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(flowContext);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));
    }

}
