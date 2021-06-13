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

/**unsubmiited exam :if the student didn't finish the exam before the time is done, o
 *or the teacher locked the exam
 * @author Ibrahim Qassem
 * 
 */
public class UnsubmittedExamController {

    @FXML
    private Label ExamCodeAndSubject;

    @FXML
    private Button MainMenu;

    @FXML
    private Button signout;

    @FXML
    private Button CEMSbutton;

    /**
     * @param primaryStage to launch the UnsubmittedExam fxml whenever we call this method start 
     */
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
    
    
    /**
     * @param it returns the student to the menu page
     */
    @FXML
    void PressCEMS(ActionEvent event) {
    	StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

    }

    /**
     * @param it returns the student to the menu page
     */ 
    @FXML
    void PressMainMenu(ActionEvent event) {
    	StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

    }

    /**
     * @param event
     * @throws Exception it logs out from the system and throws exception if there's a problem with javafx or server
     */
    @FXML
    void PressSignOut(ActionEvent event) throws Exception {
    	LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
    }

}