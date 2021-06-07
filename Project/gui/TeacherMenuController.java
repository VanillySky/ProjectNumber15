package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
			loader.setLocation(getClass().getResource("/GUI/TeacherMain.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Teacher Menu");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void ExamIn(ActionEvent event) {
		ExamsTableController ETCC = new ExamsTableController();
		ETCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}// In to ExamsTable.fxml a

	@FXML
	public void QuestionIn(ActionEvent event) {
		BuildQuestionsController BQCC = new BuildQuestionsController();
		BQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	public void StatisticIn(ActionEvent event) {
		TeacherExamStatisticsController TES = new TeacherExamStatisticsController();
		TES.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}
}
