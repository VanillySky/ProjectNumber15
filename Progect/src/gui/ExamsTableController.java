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

public class ExamsTableController {

    @FXML
    private TextField SerchByTeacherNameTXT;

    @FXML
    private TextField SerchByCourseNameTXT;

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
    public void AddNewExam() {
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
    public void UpdateExam() {
    	
    }
    
    @FXML
    public void DeleteExam() {
    	
    }
    
    @FXML
    public void SignOut() {
    	OutButton.setOnAction(event -> {
      		 OutButton.getScene().getWindow().hide();

      			FXMLLoader loader = new FXMLLoader();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      			loader.setLocation(getClass().getResource("/gui/Signin.fxml"));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
