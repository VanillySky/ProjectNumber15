package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.DeleteController;
import controllers.LoginController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author saher
 * this class for teacher , when the student done the exam , the grade appear in this table , the teacher need to approve it to go for student back
 *
 */
public class ExamApprovalController implements Initializable {

	@FXML
	private TableView<StudentGrade> GradeTable;

	@FXML
	private TableColumn<StudentGrade, String> StudentNameColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamCodeColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamCourseColumn;

	@FXML
	private TableColumn<StudentGrade, String> ExamGradeColumn;

	@FXML
	private TextField NewGradeTXT;

	@FXML
	private TextField InstructionTXT;

	@FXML
	private Button CEMSBTN;

	@FXML
	private Button ReturnBTN;

	@FXML
	private Button SignOutBTN;

	@FXML
	private Button ApproveBTN;

	@FXML
	private Button DIsapprove;

	@FXML
	private Label ErrorLbl;

	private StudentGrade selectedGrade = null;
	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();

	/**
	 * @param primaryStage
	 * this method start the ExamApproval FXML
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ExamApproval.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("ExamApproval");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @param event
	 * if we press the CEMS logo we go to TeacherMenu
	 */
	 
	@FXML
	void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * if we press approve the grade go back for the student , if you change the grade you need to put instruction
	 */
	@FXML
	void PressApprove(ActionEvent event) {
		int count = 0;
		if (selectedGrade != null) {
			if (!NewGradeTXT.getText().equals("") && InstructionTXT.getText().equals("")) {
				ErrorLbl.setText("Pleaes write Instructions");
				ErrorLbl.setVisible(true);
				count++;
			}

			if (!NewGradeTXT.getText().equals("")) {

				try {
					Integer.parseInt(NewGradeTXT.getText());

					if (Integer.parseInt(NewGradeTXT.getText()) < 0
							&& (Integer.parseInt(NewGradeTXT.getText()) > 100)) {
						ErrorLbl.setText("illegal Grade 0-100");
						ErrorLbl.setVisible(true);
						count++;
					}

				} catch (NumberFormatException e) {
					ErrorLbl.setText("illegal Grade can't be chars");
					ErrorLbl.setVisible(true);
					count++;

				}

			}
			if (count == 0) {//no bugs , you can send the grade for student 

				if (!NewGradeTXT.getText().equals(""))
					selectedGrade.setExamGrade(NewGradeTXT.getText());
				StudentGrade SG = new StudentGrade(selectedGrade.getStudentUserName(), selectedGrade.getExamCode(),
						selectedGrade.getExamCourse(), selectedGrade.getExamGrade(),
						ChatClient.currentUser.getUserName(), InstructionTXT.getText());

				AddController.AddApprovalStudentGrade(SG);
				DeleteController DC = new DeleteController();
				DC.DeleteApprovalStudentGrade(selectedGrade.getStudentUserName(), selectedGrade.getExamCode());
				dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController
						.ShowStudentGradeTeacher(ChatClient.currentUser.getFirstName()));
				GradeTable.setItems(dataList);

			}

		} else {
			ErrorLbl.setText("please chose any student Grade");
			ErrorLbl.setVisible(true);
		}

	}

	/**
	 * @param event
	 * the teacher disapprove the grade , the student got grade 0 with rejected instruction
	 */
	@FXML
	void PressDisapprove(ActionEvent event) {
		int count = 0;
		if (selectedGrade != null) {

			if (InstructionTXT.getText().equals("")) {
				ErrorLbl.setText("Pleaes write Instructions");
				ErrorLbl.setVisible(true);
				count++;
			}

			if (count == 0) {
				StudentGrade SG = new StudentGrade(selectedGrade.getStudentUserName(), selectedGrade.getExamCode(),
						selectedGrade.getExamCourse(), "0", ChatClient.currentUser.getUserName(),InstructionTXT.getText());

				AddController.AddApprovalStudentGrade(SG);
				DeleteController DC = new DeleteController();
				DC.DeleteApprovalStudentGrade(selectedGrade.getStudentUserName(), selectedGrade.getExamCode());
				dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController
						.ShowStudentGradeTeacher(ChatClient.currentUser.getFirstName()));
				GradeTable.setItems(dataList);
			} else {
				ErrorLbl.setText("please chose any student Grade");
				ErrorLbl.setVisible(true);
			}
		}

	}

	/**
	 * @param event
	 * press return to go back in exam statics 
	 */
	@FXML
	void PressReturn(ActionEvent event) {
		TeacherExamStatisticsController TESC = new TeacherExamStatisticsController();
		TESC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	/**
	 * @param event
	 * @throws Exception
	 * press sign out to left the CEMS
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
	 * this method a mouse event that help us to select grade from the table
	 */
	@FXML
	void selectGrade(MouseEvent event) {
		if (GradeTable.getSelectionModel().getSelectedItem() != null) {
			selectedGrade = GradeTable.getSelectionModel().getSelectedItem();
		}
	}

	/**
	 *start this class by put all the student grade in the table
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.StudentNameColumn.setCellValueFactory((Callback) new PropertyValueFactory("studentUserName"));
		this.ExamCodeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamCourseColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGradeColumn.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController
				.ShowStudentGradeTeacher(ChatClient.currentUser.getFirstName()));
		GradeTable.setItems(dataList);

	}

}
