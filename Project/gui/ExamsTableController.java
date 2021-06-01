package gui;

import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;
import entities.Exam;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamsTableController extends Application implements Initializable {

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

	private Exam selectedExam = null;

	private final ObservableList<Exam> dataList = FXCollections.observableArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/GUI/ExamsTable.fxml"));
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

		ExamCodeTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamCodeTable"));
		ExamNumberTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamNumberTable"));
		SubjectTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("SubjectTable"));
		CourseTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("CourseTable"));
		ChoseQuestionNumberTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ChoseQuestionNumberTable"));
		QuestionPointsTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("QuestionPointsTable"));
		ExamTimeTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamTimeTable"));
		TeacherNameTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("TeacherNameTable"));
		StudentInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("StudentInstructionTable"));
		TeacherInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("TeacherInstructionTable"));

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
	public void PressCEMS() {
		CEMSButton.setOnAction(event -> {
			CEMSButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/TeacherMain.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

	@FXML
	public void AddNewExam() {
	//	BuildNewExamController.changetoNew();
		AddNewExamBTN.setOnAction(event -> {
			AddNewExamBTN.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/BuildNewExam.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});

	}

	@FXML
	public void UpdateExam() {
   	if(selectedExam!=null) {
    		Exam exam = new Exam(selectedExam.getExamCode(),selectedExam.getExamNumber(), selectedExam.getExamSubject(),
   				selectedExam.getExamCourse(),selectedExam.getExamTime(), selectedExam.getTeacherName(), selectedExam.getChosenQuestion(), 
   				selectedExam.getQuestionPoint(),selectedExam.getTeacherInstructions() , selectedExam.getStudentInstructions());
    		BuildNewExamController.changetoupdate();
    		
    		UpdateExamBTN.setOnAction(event -> {
			CEMSButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/TeacherMain.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
    		
    		BuildNewExamController.UpdateExam(exam);
    

		 }

	}

	@FXML
	public void DeleteExam(ActionEvent event) {

		if (selectedExam != null) {
			Exam exam = new Exam(selectedExam.getExamCode(),selectedExam.getExamNumber(), selectedExam.getExamSubject(),
	   				selectedExam.getExamCourse(),selectedExam.getExamTime(), selectedExam.getTeacherName(), selectedExam.getChosenQuestion(), 
	   				selectedExam.getQuestionPoint(),selectedExam.getTeacherInstructions() , selectedExam.getStudentInstructions());
			ExamTable.getItems().removeAll(exam);
		}
	}

	@FXML
	void selectSale(MouseEvent event) {
		if (ExamTable.getSelectionModel().getSelectedItem() != null) {
			selectedExam = ExamTable.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	public void SignOut() {
		OutButton.setOnAction(event -> {
			OutButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/LoginFrame.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

}
