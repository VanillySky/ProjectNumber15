package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ManagerMenu.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Manager");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void GoToConfirmations(ActionEvent event) {

	}

	@FXML
	void GoToInfo(ActionEvent event) {

	}

	@FXML
	void PresCEMS(ActionEvent event) {
		ManagerStatisticsController MSCC = new ManagerStatisticsController();
		MSCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void PressSignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	
	}


	@FXML
	void GoToStatistics(ActionEvent event) {
		ManagerStatisticsController MSC = new ManagerStatisticsController();
		MSC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
			}// go to Manager Statistics

}
