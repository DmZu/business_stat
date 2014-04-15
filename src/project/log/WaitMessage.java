package project.log;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by d.zhukov on 14.04.14.
 */
public class WaitMessage {
    private static WaitMessage ourInstance = new WaitMessage();

    public static WaitMessage getInstance() {
        return ourInstance;
    }

    private WaitMessage() {
    }

    private Stage ms;

    public void Show(String text)
    {
        ms = new Stage();

        Pane root = new Pane();

        Label text_mes = new Label(text);

        root.getChildren().add(text_mes);

        ms.setScene(new Scene(root, 200, 1));

        ms.setTitle("Please, wait...");

        ms.show();
    }

    public void Close()
    {
        if(ms != null && ms.isShowing())
            ms.close();
    }
}
