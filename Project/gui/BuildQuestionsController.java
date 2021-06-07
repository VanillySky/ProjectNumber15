package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import controllers.DeleteController;
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.Question;

public class BuildQuestionsController implements Initializable {

	@FXML
	private TableView<Question> Table;

	@FXML
	private TableColumn<Question, String> QuestCodeTable;

	@FXML
	private TableColumn<Question, String> QuestNumTable;

	@FXML
	private TableColumn<Question, String> QuestSubjectTable;

	@FXML
	private TableColumn<Question, String> QuestionTable;

	@FXML
	private TableColumn<Question, String> QuestInstTable;

	@FXML
	private TableColumn<Question, String> Answers;

	@FXML
	private TableColumn<Question, String> Answers1Table;

	@FXML
	private TableColumn<Question, String> Answers2Table;

	@FXML
	private TableColumn<Question, String> Answers3Table;

	@FXML
	private TableColumn<Question, String> Answers4Table;

	@FXML
	private TableColumn<Question, String> RightAnswerTable;

	@FXML
	private TableColumn<Question, String> AuthorTable;

	@FXML
	private TableColumn<Question, String> pointTable;

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

	private Question selectedQuestion = null;

	private ObservableList<Question> dataList = FXCollections.observableArrayList();

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

		this.QuestCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.QuestNumTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.QuestSubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.QuestionTable.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestInstTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.Answers1Table.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		this.Answers2Table.setCellValueFactory((Callback) new PropertyValueFactory("Answer2"));
		this.Answers3Table.setCellValueFactory((Callback) new PropertyValueFactory("Answer3"));
		this.Answers4Table.setCellValueFactory((Callback) new PropertyValueFactory("Answer4"));
		this.RightAnswerTable.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.AuthorTable.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
		this.pointTable.setCellValueFactory((Callback) new PropertyValueFactory("point"));
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions());
		Table.setItems(dataList);
		Table.refresh();
	}

	@FXML
	public void AddNewQuestion(ActionEvent event) {
		NewQuestionController BNQCC = new NewQuestionController();
		BNQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void UpdateExam(ActionEvent event) {
		if(selectedQuestion != null) {
			
		}

	}

	@FXML
	public void DeleteQuestion(ActionEvent event) {
		DeleteController DC = new DeleteController();
		DC.DeleteQuestion(selectedQuestion.getQuestionCode());

		Table.setItems(
				FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions()));
		Table.refresh();
	}

	@FXML
	void selectQuestion(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}
