package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerMenuController {

    @FXML
    private Button Informationbtn;

    @FXML
    private Button Confirmationsbtn;

    @FXML
    private Button Statisticsbtn;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button OutButton;
    
    
   
    
    @FXML
   void GoToStatistics() {
	   Statisticsbtn.setOnAction(event -> {
				Statisticsbtn.getScene().getWindow().hide();

				FXMLLoader loader = new FXMLLoader();

				loader.setLocation(getClass().getResource("/gui/ManagerStatistics.fxml"));

				try {
					loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Parent root = loader.getRoot();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.showAndWait();
			});
   }// go to Manager Statistics


}
