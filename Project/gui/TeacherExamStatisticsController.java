package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class TeacherExamStatisticsController {
	
	    @FXML
	    private TextField SearchByCourseNameTXT;

	    @FXML
	    private TableColumn<?, ?> ExamNumberTable;

	    @FXML
	    private TableColumn<?, ?> ExamCodeTable;

	    @FXML
	    private TableColumn<?, ?> ChoseQuestionNumberTable;

	    @FXML
	    private TableColumn<?, ?> ExamTimeTable;

	    @FXML
	    private TableColumn<?, ?> QuestionPointsTable;

	    @FXML
	    private TableColumn<?, ?> SubjectTable;

	    @FXML
	    private TableColumn<?, ?> CourseTable;

	    @FXML
	    private TableColumn<?, ?> StudentInstructionTable;
	    @FXML
	    private TableColumn<?, ?> TeacherInstructionTable;
	    @FXML
	    private TableColumn<?, ?> AuthorTable;
	    @FXML
	    private TableView<entities.Exam> ExamsTable;
	    
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
}
