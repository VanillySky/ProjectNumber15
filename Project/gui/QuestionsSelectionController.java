package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class QuestionsSelectionController {
	
    @FXML
    private Button CEMSButton;

    @FXML
    private Button OutButton;

    @FXML
    private TableColumn<?, ?> QuestionNumberTable;

    @FXML
    private TableColumn<?, ?> QuestionCodeTable;

    @FXML
    private TableColumn<?, ?> CourseTable;

    @FXML
    private TableColumn<?, ?> QuestionTable;

    @FXML
    private TableColumn<?, ?> QuestionInstractionsTable;

    @FXML
    private TableColumn<?, ?> AnswersTable;

    @FXML
    private TableColumn<?, ?> RightAnswerTable;

    @FXML
    private TableColumn<?, ?> AuthorTable;

    @FXML
    private TableColumn<?, ?> QuestionNumberTable2;

    @FXML
    private TableColumn<?, ?> QuestionCodeTable2;

    @FXML
    private TableColumn<?, ?> CourseTable2;

    @FXML
    private TableColumn<?, ?> QuestionTable2;

    @FXML
    private TableColumn<?, ?> QuestionInstractionsTable2;

    @FXML
    private TableColumn<?, ?> AnswersTable2;

    @FXML
    private TableColumn<?, ?> RightAnswerTable2;

    @FXML
    private TableColumn<?, ?> AuthorTable2;

    @FXML
    private TableColumn<?, ?> PointsTable;

    @FXML
    private Button CourseNameSearchButton;

    @FXML
    private Button TeacherNameSearchButton;

    @FXML
    private Button AddnewQuestionBTN;

    @FXML
    private Button RemoveQuestionBTN;

    @FXML
    private Button ReturnBTN;

    @FXML
    private Button DoneBTN;

    
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
    public void AddNewQuestion() {
    	
    }
    
    @FXML
    public void RemoveQuestion() {
    	
    }
    
    @FXML
    public void PressReturn() {
    	 ReturnBTN.setOnAction(event -> {
       		 ReturnBTN.getScene().getWindow().hide();

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
    
    /////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void PressDone() {
   	 ReturnBTN.setOnAction(event -> {
      		 ReturnBTN.getScene().getWindow().hide();

      			FXMLLoader loader = new FXMLLoader();

      			loader.setLocation(getClass().getResource("/gui/ExamTable.fxml"));

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
}
