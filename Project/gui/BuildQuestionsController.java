package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DeleteController;
import controllers.LoginController;
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import entities.Exam;
import entities.Question;

/**
 * @author axwara1
 * in the class we build a new questions , we put question code and question number and question answers and more
 *
 */
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
	private Button SerchBySubjectButton;

	@FXML
	private Button SerchByTeacherButton;

	@FXML
	private TextField SerchByTeacherTXT;

	@FXML
	private TextField SerchBySubjectTXT;

	private Question selectedQuestion = null;

	@FXML
	private Label ErrorLBL;

	private ObservableList<Question> dataList = FXCollections.observableArrayList();

	/**
	 * @param primaryStage
	 * this method start BuildQuesion FXML , that appear table that have all the question in CEMS 
	 */
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

	/**
	 * this method start when the frame open , we put all the data that existing in sql to this table
	 */
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
		
		for(int i=0;i<dataList.size();i++) 
			NewQuestionController.AllQuestionscode.add(dataList.get(i).getQuestionCode());
			
		}

	/**
	 * @param event
	 * we press to AddnewQuestion to go to the frame of write a new question , temp =false because join next page as add not update  
	 */
	@FXML
	public void AddNewQuestion(ActionEvent event) {
		NewQuestionController.temp = false;
		NewQuestionController BNQCC = new NewQuestionController();
		BNQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * send all arguments to next page if we need to update it .
	 */
	@FXML
	void UpdateQuestion(ActionEvent event) {
		if (selectedQuestion != null) {

			NewQuestionController.QuestionNum = selectedQuestion.getQuestionNumber();
			NewQuestionController.Subject = selectedQuestion.getSubject();
			NewQuestionController.Question = selectedQuestion.getQuestion();
			NewQuestionController.Answer1 = selectedQuestion.getAnswer1();
			NewQuestionController.Answer2 = selectedQuestion.getAnswer2();
			NewQuestionController.Answer3 = selectedQuestion.getAnswer3();
			NewQuestionController.Answer4 = selectedQuestion.getAnswer4();
			NewQuestionController.Rigthanswer = selectedQuestion.getRightAnswer();
			NewQuestionController.QuestionIns = selectedQuestion.getQuestionInstruction();
			NewQuestionController.temp = true;
			NewQuestionController BNQCC = new NewQuestionController();
			BNQCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		} else {
			ErrorLBL.setText("Please chose question to Update");
			ErrorLBL.setVisible(true);

		}

	}

	/**
	 * @param event
	 * this method to delete a question from the table 
	 */
	@FXML
	public void DeleteQuestion(ActionEvent event) {
		if (selectedQuestion != null) {
			DeleteController DC = new DeleteController();
			DC.DeleteQuestion(selectedQuestion.getQuestionCode());

			Table.setItems(
					FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions()));
			Table.refresh();
		} else {
			ErrorLBL.setText("Please chose question Delete");
			ErrorLBL.setVisible(true);

		}
	}

	/**
	 * @param event
	 * we using this method to mouse event that help us for select 
	 */
	@FXML
	void selectQuestion(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}

	/**
	 * @param event
	 * if we press the CEMS logo we go to TeacherMenu
	 */
	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * @throws Exception
	 * This method to sign out.
	 */
	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * @param event
	 * this method doing search on the table by teacher 
	 */
	@FXML
	public void SearchByTeacher(ActionEvent event) {
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
		FilteredList<Question> filteredData = new FilteredList<Question>(dataList, b -> true);
		SerchByTeacherTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Question -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Question.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);

	}

	/**
	 * @param event
	 * this method doing search on the table by Subject
	 */
	@FXML
	public void SearchBySubject(ActionEvent event) {
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
		FilteredList<Question> filteredData = new FilteredList<Question>(dataList, b -> true);
		SerchBySubjectTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Question -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Question.getSubject().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);

	}

}
