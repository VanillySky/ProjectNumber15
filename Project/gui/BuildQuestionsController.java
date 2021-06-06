package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DeleteController;
import controllers.DisplayController;

import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
public class BuildQuestionsController implements Initializable {

    @FXML
    private TableView<Question> QuestionTable;

    @FXML
    private TableColumn<Question, String> QuestNum;

    @FXML
    private TableColumn<Question, String> QuestCode;

    @FXML
    private TableColumn<Question, String> Course;

    @FXML
    private TableColumn<Question, String> Question;

    @FXML
    private TableColumn<Question, String> QuestInst;

    @FXML
    private TableColumn <Question, String> Answers;

    @FXML
    private TableColumn<Question, String> RightAns;

    @FXML
    private TableColumn<Question, String> Author;

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
    
	private Question selectedQuestion = null;

    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/BuildQuestions.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Build Question");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
    @Override
	public void initialize(URL url, ResourceBundle rb) {

		this.QuestNum.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.QuestCode.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.Course.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.Question.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestInst.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.Answers.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		//here maybe we have to add more Answers
		this.RightAns.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.Author.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
				this.QuestionTable.setItems(FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions()));
				this.QuestionTable.refresh();
		}
	
    
    @FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
    @FXML
	public void AddNewQuestion(ActionEvent event) {
		BuildNewQuestionController BNQCC = new BuildNewQuestionController();
		BNQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
    @FXML
	public void DeleteQuestion(ActionEvent event) {
        DeleteController DC = new DeleteController();
        DC.DeleteExam(selectedQuestion.getQuestionCode()); 
        
		this.QuestionTable.setItems(FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions()));
		this.QuestionTable.refresh();
	}
    @FXML
	void selectQuestion(MouseEvent event) {
		if (QuestionTable.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion =QuestionTable.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

    
   

}
