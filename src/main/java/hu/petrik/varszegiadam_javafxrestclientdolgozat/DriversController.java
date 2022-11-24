package hu.petrik.varszegiadam_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class DriversController {

    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<Driver> driverTable;
    @FXML
    private TableColumn<Driver, Integer> id_Col;
    @FXML
    private TableColumn<Driver, String> driver_Col;
    @FXML
    private TableColumn<Driver, String> team_Col;
    @FXML
    private TableColumn<Driver, Boolean> ret_Col;
    @FXML
    private TableColumn<Driver, Integer> start_Col;
    @FXML
    private Button insertButton;

    @FXML
    private void initialize(){
        id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
        driver_Col.setCellValueFactory(new PropertyValueFactory<>("driver_name"));
        team_Col.setCellValueFactory(new PropertyValueFactory<>("team_name"));
        ret_Col.setCellValueFactory(new PropertyValueFactory<>("retired"));
        start_Col.setCellValueFactory(new PropertyValueFactory<>("start_Year"));
        Platform.runLater(()->{
                try {
                   loadDrivers();
                }catch (IOException e){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Database load failed");
                    alert.showAndWait();
                    Platform.exit();
                }
        });
    }

    private void loadDrivers()throws IOException{
        Response response =RequestHandler.get(App.BASE_URL);
        String content=response.getContent();
        Gson converter=new Gson();
        Driver[] drivers=converter.fromJson(content, Driver[].class);
        driverTable.getItems().clear();
        for (Driver driver:drivers){
            driverTable.getItems().add(driver);
        }
    }


    @FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            //System.out.println("Faszba már");
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-driver-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create person");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadDrivers();
                }catch (IOException e){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Database load failed");
                    alert.showAndWait();
                }
            });
        }catch (IOException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error in the form");
            alert.showAndWait();
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {

    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
    }
}