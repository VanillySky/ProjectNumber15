/**
 * Sample Skeleton for 'SubmittedExam.fxml' Controller Class
 */

package gui;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SubmittedExamController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ExamCodeAndSubject"
    private Label ExamCodeAndSubject; // Value injected by FXMLLoader

    @FXML // fx:id="StudentMenuBTN"
    private Button StudentMenuBTN; // Value injected by FXMLLoader

    @FXML // fx:id="OutBTN"
    private Button OutBTN; // Value injected by FXMLLoader

    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/SubmittedExam.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("SUBMITTED");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void BackToMainMenu(ActionEvent event) {
    	StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void SignOut(ActionEvent event) throws Exception {
    	ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ExamCodeAndSubject != null : "fx:id=\"ExamCodeAndSubject\" was not injected: check your FXML file 'SubmittedExam.fxml'.";
        assert StudentMenuBTN != null : "fx:id=\"StudentMenuBTN\" was not injected: check your FXML file 'SubmittedExam.fxml'.";
        assert OutBTN != null : "fx:id=\"OutBTN\" was not injected: check your FXML file 'SubmittedExam.fxml'.";

    }
}

	