package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    //private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang", new Locale("ru", "RU"));
    //private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang", Locale.ENGLISH);
    private ResourceBundle b = ResourceBundle.getBundle("Resources.localization.lang");
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = primaryStage;

        primaryStage.setTitle(b.getString("main_title"));
        //primaryStage.setTitle(new String(b.getString("main_title").getBytes("UTF-8"), "ISO-8859-1"));
        //primaryStage.setTitle(new String(b.getString("main_title").getBytes("ISO-8859-1"), "UTF-8"));



        //primaryStage.setTitle(new String(b.getString(new String(("gui.label.main_title").getBytes("UTF-16"),"UTF-8")).getBytes("ISO-8859-1"), "UTF-16"));

        //primaryStage.setTitle("бизнес информация");

        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

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

        VBox root = new VBox(20);
        root.getChildren().add(HBoxBuilder.create().spacing(10).style("-fx-background-color: gray").padding(new Insets(5)).children(btnEN, btnRU).build());
        root.getChildren().add(new StackPane());
        //root.getChildren().add(new StackPane());
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        loadView(new Locale("ru", "RU"));
    }

    private void loadView(Locale local) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setResources(ResourceBundle.getBundle("Resources.localization.lang", local));

            Pane pane = (BorderPane) fxmlLoader.load(this.getClass().getResource("sample.fxml").openStream());
            // replace the content
            StackPane content = (StackPane) ((VBox) stage.getScene().getRoot()).getChildren().get(1);
            content.getChildren().clear();
            content.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
