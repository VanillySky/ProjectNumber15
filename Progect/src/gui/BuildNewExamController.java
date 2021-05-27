package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuildNewExamController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button OutButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button AddQuestionsButton;

    @FXML
    private TextField ExamNumberField;

    @FXML
    private TextField ExamSubjectField;

    @FXML
    private TextField ExamCourseField;

    @FXML
    private TextField ExamTimeField;

    @FXML
    private TextField StudentInstructionField;

    @FXML
    private TextField TeacherInstructionField;

    
    @FXML
    public void PressBack() {
   	 BackButton.setOnAction(event -> {
   		 BackButton.getScene().getWindow().hide();

   			FXMLLoader loader = new FXMLLoader();

   			loader.setLocation(getClass().getResource("/gui/ExamsTable.fxml"));

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
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void AddQuestions() {
   	 AddQuestionsButton.setOnAction(event -> {
   		 AddQuestionsButton.getScene().getWindow().hide();

   			FXMLLoader loader = new FXMLLoader();

   			loader.setLocation(getClass().getResource("/gui/QuestionSelection.fxml"));

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
