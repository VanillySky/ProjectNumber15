package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import entities.Question;
import javafx.application.Application;
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

public class QuestionsSelectionController extends Application implements Initializable {

	@FXML
	private TableView<Question> Table;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button OutButton;

	@FXML
	private TableColumn<Question, String> QuestionNumberTable;

	@FXML
	private TableColumn<Question, String> QuestionCodeTable;

	@FXML
	private TableColumn<Question, String> SubjectTable;

	@FXML
	private TableColumn<Question, String> QuestionTable;

	@FXML
	private TableColumn<Question, String> QuestionInstractionsTable;

	@FXML
	private TableColumn<Question, String> AnswersTable;

	@FXML
	private TableColumn<Question, String> AnswersTable1;

	@FXML
	private TableColumn<Question, String> AnswersTable222;

	@FXML
	private TableColumn<Question, String> AnswersTable3;

	@FXML
	private TableColumn<Question, String> AnswersTable4;

	@FXML
	private TableColumn<Question, String> RightAnswerTable;

	@FXML
	private TableColumn<Question, String> AuthorTable;

	@FXML
	private TableColumn<Question, String> PointsTable1;

	@FXML
	private TableView<Question> Table2;

	@FXML
	private TableColumn<Question, String> QuestionNumberTable2;

	@FXML
	private TableColumn<Question, String> QuestionCodeTable2;

	@FXML
	private TableColumn<Question, String> subjectTable2;

	@FXML
	private TableColumn<Question, String> QuestionTable2;

	@FXML
	private TableColumn<Question, String> QuestionInstractionsTable2;

	@FXML
	private TableColumn<Question, String> AnswersTable2;

	@FXML
	private TableColumn<Question, String> AnswersTable21;

	@FXML
	private TableColumn<Question, String> AnswersTable22;

	@FXML
	private TableColumn<Question, String> AnswersTable23;

	@FXML
	private TableColumn<Question, String> AnswersTable24;

	@FXML
	private TableColumn<Question, String> RightAnswerTable2;

	@FXML
	private TableColumn<Question, String> AuthorTable2;

	@FXML
	private TableColumn<Question, String> PointsTable2;

	@FXML
	private TextField SerchBySubjectNameTXT;

	@FXML
	private Button SubjectNameSearchButton;

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

	@FXML
	private Label pointERRLBL;

	private Question selectedQuestion = null;

	private final ObservableList<Question> dataList = FXCollections.observableArrayList();
	private ArrayList<Question> arr = new ArrayList<Question>();

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/QuestionsSelection.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Questions Selection");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void search(ActionEvent event) {

		FilteredList<Question> filteredData = new FilteredList<Question>(dataList, b -> true);
		if (TeacherNameSearchButton.isPressed()) {
			SerchByTeacherNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
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
		} else {
			SerchBySubjectNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
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

		}

		SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);

	}

	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void AddNewQuestion(ActionEvent event) {
		if (selectedQuestion != null) {
			Question question = new Question(selectedQuestion.QuestionNumber, selectedQuestion.QuestionCode,
					selectedQuestion.Subject, selectedQuestion.Question, selectedQuestion.QuestionInstruction,
					selectedQuestion.Answer1, selectedQuestion.Answer2, selectedQuestion.Answer3,
					selectedQuestion.Answer4, selectedQuestion.RightAnswer, selectedQuestion.Author,
					selectedQuestion.point);
			arr.add(question);
			dataList.add(question);
			Table2.setItems(dataList);
			dataList.clear();

		} else {
			pointERRLBL.setText("please chose any question first!!");
			pointERRLBL.setVisible(true);
		}

	}

	@FXML
	public void RemoveQuestion(ActionEvent event) {
		if (selectedQuestion != null) {
			Question question = new Question(selectedQuestion.QuestionNumber, selectedQuestion.QuestionCode,
					selectedQuestion.Subject, selectedQuestion.Question, selectedQuestion.QuestionInstruction,
					selectedQuestion.Answer1, selectedQuestion.Answer2, selectedQuestion.Answer3,
					selectedQuestion.Answer4, selectedQuestion.RightAnswer, selectedQuestion.Author,
					selectedQuestion.point);
			Table2.getItems().removeAll(question);
			arr.remove(question);
		} else {
			pointERRLBL.setText("please chose any question first!!");
			pointERRLBL.setVisible(true);

		}

	}

	@FXML
	public void PressReturn(ActionEvent event) {
		BuildNewExamController BNECC = new BuildNewExamController();
		BNECC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void PressDone(ActionEvent event) {

		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			if ((arr.get(i).point == null) || (arr.get(i).point == "0")) {
				pointERRLBL.setText("fill all points");
				pointERRLBL.setVisible(true);
				count++;
			}
		}
		if (arr.isEmpty()) {
			pointERRLBL.setText("please add any question!!");
			pointERRLBL.setVisible(true);
			count++;
		}

		if (count == 0) {
			ExamsTableController ETCC = new ExamsTableController();
			ETCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}

	@FXML
	void selectQuestion(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	void selectQuestion2(MouseEvent event) {
		if (Table2.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}

	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		QuestionNumberTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionNumberTable"));
		QuestionCodeTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionCodeTable"));
		SubjectTable.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectTable"));
		QuestionTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionTable"));
		QuestionInstractionsTable
				.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionInstractionsTable"));
		AnswersTable1.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable1"));
		AnswersTable222.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable222"));
		AnswersTable3.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable3"));
		AnswersTable4.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable4"));
		RightAnswerTable.setCellValueFactory(new PropertyValueFactory<Question, String>("RightAnswerTable"));
		AuthorTable.setCellValueFactory(new PropertyValueFactory<Question, String>("AuthorTable"));
		PointsTable1.setCellValueFactory(new PropertyValueFactory<Question, String>("PointsTable1"));
		QuestionNumberTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionNumberTable2"));
		QuestionCodeTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionCodeTable2"));
		subjectTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectTable2"));
		QuestionTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionTable2"));
		QuestionInstractionsTable2
				.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionInstractionsTable2"));
		AnswersTable21.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable21"));
		AnswersTable22.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable22"));
		AnswersTable23.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable23"));
		AnswersTable24.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable24"));
		RightAnswerTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("RightAnswerTable2"));
		AuthorTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("AuthorTable2"));
		PointsTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("PointsTable2"));

	}
}
