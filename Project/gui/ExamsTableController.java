package gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DisplayExams;

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
	private Button CourseNameSerchBTN;

	@FXML
	private Button TeacherNameSerchBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Label LabelERR;

	private Exam selectedExam = null;

	private final ObservableList<Exam> dataList = FXCollections.observableArrayList();

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

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		/*
		 * ExamCodeTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("ExamCodeTable")); ExamNumberTable.setCellValueFactory(new
		 * PropertyValueFactory<Exam, String>("ExamNumberTable"));
		 * SubjectTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("SubjectTable")); CourseTable.setCellValueFactory(new
		 * PropertyValueFactory<Exam, String>("CourseTable"));
		 * ExamTimeTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("ExamTimeTable")); TeacherNameTable.setCellValueFactory(new
		 * PropertyValueFactory<Exam, String>("TeacherNameTable"));
		 * ChoseQuestionNumberTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("ChoseQuestionNumberTable"));
		 * QuestionPointsTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("QuestionPointsTable"));
		 * StudentInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("StudentInstructionTable"));
		 * TeacherInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam,
		 * String>("TeacherInstructionTable"));
		 * this.ExamTable.setItems(FXCollections.observableArrayList((Collection)
		 * controllers.DisplayExams.ShowExams())); this.ExamTable.refresh();
		 */

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
		this.ExamTable.setItems(FXCollections.observableArrayList((Collection) controllers.DisplayExams.ShowExams()));
	}

	@FXML
	public void Search(ActionEvent event) {

		FilteredList<Exam> filteredData = new FilteredList<Exam>(dataList, b -> true);

		if (TeacherNameSerchBTN.isPressed()) {
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

		} else {
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
		}

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

		if (selectedExam != null) {
			Exam exam = new Exam(selectedExam.getExamCode(), selectedExam.getExamNumber(),
					selectedExam.getExamSubject(), selectedExam.getExamCourse(), selectedExam.getExamTime(),
					selectedExam.getTeacherName(), selectedExam.getChosenQuestion(), selectedExam.getQuestionPoint(),
					selectedExam.getTeacherInstructions(), selectedExam.getStudentInstructions());
			ExamTable.getItems().removeAll(exam);
		} else {
			LabelERR.setText("please chose any Exam first to Delete!!");
			LabelERR.setVisible(true);

		}
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
}
