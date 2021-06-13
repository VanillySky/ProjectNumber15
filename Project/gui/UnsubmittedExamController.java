package gui;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UnsubmittedExamController {

    @FXML
    private Label ExamCodeAndSubject;

    @FXML
    private Button MainMenu;

    @FXML
    private Button signout;

    @FXML
    private Button CEMSbutton;

    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/UnsubmittedExam.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Unsubmitted Exam");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    @FXML
    void PressCEMS(ActionEvent event) {
    	StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void PressMainMenu(ActionEvent event) {
    	StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void PressSignOut(ActionEvent event) throws Exception {
    	LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
    }

}