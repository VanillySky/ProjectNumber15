package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerStatisticsController {

    @FXML
    private TextField SearchStudentTXT;

    @FXML
    private TextField SearchCourseTXT;

    @FXML
    private TextField SearchTeacherTXT;

    @FXML
    private TableColumn<?, ?> StudentNameCol;

    @FXML
    private TableColumn<?, ?> ExamCodeCol;

    @FXML
    private TableColumn<?, ?> QuestionsNumCol;

    @FXML
    private TableColumn<?, ?> ExamTimeCol;

    @FXML
    private TableColumn<?, ?> QuestionsPointsCol;

    @FXML
    private TableColumn<?, ?> SubjectCol;

    @FXML
    private TableColumn<?, ?> CourseCol;

    @FXML
    private TableColumn<?, ?> StudentInstructionsCol;

    @FXML
    private TableColumn<?, ?> TeacherInstructionsCol;

    @FXML
    private TableColumn<?, ?> AuthorCol;

    @FXML
    private Button CEMSBTN;

    @FXML
    private Button SearchStudentBTN;

    @FXML
    private Button SearchCourseBTN;

    @FXML
    private Button SearchTeacherBTN;

    @FXML
    private Button SignOutBTN;

    @FXML
    private Button GetReportBTN;

    @FXML
    private Button ReturnBTN;

 public void start(Stage primaryStage) throws Exception {
    	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ManagerStatistics.fxml"));
		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.show();
	}
 
 
 public void PressReturn() {
	 ReturnBTN.setOnAction(event -> {
			ReturnBTN.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/ManagerMenu.fxml"));

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
 
 public void PressCEMS() {
	 CEMSBTN.setOnAction(event -> {
		 CEMSBTN.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/ManagerMenu.fxml"));

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