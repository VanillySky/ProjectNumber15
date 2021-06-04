package gui;
import java.io.IOException;

import entities.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	    private Button GetReportBTN;

	    @FXML
	    private Button ReturnBTN;

	    @FXML
	    private Button SignOutBTN;
	    @FXML
	    private Button SearchBTN;
	    
	    @FXML
	    public void SignOut() 
	    {
	    	SignOutBTN.setOnAction(event -> {
	    		SignOutBTN.getScene().getWindow().hide();

	      			FXMLLoader loader = new FXMLLoader();
	      			loader.setLocation(getClass().getResource("/gui/Signin.fxml"));
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
