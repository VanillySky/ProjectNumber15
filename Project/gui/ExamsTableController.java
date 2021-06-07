package gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DeleteController;
import controllers.DisplayController;

import java.net.URL;
import entities.Exam;
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
import server.SQLConnection;

public class ExamsTableController implements Initializable {

	@FXML
	private TableView<Exam> ExamTable;

	@FXML
	private TextField SerchByTeacherNameTXT;

	@FXML
	private TextField SerchByCourseNameTXT;

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
	private TableColumn<Exam, String> TeacherNameTable;

	@FXML
	private TableColumn<Exam, String> CourseTable;

	@FXML
	private TableColumn<Exam, String> StudentInstructionTable;

	@FXML
	private TableColumn<Exam, String> TeacherInstructionTable;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button AddNewExamBTN;

	@FXML
	private Button UpdateExamBTN;

	@FXML
	private Button DeleteExamBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Label LabelERR;

	@FXML
	private Button SearchByTeacherBTN;

	@FXML
	private Button SearchBycourseBTN;

	private Exam selectedExam = null;

	private ObservableList<Exam> dataList = FXCollections.observableArrayList();
	private ObservableList<Exam> dataList1 = FXCollections.observableArrayList();

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ExamsTable.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Exams Table");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void SearchByTeacher(ActionEvent event) {

		this.ExamCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamSubject"));
		this.CourseTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamTimeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamTime"));
		this.TeacherNameTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		this.ChoseQuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ChosenQuestion"));
		this.QuestionPointsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionPoint"));
		this.StudentInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("StudentInstructions"));
		this.TeacherInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherInstructions"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowExams());
		ExamTable.setItems(dataList);

		FilteredList<Exam> filteredData = new FilteredList<Exam>(dataList, b -> true);
		SerchByTeacherNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Exam -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Exam.getTeacherName().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Exam> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(ExamTable.comparatorProperty());
		ExamTable.setItems(sortedData);
	}

	@FXML
	public void SearchByCourse(ActionEvent event) {

		this.ExamCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamSubject"));
		this.CourseTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamTimeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamTime"));
		this.TeacherNameTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		this.ChoseQuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ChosenQuestion"));
		this.QuestionPointsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionPoint"));
		this.StudentInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("StudentInstructions"));
		this.TeacherInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherInstructions"));

		dataList1 = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowExams());
		ExamTable.setItems(dataList1);

		FilteredList<Exam> filteredData = new FilteredList<Exam>(dataList1, b -> true);
		SerchByCourseNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
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
		sortedData.comparatorProperty().bind(ExamTable.comparatorProperty());
		ExamTable.setItems(sortedData);

	}

	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void AddNewExam(ActionEvent event) {
		BuildNewExamController BNECC = new BuildNewExamController();
		BNECC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void UpdateExam(ActionEvent event) {
		if (selectedExam != null) {
			Exam exam = new Exam(selectedExam.getExamCode(), selectedExam.getExamNumber(),
					selectedExam.getExamSubject(), selectedExam.getExamCourse(), selectedExam.getExamTime(),
					selectedExam.getTeacherName(), selectedExam.getChosenQuestion(), selectedExam.getQuestionPoint(),
					selectedExam.getTeacherInstructions(), selectedExam.getStudentInstructions());
			BuildNewExamController updateExam = new BuildNewExamController();

			BuildNewExamController BNECC = new BuildNewExamController();
			BNECC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();

			updateExam.UpdateExam(exam);

		} else {
			LabelERR.setText("please chose any Exam first to Update!!");
			LabelERR.setVisible(true);
		}
	}

	@FXML
	public void DeleteExam(ActionEvent event) {
		DeleteController DC = new DeleteController();
		DC.DeleteExam(selectedExam.getExamCode());

		this.ExamTable
				.setItems(FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowExams()));
		this.ExamTable.refresh();
	}

	@FXML
	void selectExam(MouseEvent event) {
		if (ExamTable.getSelectionModel().getSelectedItem() != null) {
			selectedExam = ExamTable.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		this.ExamCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.ExamNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamSubject"));
		this.CourseTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamTimeTable.setCellValueFactory((Callback) new PropertyValueFactory("ExamTime"));
		this.TeacherNameTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		this.ChoseQuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("ChosenQuestion"));
		this.QuestionPointsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionPoint"));
		this.StudentInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("StudentInstructions"));
		this.TeacherInstructionTable.setCellValueFactory((Callback) new PropertyValueFactory("TeacherInstructions"));
		if (controllers.DisplayController.ShowExams() != null) {
			this.ExamTable.setItems(
					FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowExams()));
		}
	}
}
