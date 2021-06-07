package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentMenuController {

	/**
	 * 
	 */
	@FXML
	private ResourceBundle resources;

	/**
	 * 
	 */
	@FXML
	private URL location;

	/**
	 * 
	 */
	@FXML
	private Button OutButton;

	/**
	 * 
	 */
	@FXML
	private Button StatisticsButton;

	/**
	 * 
	 */
	@FXML
	private Button ExamButton;

	/**
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/StudentMenuFrame.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student Menu");
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
		ExaminationController EC = new ExaminationController();
		EC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}


	
	public void StatisticIn(ActionEvent event) {
		StudentGradeListController SGS = new StudentGradeListController();
		SGS.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}
	
}