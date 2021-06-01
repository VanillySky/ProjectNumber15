package gui;

import java.io.IOException;

import entities.Exam;
import entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuestionsSelectionController {
	
	@FXML
    private Button CEMSButton;

    @FXML
    private Button OutButton;
    
    @FXML
    private TableView<?> Table;

    @FXML
    private TableColumn<?, ?> QuestionTable;

    @FXML
    private TableColumn<?, ?> QuestionNumberTable;

    @FXML
    private TableColumn<?, ?> QuestionCodeTable;

    @FXML
    private TableColumn<?, ?> SubjectTable;

    @FXML
    private TableColumn<?, ?> QuestionInstractionsTable;

    @FXML
    private TableColumn<?, ?> AnswersTable;

    @FXML
    private TableColumn<?, ?> RightAnswerTable;

    @FXML
    private TableColumn<?, ?> AuthorTable;
    
    @FXML
    private TableView<?> Table2;

    @FXML
    private TableColumn<?, ?> QuestionTable2;

    @FXML
    private TableColumn<?, ?> QuestionNumberTable2;

    @FXML
    private TableColumn<?, ?> QuestionCodeTable2;

    @FXML
    private TableColumn<?, ?> subjectTable2;

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
    private TextField SerchBySubjectrNameTXT;

    @FXML
    private Button CourseNameSearchButton;

    @FXML
    private TextField SerchByTeacherNameTXT;

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

    private Question selectedQuestion = null;
    
    
    @FXML
    void search(ActionEvent event) {

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
    public void AddNewQuestion(ActionEvent event) {
    	
    }
    
    @FXML
    public void RemoveQuestion(ActionEvent event) {
    	
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
	void selectSale(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
	   //	selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
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
