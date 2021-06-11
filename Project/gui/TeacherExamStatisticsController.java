package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DisplayController;
import entities.Exam;
import entities.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class TeacherExamStatisticsController implements Initializable {

	@FXML
	private TextField SearchByCourseNameTXT;

	@FXML
	private TableColumn<Exam, String> ExamNumberTable;

	@FXML
	private TableColumn<Exam, String> ExamCodeTable;

	@FXML
	private TableColumn<Exam, String> ChoseQuestionNumberTable;

	@FXML
	private TableColumn<Exam, String> ExamTimeTable;

	@FXML
	private TableColumn<Exam, String> QuestionPointsTable;

	@FXML
	private TableColumn<Exam, String> SubjectTable;

	@FXML
	private TableColumn<Exam, String> CourseTable;

	@FXML
	private TableColumn<Exam, String> StudentInstructionTable;
	@FXML
	private TableColumn<Exam, String> TeacherInstructionTable;
	@FXML
	private TableColumn<Exam, String> AuthorTable;
	@FXML
	private TableView<Exam> ExamsTable;

	@FXML
	private Label ErrorLbl;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button GetReportBTN;

	@FXML
	private Button ReturnBTN;

	@FXML
	private Button SignOutBTN;
	@FXML
	private Button SearchBTN;

	@FXML
	private Button ApprovalBTN;

	@FXML
	private Button StatusBTN;

	private Exam selectedExam = null;

	private ObservableList<Exam> dataList = FXCollections.observableArrayList();
	private ObservableList<StudentGrade> AllExamGrades = FXCollections.observableArrayList();
	private ArrayList<StudentGrade> ExamGrades = new ArrayList<StudentGrade>();
	private int[] Grades;
	boolean NoGrades;
	static String Examcode;
	static String Durition;

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/TeacherExamsStatistics.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Teacher Exam Statics");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void Search(ActionEvent event) {
		FilteredList<Exam> filteredData = new FilteredList<Exam>(dataList, b -> true);

		SearchByCourseNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Exam -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Exam.getExamCourse().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Exam> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(ExamsTable.comparatorProperty());
		ExamsTable.setItems(sortedData);
	}

	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void PressBack(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void PressReport(ActionEvent event) {
		if (selectedExam != null) {
			TeacherExamReportController.isTeacher=true;
			AllExamGrades = FXCollections
					.observableArrayList((Collection) controllers.DisplayController.ShowApprovedStudentTeacher(ChatClient.currentUser.getUserName()));
			System.out.println(AllExamGrades.size());
			for (int i = 0; i < AllExamGrades.size(); i++) {
				if (selectedExam.getExamCode().equals(AllExamGrades.get(i).getExamCode()))
					ExamGrades.add(AllExamGrades.get(i));
			}
			
			if (ExamGrades.size() == 0)
				NoGrades = true;
			else
				NoGrades = false;

			if (NoGrades == false) {
				Grades = new int[ExamGrades.size()];

				TeacherExamReportController.numberofStudents = ExamGrades.size();
				int sum = 0;
				for (int i = 0; i < ExamGrades.size(); i++) {
					Grades[i] = Integer.parseInt(ExamGrades.get(i).getExamGrade());
					sum += Integer.parseInt(ExamGrades.get(i).getExamGrade());
				}
				Arrays.sort(Grades);
				TeacherExamReportController.MinGrade = Grades[0];
				TeacherExamReportController.MaxGrade = Grades[Grades.length - 1];
				TeacherExamReportController.Average = sum / (double) Grades.length;

				if (Grades.length % 2 == 1)
					TeacherExamReportController.median = Grades[(Grades.length + 1) / 2 - 1];
				else
					TeacherExamReportController.median = (Grades[Grades.length / 2 - 1] + Grades[Grades.length / 2])
							/ 2;

				for (int i = 0; i < Grades.length; i++) {
					if (Grades[i] >= 95 && Grades[i] <= 100)
						TeacherExamReportController.GradeRange[0]++;

					if (Grades[i] >= 90 && Grades[i] <= 94)
						TeacherExamReportController.GradeRange[1]++;

					if (Grades[i] >= 85 && Grades[i] <= 89)
						TeacherExamReportController.GradeRange[2]++;

					if (Grades[i] >= 80 && Grades[i] <= 84)
						TeacherExamReportController.GradeRange[3]++;

					if (Grades[i] >= 75 && Grades[i] <= 79)
						TeacherExamReportController.GradeRange[4]++;

					if (Grades[i] >= 70 && Grades[i] <= 74)
						TeacherExamReportController.GradeRange[5]++;

					if (Grades[i] >= 65 && Grades[i] <= 69)
						TeacherExamReportController.GradeRange[6]++;

					if (Grades[i] >= 55 && Grades[i] <= 64)
						TeacherExamReportController.GradeRange[7]++;

					if (Grades[i] >= 0 && Grades[i] <= 54)
						TeacherExamReportController.GradeRange[8]++;
				}

				TeacherExamReportController TERC = new TeacherExamReportController();
				TERC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();
			} else {
				ErrorLbl.setText("there is no grades");
				ErrorLbl.setVisible(true);
			}
		} else {
			ErrorLbl.setText("please chose any exam!!");
			ErrorLbl.setVisible(true);
		}

	}

	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@FXML
	void selectExam(MouseEvent event) {
		if (ExamsTable.getSelectionModel().getSelectedItem() != null) {
			selectedExam = ExamsTable.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	void PressApproval(ActionEvent event) {
		ExamApprovalController EACC = new ExamApprovalController();
		EACC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	@FXML
	void PressStatus(ActionEvent event) {
		if (selectedExam != null) {
			Examcode=selectedExam.getExamCode();
			Durition=selectedExam.getExamTime();
			StatusController SCC = new StatusController();
			SCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
			
		}else {
			ErrorLbl.setText("please chose any exam!!");
			ErrorLbl.setVisible(true);	
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.ExamCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamSubject"));
		this.CourseTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamTimeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamTime"));
		this.AuthorTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		this.ChoseQuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ChosenQuestion"));
		this.QuestionPointsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionPoint"));
		this.StudentInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("StudentInstructions"));
		this.TeacherInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherInstructions"));

		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowTeacherExams(ChatClient.currentUser.getFirstName()));
		ExamsTable.setItems(dataList);

		for (int i = 0; i < 9; i++) {
			TeacherExamReportController.GradeRange[i] = 0;
		}

	}
}