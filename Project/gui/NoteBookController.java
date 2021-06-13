package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Exam;
import entities.ExamResponse;
import entities.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NoteBookController implements Initializable {

	@FXML
	private Label questionLBL;

	@FXML
	private Label RightAnswer;

	@FXML
	private Label StudentAnswer;

	@FXML
	private Label Point;

	@FXML
	private Button CEMBTN;

	@FXML
	private Button SignOUTBTN;

	@FXML
	private Button NEXTBTN;

	@FXML
	private Button PrevBTN;
	@FXML
	private ImageView prevImage;

	@FXML
	private ImageView NextImg;

	static int N;
	boolean lastPage;

	@FXML
	private Button RetrurnBTN;
	String[] Allpoint;
	Question[] AllQuestion;
	String[] AllStudentAnswer;

	private ArrayList<Object> getQuestion = new ArrayList<Object>();
	static String ExamCode;
	private ObservableList<Exam> dataList = FXCollections.observableArrayList();
	private ObservableList<ExamResponse> dataList2 = FXCollections.observableArrayList();

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/NoteBook.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Note Book");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void CEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	@FXML
	void GoNext(ActionEvent event) {

		prevImage.setVisible(true);
		PrevBTN.setDisable(false);

		if (N != AllQuestion.length - 2) {

			N++;

			Point.setText("point:" + Allpoint[N]);
			questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion());

			if (AllQuestion[N].RightAnswer.equals("1")) ///////////////// to write the right answer
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer1());
			if (AllQuestion[N].RightAnswer.equals("2"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer2());
			if (AllQuestion[N].RightAnswer.equals("3"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer3());
			if (AllQuestion[N].RightAnswer.equals("4"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer4());

			if (AllStudentAnswer[N].equals("1"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer1());
			if (AllStudentAnswer[N].equals("2"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer2());
			if (AllStudentAnswer[N].equals("3"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer3());
			if (AllStudentAnswer[N].equals("4"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer4());
		} else {

			NextImg.setVisible(false);
			N++;
			Point.setText("point:" + Allpoint[N]);
			questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion());

			if (AllQuestion[N].RightAnswer.equals("1")) ///////////////// to write the right answer
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer1());
			if (AllQuestion[N].RightAnswer.equals("2"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer2());
			if (AllQuestion[N].RightAnswer.equals("3"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer3());
			if (AllQuestion[N].RightAnswer.equals("4"))
				RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer4());

			if (AllStudentAnswer[N].equals("1"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer1());
			if (AllStudentAnswer[N].equals("2"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer2());
			if (AllStudentAnswer[N].equals("3"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer3());
			if (AllStudentAnswer[N].equals("4"))
				StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer4());

		}

	}

	@FXML
	void GoPrev(ActionEvent event) {
		
		NEXTBTN.setDisable(false);
		NextImg.setVisible(true);
		N--;
		
		
		Point.setText("point:" + Allpoint[N]);
		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion());

		if (AllQuestion[N].RightAnswer.equals("1")) ///////////////// to write the right answer
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer1());
		if (AllQuestion[N].RightAnswer.equals("2"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer2());
		if (AllQuestion[N].RightAnswer.equals("3"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer3());
		if (AllQuestion[N].RightAnswer.equals("4"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer4());

		if (AllStudentAnswer[N].equals("1"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer1());
		if (AllStudentAnswer[N].equals("2"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer2());
		if (AllStudentAnswer[N].equals("3"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer3());
		if (AllStudentAnswer[N].equals("4"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer4());
		
		if(N==0) {
			prevImage.setVisible(false);
			PrevBTN.setDisable(true);

		}
		

	}

	@FXML
	void GoReturn(ActionEvent event) {
		StudentGradeListController SGLC = new StudentGradeListController();
		SGLC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		N = 0;
		lastPage = false;
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowOneExam(ExamCode));
		String[] QuestionCodes = dataList.get(0).getChosenQuestion().split("\n");
		String[] points = dataList.get(0).getQuestionPoint().split("\n");
		AllStudentAnswer = new String[QuestionCodes.length];
		AllQuestion = new Question[QuestionCodes.length];
		Allpoint = new String[QuestionCodes.length];
		for (int i = 0; i < QuestionCodes.length; i++) {
			getQuestion = (ArrayList<Object>) controllers.DisplayController.ShowOneQuestions(QuestionCodes[i]);
			AllQuestion[i] = (Question) getQuestion.get(0);
			Allpoint[i] = points[i];
		}

		dataList2 = FXCollections.observableArrayList((Collection) controllers.DisplayController
				.GetAllStudentAnswer(ExamCode, ChatClient.currentUser.getUserName()));
		for (int i = 0; i < QuestionCodes.length; i++) {
			AllStudentAnswer[i] = dataList2.get(i).getStudentAnswer();
		}

		Point.setText("point:" + Allpoint[N]);
		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion());

		if (AllQuestion[N].RightAnswer.equals("1")) ///////////////// to write the right answer
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer1());
		if (AllQuestion[N].RightAnswer.equals("2"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer2());
		if (AllQuestion[N].RightAnswer.equals("3"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer3());
		if (AllQuestion[N].RightAnswer.equals("4"))
			RightAnswer.setText("The right Answer:-" + AllQuestion[N].getAnswer4());

	
		if (AllStudentAnswer[N].equals("1"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer1());
		if (AllStudentAnswer[N].equals("2"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer2());
		if (AllStudentAnswer[N].equals("3"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer3());
		if (AllStudentAnswer[N].equals("4"))
			StudentAnswer.setText("Your Answer:-" + AllQuestion[N].getAnswer4());

	}

}
