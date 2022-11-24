package hu.petrik.varszegiadam_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateDriverController {
    @FXML
    private TextField driverField;
    @FXML
    private TextField teamField;
    @FXML
    private Spinner<Integer> startField;
    @FXML
    private CheckBox retiredCheck;
    @FXML
    private Button uploadBtn;

    @FXML
    private void initialize(){
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(2002, 2020, 2007);
        startField.setValueFactory(valueFactory);
    }

    @FXML
    public void uploadClick(ActionEvent actionEvent) {
        String driver_name=driverField.getText().trim();
        String team_name=teamField.getText().trim();
        int start=startField.getValue();
        boolean retired=false;
        if (driver_name.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Name is required");
            alert.setTitle("Warning");
            alert.showAndWait();
            return;
        }
        if (team_name.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Team is required");
            alert.setTitle("Warning");
            alert.showAndWait();
            return;
        }
        if (team_name.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Team is required");
            alert.setTitle("Warning");
            alert.showAndWait();
            return;
        }
        if (retiredCheck.isSelected()){
            retired=true;
            return;
        }

        Driver newDriver=new Driver(0, driver_name, team_name, retired, start);
        Gson converter=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json=converter.toJson(newDriver);
        try {
            Response response=RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode()==201){
                driverField.setText("");
                teamField.setText("");
                startField.getValueFactory().setValue(2006);
            }else {
                Alert alert =new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error in the upload");
                alert.setTitle("Warning");
                alert.showAndWait();
            }
        }catch (IOException e){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cannot connect to the server");
            alert.setTitle("ERROR");
            alert.showAndWait();
        }
    }
}
