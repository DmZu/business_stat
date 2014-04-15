package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import project.Adapter_to_config;
import project.Adapter_to_log;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    //private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang", new Locale("ru", "RU"));
    //private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang", Locale.ENGLISH);
    private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang", Adapter_to_config.getInstance().GetLocale());
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("business_gui.fxml"));
        stage = primaryStage;



        primaryStage.setTitle(b.getString("main_title"));

        //primaryStage.close();

        VBox root = new VBox(20);



        ///Add lang buttons. If uncommit get(0) -> get(1)
        /*
        Button btnEN = new Button();
        btnEN.setText("English");
        btnEN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView(Locale.ENGLISH);
            }
        });


        Button btnRU = new Button();
        btnRU.setText("Russian");
        btnRU.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView(new Locale("ru", "RU"));
            }
        });


        root.getChildren().add(HBoxBuilder.create().spacing(10).style("-fx-background-color: gray").padding(new Insets(5)).children(btnEN, btnRU).build());
        */



        root.getChildren().add(new StackPane());

        primaryStage.setScene(new Scene(root, 680, 420));


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Adapter_to_log.getInstance().Disconnect();
                Adapter_to_config.getInstance().Save_config();
            }
        });

        primaryStage.show();

        loadView(Adapter_to_config.getInstance().GetLocale());

        //root.autosize();

    }

    private void loadView(Locale local) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();


            fxmlLoader.setResources(ResourceBundle.getBundle("Resources.localization.lang", local));

            URL url_ = this.getClass().getResource("business_gui.fxml");

            //FXMLLoader fxl = new FXMLLoader(url_);

            //business_Controller cntr = new business_Controller();

            //fxmlLoader.setRoot(cntr);

            //fxmlLoader.setController(cntr);

            Pane pane = (BorderPane) fxmlLoader.load(url_.openStream());
            // replace the content
            StackPane content = (StackPane) ((VBox) stage.getScene().getRoot()).getChildren().get(0);
            //pane.get
            content.getChildren().clear();
            content.getChildren().add(pane);
            //content.getChildren().add(new business_Controller());

            System.out.println("s = " + pane.getChildren().size());




            //stage.setHeight(800);
            //stage.setWidth(800);

            ((business_Controller)fxmlLoader.getController()).OnLoad();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
