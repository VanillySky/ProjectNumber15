package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Shaden
 *
 */
/**
 * @author Ibrahim Qassem
 *
 */
public class TeacherMenuController {

	/**
	 * 
	 */
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	/**
	 * Button for getting back to the main menu
	 */
	@FXML
	private Button CEMSButton;

	/**
	 * 
	 */
	@FXML
	private Button OutButton;

	@FXML
	private Button StatisticsButton;

	@FXML
	private Button QuestionButton;

	@FXML
	private Button ExamButton;

	/**
	 * The method is the main entry point for JavaFX applications.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/TeacherMain.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Teacher Menu");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param event
	 * @throws Exception this method logs out from the system and updates the connection situation
	 */
	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * @param event this function opens the exam from the teacher  menu
	 */
	@FXML
	public void ExamIn(ActionEvent event) {
		ExamsTableController ETCC = new ExamsTableController();
		ETCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}// In to ExamsTable.fxml a

	/**
	 * @param event this function opens the question from the teacher  menu
	 */
	@FXML
	public void QuestionIn(ActionEvent event) {
		BuildQuestionsController BQCC = new BuildQuestionsController();
		BQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	/**
	 * @param event this function opens the statistics from the teacher  menu
	 */
	public void StatisticIn(ActionEvent event) {
		TeacherExamStatisticsController TES = new TeacherExamStatisticsController();
		TES.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}
}
