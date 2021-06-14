package gui;

import java.net.URL;


import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Exam;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author axwara1
 * this class for the students , he can see all his approvals grades 
 *
 */
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
	private Button GetExamNoteBook;

	@FXML
	private Button GetExamNotbookBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Button CEMSButton;
	 @FXML
	    private Label ERRLBL;
	
	private StudentGrade selectedGrade = null;

	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();

	/**
	 * this method to open studentGradeList FXML
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

	
	/**
	 * @param event
	 * this method help to select a grade
	 */
	@FXML
	void selectGrade(MouseEvent event) {
		if (GradeTable.getSelectionModel().getSelectedItem() != null) {
		selectedGrade = GradeTable.getSelectionModel().getSelectedItem();
		
		}
	}
	
	
	/**
	 * @param event
	 * press getExam to get the copy of exam
	 */
	@FXML
	void PressGetExam(ActionEvent event) {
		if(selectedGrade!=null) {
			if(!selectedGrade.getInstr().equals("rejected")) {
				NoteBookController.ExamCode=selectedGrade.getExamCode();
				NoteBookController NB = new NoteBookController();
				NB.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();

				
				
			}else {
				ERRLBL.setText("the exam is rejected you cant see its NoteBook");
				ERRLBL.setVisible(true);
			}
		}else
		{
			ERRLBL.setText("Please select a Grade");
			ERRLBL.setVisible(true);
		}

		
		
	}

	/**
	 * @param event
	 * press to logo to go to StudentMenu
	 */
	@FXML
	void PressCEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * @throws Exception
	 * press to sign out
	 */
	@FXML
	void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 *start this class by put all the Grades in the Table 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.ExamCodeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamCourseColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGradeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.TeacherNamecolumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.InstructionColumn.setCellValueFactory((Callback) new PropertyValueFactory("instr"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController
				.ShowApprovedStudentGrade(ChatClient.currentUser.getUserName()));
		GradeTable.setItems(dataList);

	}

}
