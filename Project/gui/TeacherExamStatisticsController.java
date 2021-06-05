package gui;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
		private Button CEMSButton;
	    
	    @FXML
	    private Button GetReportBTN;

	    @FXML
	    private Button ReturnBTN;

	    @FXML
	    private Button SignOutBTN;
	    @FXML
	    private Button SearchBTN;
	    
	    private Exam selectedExam=null;
	    
	    private final ObservableList<Exam> dataList = FXCollections.observableArrayList();
	    
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
	    	
			
		}
	    
	    @FXML
		public void SignOut(ActionEvent event) {
			LoginFrameController LFCC = new LoginFrameController();
			LFCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	    
	    @FXML
		void selectExam(MouseEvent event) {
			if (ExamsTable.getSelectionModel().getSelectedItem() != null) {
				selectedExam = ExamsTable.getSelectionModel().getSelectedItem();
			}
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			ExamCodeTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamCodeTable"));
			ExamNumberTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamNumberTable"));
			SubjectTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("SubjectTable"));
			CourseTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("CourseTable"));
			ExamTimeTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamTimeTable"));
			AuthorTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("AuthorTable"));
			ChoseQuestionNumberTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("ChoseQuestionNumberTable"));
			QuestionPointsTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("QuestionPointsTable"));
			StudentInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("StudentInstructionTable"));
			TeacherInstructionTable.setCellValueFactory(new PropertyValueFactory<Exam, String>("TeacherInstructionTable"));		
		}
}
