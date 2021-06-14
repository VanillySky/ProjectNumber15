package gui;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * @author axwara1
 * this class the menu of Manager , he can see statics and Confirmations the asks 
 *
 */
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

	/**
	 * @param primaryStage
	 * this method start the ManagerMenu FXML
	 */
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

	
	/**
	 * @param event
	 * go to Confirmations
	 */
	@FXML
	void GoToConfirmations(ActionEvent event) {
		ManagerApprovalController MACC = new ManagerApprovalController();
		MACC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
		
	}

	/**
	 * @param event
	 * press the CEMS logo and go to Manager menu
	 */
	@FXML
	void PresCEMS(ActionEvent event) {
		ManagerMenuController MMC = new ManagerMenuController();
		MMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * @throws Exception
	 * sign out from CEMS
	 */
	@FXML
	void PressSignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());

	}

	/**
	 * @param event
	 * go to Manager Statistics
	 */
	@FXML
	void GoToStatistics(ActionEvent event) {
		ManagerStatisticsController MSC = new ManagerStatisticsController();
		MSC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}
