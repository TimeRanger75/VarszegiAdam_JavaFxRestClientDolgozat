package hu.petrik.varszegiadam_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateDriverController {
    @FXML
    private CheckBox retiredCheck;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField teamField;
    @FXML
    private TextField driverField;
    @FXML
    private Spinner<Integer> startField;

    private Driver driver;

    @FXML
    private void initialize(){
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(2002, LocalDate.now().getYear(), 2007);
        startField.setValueFactory(valueFactory);
    }

    public void setDriver(Driver driver){
        this.driver=driver;
        driverField.setText(this.driver.getDriver_name());
        teamField.setText(this.driver.getTeam_name());
        startField.getValueFactory().setValue(this.driver.getStart_Year());
        retiredCheck.setSelected(this.driver.isRetired());
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String driver_name=driverField.getText().trim();
        String team_name=teamField.getText().trim();
        int start=startField.getValue();
        Boolean retired=retiredCheck.isSelected();
        if (driver_name.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Name required");
            alert.showAndWait();
            return;
        }
        if (team_name.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Team required");
            alert.showAndWait();
            return;
        }

        this.driver.setDriver_name(driver_name);
        this.driver.setTeam_name(team_name);
        this.driver.setStart_Year(start);
        this.driver.setRetired(retired);
        Gson converter=new Gson();
        String json=converter.toJson(this.driver);
        try {
            String url = App.BASE_URL + "/" + this.driver.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateBtn.getScene().getWindow();
                stage.close();
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error occured in the update ");
                alert.showAndWait();
            }
        } catch (IOException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cannot connect to the server");
            alert.showAndWait();
        }
    }
}
