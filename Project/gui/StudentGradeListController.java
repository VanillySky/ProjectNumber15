package gui;

import java.awt.event.ActionEvent;

import entities.StudentGrade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StudentGradeListController {
	@FXML
	private TableView<StudentGrade> GradeTable;

	@FXML
	private AnchorPane AcnPane;

	@FXML
	private TableColumn<StudentGrade, String> ExamCodeColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamCourseColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamGradeColumn;

	@FXML
	private Button GetExamNotbookBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Button CEMSButton;

	/**
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/StudentGradeList.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Grade list");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void PressCEMS(ActionEvent event) {

	}

	@FXML
	void SignOut(ActionEvent event) {

	}

}
