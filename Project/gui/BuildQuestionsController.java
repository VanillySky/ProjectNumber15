package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuildQuestionsController {

    @FXML
    private TableView<?> QuestionTable;

    @FXML
    private TableColumn<?, ?> QuestNum;

    @FXML
    private TableColumn<?, ?> QuestCode;

    @FXML
    private TableColumn<?, ?> Course;

    @FXML
    private TableColumn<?, ?> Question;

    @FXML
    private TableColumn<?, ?> QuestInst;

    @FXML
    private TableColumn<?, ?> Answers;

    @FXML
    private TableColumn<?, ?> RightAns;

    @FXML
    private TableColumn<?, ?> Author;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button DeleteQuestionButton;

    @FXML
    private Button UpdateQuestionButton;

    @FXML
    private Button AddQuestionButton;

    @FXML
    private Button SignOutButton;

    @FXML
    private Button SerchByCourseButton;

    @FXML
    private Button SerchByTeacherButton;

    @FXML
    private TextField CourseNameTXT;

    @FXML
    private TextField teacherNameTXT;
    
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
    	SignOutButton.setOnAction(event -> {
    		SignOutButton.getScene().getWindow().hide();

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
