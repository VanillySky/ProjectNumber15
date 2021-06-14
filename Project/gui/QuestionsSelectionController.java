package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.DeleteController;
import controllers.LoginController;
import controllers.UpgradeConroller;
import entities.Exam;
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
import javafx.util.Callback;
/**
 * 14.06.2021
 * @author Ahmad
 *
 */
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

	@FXML
	private Button AddPoint;

	@FXML
	private TextField AddPointTXT;

	private Question selectedQuestion = null;
	private Question selectedQuestion2 = null;
	static private String ExamCode, Examnumber, examSubject, ExamCourse, ExamTime, StudentIns, TeacherIns,
			getquestionscodes, getpoints;
	private ObservableList<Question> dataList = FXCollections.observableArrayList();
	private ObservableList<Question> dataList2 = FXCollections.observableArrayList();
	static String saveQuestioncode, savePoints;
	private Exam exam;
	static int sumpoints=0;
	static boolean temp;

	/**
	 * The method is the main entry point for JavaFX applications.
	 * 
	 * @param primaryStage
	 */
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

	/**
	 * By this method we search for the question by the teacher name
	 * @param event
	 */
	@FXML
	public void SearchByTeacher(ActionEvent event) {

		this.QuestionCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.QuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.QuestionTable.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestionInstractionsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.AnswersTable1.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		this.AnswersTable2.setCellValueFactory((Callback) new PropertyValueFactory("Answer2"));
		this.AnswersTable3.setCellValueFactory((Callback) new PropertyValueFactory("Answer3"));
		this.AnswersTable4.setCellValueFactory((Callback) new PropertyValueFactory("Answer4"));
		this.RightAnswerTable.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.AuthorTable.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
		this.PointsTable1.setCellValueFactory((Callback) new PropertyValueFactory("point"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions());
		Table.setItems(dataList);
		FilteredList<Question> filteredData = new FilteredList<Question>(dataList, b -> true);
		SerchByTeacherNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Question -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Question.Author.toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);
	}

	/**
	 * By this method we search for the question by the course
	 * @param event
	 */
	@FXML
	public void SearchByCourse(ActionEvent event) {

		this.QuestionCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.QuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.QuestionTable.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestionInstractionsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.AnswersTable1.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		this.AnswersTable2.setCellValueFactory((Callback) new PropertyValueFactory("Answer2"));
		this.AnswersTable3.setCellValueFactory((Callback) new PropertyValueFactory("Answer3"));
		this.AnswersTable4.setCellValueFactory((Callback) new PropertyValueFactory("Answer4"));
		this.RightAnswerTable.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.AuthorTable.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
		this.PointsTable1.setCellValueFactory((Callback) new PropertyValueFactory("point"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions());
		Table.setItems(dataList);
		FilteredList<Question> filteredData = new FilteredList<Question>(dataList, b -> true);
		SerchBySubjectNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(Question -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Question.Subject.toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);
	}

	/**
	 * The method is to go back to the previous frame
	 * @param event
	 */
	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	/**
	 * This function apply the teacher to add questions to the question bank
	 * @param event
	 */
	@FXML
	public void AddNewQuestion(ActionEvent event) {
		boolean flag = false;
		if (selectedQuestion != null) {
			Question question = new Question(selectedQuestion.QuestionCode, selectedQuestion.QuestionNumber,
					selectedQuestion.Subject, selectedQuestion.Question, selectedQuestion.QuestionInstruction,
					selectedQuestion.Answer1, selectedQuestion.Answer2, selectedQuestion.Answer3,
					selectedQuestion.Answer4, selectedQuestion.RightAnswer, selectedQuestion.Author,
					selectedQuestion.point);

			for (int i = 0; i < dataList2.size(); i++) {
				if (dataList2.get(i).QuestionCode == question.QuestionCode)
					flag = true;
			}
			if (flag == false) {
				dataList2.add(question);
				Table2.setItems(dataList2);
				Table2.refresh();
			} else {
				pointERRLBL.setText("the question is existed!!");
				pointERRLBL.setVisible(true);
			}

		} else {
			pointERRLBL.setText("please chose any question first!!");
			pointERRLBL.setVisible(true);
		}

	}
	/**
	 * This function removes the question from the question bank 
	 * @param event
	 */
	@FXML
	public void RemoveQuestion(ActionEvent event) {
		if (Table2.getSelectionModel().getSelectedItem() != null) {
			Table2.getItems().removeAll(Table2.getSelectionModel().getSelectedItem());
			Table2.setItems(dataList2);
			Table2.refresh();
		} else {

			pointERRLBL.setText("please chose any question first!!");
			pointERRLBL.setVisible(true);

		}

	}
	/**
	 * The method is to go back to the previous frame
	 * @param event
	 */
	@FXML
	public void PressReturn(ActionEvent event) {
		for (int i = 0; i < dataList2.size(); i++) {
			saveQuestioncode += dataList2.get(i).getQuestionCode() + "\n";
			savePoints += dataList2.get(i).getPoint() + "\n";
		}
		BuildNewExamController BNECC = new BuildNewExamController();
		BNECC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	public void getarugments(String Examnumber, String examSubject, String ExamCourse, String ExamTime,
			String StudentIns, String TeacherIns, String questionscodes, String points) {
		QuestionsSelectionController.ExamCode = examSubject + ExamCourse + Examnumber;
		QuestionsSelectionController.Examnumber = Examnumber;
		QuestionsSelectionController.examSubject = examSubject;
		QuestionsSelectionController.ExamCourse = ExamCourse;
		QuestionsSelectionController.ExamTime = ExamTime;
		QuestionsSelectionController.StudentIns = StudentIns;
		QuestionsSelectionController.TeacherIns = TeacherIns;
		QuestionsSelectionController.getquestionscodes = questionscodes;
		QuestionsSelectionController.getpoints = points;
	}
	
	/**
	 * After Adding all the relvant textfeilds press done to add the question to questions bank
	 * @param event
	 */
	@FXML
	public void PressDone(ActionEvent event) {
		int count = 0;
		sumpoints=0;
		String questionscodes = "", points = "";

		for (int i = 0; i < dataList2.size(); i++) {

			questionscodes += dataList2.get(i).getQuestionCode() + "\n";

			points += dataList2.get(i).getPoint() + "\n";
		}

		for (int i = 0; i < dataList2.size(); i++) {
			if ((dataList2.get(i).point == null) || (dataList2.get(i).point == "0")) {
				pointERRLBL.setText("fill all points");
				pointERRLBL.setVisible(true);
				count++;
			}
		}
		if (dataList2.isEmpty()) {
			pointERRLBL.setText("please add any question!!");
			pointERRLBL.setVisible(true);
			count++;
		}
		for (int i = 0; i < dataList2.size(); i++) {
			sumpoints+= Integer.parseInt(dataList2.get(i).getPoint());
		}
		
		if(sumpoints!=100) {
			pointERRLBL.setText("the grade of exam should be 100");
			pointERRLBL.setVisible(true);
			count++;
		}
		
		if (count == 0) {
			exam = new Exam(ExamCode, Examnumber, examSubject, ExamCourse, ExamTime,
					ChatClient.currentUser.getFirstName(), questionscodes, points, StudentIns, TeacherIns);
			if (temp) {
				UpgradeConroller.UpgradeExam(exam);

			} else
				AddController.AddExam(exam);
			ExamsTableController ETCC = new ExamsTableController();
			ETCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}
	
	/**
	 * In this function the teacher select the question from the first table to add it to the next table
	 * @param event
	 */
	@FXML
	void selectQuestion(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}
	
	/**
	 * In this function the teacher select the question from the second table to remove it or to add points
	 * @param event
	 */
	@FXML
	void selectQuestion2(MouseEvent event) {
		if (Table2.getSelectionModel().getSelectedItem() != null) {
			selectedQuestion2 = Table2.getSelectionModel().getSelectedItem();
		}
	}

	/**
	 * This function signs out the student from the system and retuens to the login frame 
	 * @param event
	 * @throws Exception
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
	 * In this function the teacher adds points to the selected question
	 * @param event
	 */
	@FXML
	void AddPoint(ActionEvent event) {

		if (selectedQuestion2 != null && !AddPointTXT.getText().equals("")) {
			selectedQuestion2.setPoint(AddPointTXT.getText());
			Table2.refresh();
		} else {
			pointERRLBL.setText("please add question/points");
			pointERRLBL.setVisible(true);
		}
	}
	
	/**
	 * initialize the controls in the fxml
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.QuestionCodeTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.QuestionNumberTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.SubjectTable.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.QuestionTable.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestionInstractionsTable.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.AnswersTable1.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		this.AnswersTable2.setCellValueFactory((Callback) new PropertyValueFactory("Answer2"));
		this.AnswersTable3.setCellValueFactory((Callback) new PropertyValueFactory("Answer3"));
		this.AnswersTable4.setCellValueFactory((Callback) new PropertyValueFactory("Answer4"));
		this.RightAnswerTable.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.AuthorTable.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
		this.PointsTable1.setCellValueFactory((Callback) new PropertyValueFactory("point"));

		this.QuestionCodeTable2.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.QuestionNumberTable2.setCellValueFactory((Callback) new PropertyValueFactory("QuestionNumber"));
		this.subjectTable2.setCellValueFactory((Callback) new PropertyValueFactory("Subject"));
		this.QuestionTable2.setCellValueFactory((Callback) new PropertyValueFactory("Question"));
		this.QuestionInstractionsTable2.setCellValueFactory((Callback) new PropertyValueFactory("QuestionInstruction"));
		this.AnswersTable21.setCellValueFactory((Callback) new PropertyValueFactory("Answer1"));
		this.AnswersTable22.setCellValueFactory((Callback) new PropertyValueFactory("Answer2"));
		this.AnswersTable23.setCellValueFactory((Callback) new PropertyValueFactory("Answer3"));
		this.AnswersTable24.setCellValueFactory((Callback) new PropertyValueFactory("Answer4"));
		this.RightAnswerTable2.setCellValueFactory((Callback) new PropertyValueFactory("RightAnswer"));
		this.AuthorTable2.setCellValueFactory((Callback) new PropertyValueFactory("Author"));
		this.PointsTable2.setCellValueFactory((Callback) new PropertyValueFactory("point"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowQuestions());
		Table.setItems(dataList);

		if (temp) {// if we press update temp=true
			String[] SPQC = getquestionscodes.split("\n");
			String[] SPP = getpoints.split("\n");
			Question qs;
			for (int j = 0; j < SPQC.length; j++)
				for (int i = 0; i < dataList.size(); i++) {
					if (dataList.get(i).QuestionCode.equals(SPQC[j])) {
						qs = dataList.get(i);
						qs.setPoint(SPP[j]);
						dataList2.add(qs);

					}
				}
			Table2.setItems(dataList2);
			Table2.refresh();
		} 
	}
}
