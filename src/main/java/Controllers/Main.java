package Controllers;

import ConnectionLayer.ConexiuneProlog;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Application {

    private static final int PORT=5007;
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void start(Stage stage) throws Exception{
        ConexiuneProlog conexiuneProlog = new ConexiuneProlog(PORT, stage);



        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();

        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flowContext.register("ConexiuneProlog", conexiuneProlog);

        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        decorator.setGraphic(new SVGGlyph(""));

        stage.setTitle("SmartChoice");

        double width = 1000;
        double height = 600;

        Scene scene = new Scene(decorator, width, height);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                Main.class.getResource("/jfoenix-components.css").toExternalForm(),
                Main.class.getResource("/jfoenix-main-demo.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws Exception {

        launch(args);
    }
}
