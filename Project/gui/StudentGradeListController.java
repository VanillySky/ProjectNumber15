package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import entities.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentGradeListController implements Initializable {
	@FXML
	private TableView<StudentGrade> GradeTable;

	@FXML
	private TableColumn<StudentGrade, String> ExamCodeColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamCourseColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamGradeColumn;

	@FXML
	private TableColumn<StudentGrade, String> TeacherNamecolumn;
	
	 @FXML
	    private TableColumn<StudentGrade, String> InstructionColumn;

	@FXML
	private Button GetExamNotbookBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Button CEMSButton;
	
	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();

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
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.ExamCodeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamCourseColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGradeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.TeacherNamecolumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.InstructionColumn.setCellValueFactory((Callback) new PropertyValueFactory("instr"));
		
		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowApprovedStudentGrade(ChatClient.currentUser.getUserName()));
				GradeTable.setItems(dataList);

	}

}
