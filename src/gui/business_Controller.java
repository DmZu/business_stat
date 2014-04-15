package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import project.Adapter_to_config;
import project.Adapter_to_log;
import sun.security.util.Resources;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class business_Controller extends VBox {

    @FXML private Button actiontarget;

    @FXML private Button print_button;

    @FXML private WebView web_viewer;

    @FXML private ComboBox connect_comboBox;

    @FXML private ComboBox lang_combobox;

    @FXML private DatePicker begin_data;

    @FXML private DatePicker end_data;

    @FXML private BorderPane main_border_pain;



    @FXML public void OnLoad()
    {

        for(String str : Adapter_to_config.getInstance().GetCnctsStrs())
            connect_comboBox.getItems().add(str.split("<div>")[0]);

        connect_comboBox.setValue(Adapter_to_config.getInstance().GetActiveConnect().split("<div>")[0]);

        Calendar cld = Calendar.getInstance();

        cld.setTime(Adapter_to_config.getInstance().GetBeginDate());
        begin_data.setValue(LocalDate.ofYearDay(cld.get(Calendar.YEAR), cld.get(Calendar.DAY_OF_YEAR)));

        cld.setTime(Adapter_to_config.getInstance().GetEndDate());
        end_data.setValue(LocalDate.ofYearDay(cld.get(Calendar.YEAR), cld.get(Calendar.DAY_OF_YEAR)));

        //main_border_pain.set

        lang_combobox.getItems().add("en");
        lang_combobox.getItems().add("ru");

        if(Adapter_to_config.getInstance().GetLocale() == Locale.ENGLISH)
            lang_combobox.getSelectionModel().select(0);
        else
            lang_combobox.getSelectionModel().select(1);

    }

    @FXML private void BeginDatePickerAction(ActionEvent event) {

        try {
            Adapter_to_config.getInstance().SetBeginDate((new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS")).parse(
                    begin_data.getValue().getYear() + "." +
                    begin_data.getValue().getMonthValue() + "." +
                    begin_data.getValue().getDayOfMonth() +
                    " 00:00:00.000"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML private void EndDatePickerAction(ActionEvent event) {

        try {
            Adapter_to_config.getInstance().SetEndDate((new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS")).parse(
                    end_data.getValue().getYear() + "." +
                            end_data.getValue().getMonthValue() + "." +
                            end_data.getValue().getDayOfMonth() +
                            " 23:59:59.999"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML private void ConnectStringChange(ActionEvent event) {

        Adapter_to_config.getInstance().SetActiveConnect(connect_comboBox.getValue().toString());


        //if(Adapter_to_log.getInstance().IsConnected())
        Adapter_to_log.getInstance().Connect();

    }

    @FXML private void LangChange(ActionEvent event) {

        Adapter_to_config.getInstance().SetLocale(lang_combobox.getValue().toString());

    }

    @FXML private void PrintReport(ActionEvent event) {

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            web_viewer.getEngine().print(job);
            job.endJob();
        }


    }

    @FXML private void GetReportButtonAction(ActionEvent event) {
        //actiontarget.setText("Sign in button pressed");
//        actiontarget.setText("Access Level = " + Adapter_to_log.getInstance().ReadLog().length());

        Adapter_to_log.getInstance().ReadLog(Adapter_to_config.getInstance().GetBeginDate(), Adapter_to_config.getInstance().GetEndDate());


        web_viewer.getEngine().load("file:///" + Paths.get("").toAbsolutePath().toString() + "\\" + Adapter_to_config.getInstance().GetReportFileName());


        //web_viewer.getEngine().load("JavaFXApp.html");
        //web_viewer.getEngine().load("http://www.yandex.ru/");

        web_viewer.getEngine().reload();

        /*
        PrinterJob pjob = PrinterJob.createPrinterJob();

        web_viewer.getEngine().print(pjob);

        pjob.endJob();
        */


        //WebEngine engine = web_viewer.getEngine();
        //engine.load(this.getClass().getResource("d:\\report.html").toExternalForm());
    }
}
