package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.DeleteController;
import controllers.LoginController;
import controllers.UpgradeConroller;
import entities.Exam;
import entities.commonmistake;
import entities.ExamResponse;
import entities.ManagerMessage;
import entities.Question;
import entities.StatusExam;
import entities.StudentGrade;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author axwara1 this class is doing a auto exam for student , only the
 *         students can join it , and all the questions come in one frame we
 *         just changes the labels
 *
 */
public class AutoController implements Initializable {

	@FXML
	private Pane pane;
	@FXML
	private Label questionLBL;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private Text hoursTimer;

	@FXML
	private Text MinutesTimer;

	@FXML
	private Text SecondsTimer;

	@FXML
	private RadioButton Answer1RB;

	@FXML
	private RadioButton Answer2RB;

	@FXML
	private RadioButton Answer3RB;

	@FXML
	private RadioButton Answer4RB;

	@FXML
	private ImageView prevIMG;

	@FXML
	private ImageView submitIMG;

	@FXML
	private Button CEMBTN;

	@FXML
	private Button SignOUTBTN;

	@FXML
	private Button NEXTBTN;

	@FXML
	private Button PrevBTN;

	@FXML
	private Button BackToMenu;

	@FXML
	private Label TheExamDone;

	@FXML
	private Label label1;

	@FXML
	private Label questionIns;

	@FXML
	private Label cantSubmit;

	@FXML
	private Label AddLBL;

	int sum;
	boolean submit = false;
	int[] StudentAnswer;
	String[] Allpoint;
	Question[] AllQuestion;
	static int N;
	private ObservableList<ManagerMessage> dataList3 = FXCollections.observableArrayList();
	private ObservableList<Exam> dataList = FXCollections.observableArrayList();
	private ObservableList<ExamResponse> dataList2 = FXCollections.observableArrayList();
	private ArrayList<Object> getQuestion = new ArrayList<Object>();
	static String ExamCode;
	static int Endnumber;
	Map<Integer, String> numberMap;
	static Integer CurrSeconds;
	Thread thrd;
	Integer hours, min, addSec;
	static boolean timefinish;

	
	
	
	/**
	 * @param primaryStage this function open the FXML of the auto exam , we call it from Login auto class... 
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/Auto.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Auto Exam");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param event => when we press to CEMS LOGO we back to menu 
	 */
	@FXML
	void CEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * start a thread that every time decrease from the current seconds 1  , 
	 * just a doing count down 
	 */
	void startCountdown() {
		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						CurrSeconds -= 1;
						setOutput();
						Thread.sleep(1000);
						if (CurrSeconds == 0) {

							timefinish = true;
							Done();
							thrd.stop();

						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		thrd.start();
	}

	/**
	 *  this method help the thread , every time the thread join this method to write the count down 
	 */
	void setOutput() {
		LinkedList<Integer> currHms = secondsToHms(CurrSeconds);
		hoursTimer.setText(numberMap.get(currHms.get(0)));
		MinutesTimer.setText(numberMap.get(currHms.get(1)));
		SecondsTimer.setText(numberMap.get(currHms.get(2)));
	}

	/**
	 * when the thread come to current seconds to 0
	 * he call this method to stop the thread   
	 */
	void Done() {

		cantSubmit.setText("the Time done you can't submit your exam");
		cantSubmit.setVisible(true);
		LoginController.ChangeLockedEXCODE(ExamCode, "locked");

	}

	/**
	 * @param h -> mean the hour 
	 * @param m -> mean the minutes 
	 * @param s -> mean the seconds
	 * @return -> the time with seconds 
	 */
	Integer hmsToSeconds(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		return hToSeconds + mToSeconds + s;
	}

	/**
	 * @param currSeconds -> the amount of seconds 
	 * @return -> the hour and minutes and seconds -> 3600 seconds => 1:00
	 */
	LinkedList<Integer> secondsToHms(Integer currSeconds) {
		Integer hours = currSeconds / 3600;
		currSeconds = CurrSeconds % 3600;
		Integer minutes = CurrSeconds / 60;
		currSeconds = CurrSeconds % 60;
		Integer seconds = currSeconds;
		LinkedList<Integer> answer = new LinkedList<>();
		answer.add(hours);
		answer.add(minutes);
		answer.add(seconds);
		return answer;
	}

	/**
	 * @param event when we press the next button first we check the answer of the student we put it in studentAnswer array , and then we write the new question and answers , there is question arrays
	 * and points array and all the answers ,,, if the student answer a question and press next then prev the answer saved . if we come to last question next change to submit and when we press submit
	 * we check all the common mistake we upgrade the status we calculate the grade , and appear like labels that told us that the exam is done  . 
	 */
	@FXML
	void GoNext(ActionEvent event) {

		if (submit == false) {
			prevIMG.setVisible(true);
			PrevBTN.setDisable(false);
			if (N != AllQuestion.length - 2) {
				if (Answer1RB.isSelected())
					StudentAnswer[N] = 1;

				if (Answer2RB.isSelected())
					StudentAnswer[N] = 2;

				if (Answer3RB.isSelected())
					StudentAnswer[N] = 3;

				if (Answer4RB.isSelected())
					StudentAnswer[N] = 4;

				N++;
				if (StudentAnswer[N] == 0) {
					Answer1RB.setSelected(false);
					Answer2RB.setSelected(false);
					Answer3RB.setSelected(false);
					Answer4RB.setSelected(false);
				} else {
					if (StudentAnswer[N] == 1)
						Answer1RB.setSelected(true);
					if (StudentAnswer[N] == 2)
						Answer2RB.setSelected(true);
					if (StudentAnswer[N] == 3)
						Answer3RB.setSelected(true);
					if (StudentAnswer[N] == 4)
						Answer4RB.setSelected(true);
				}

				questionLBL.setText(N + 1 + "- " + AllQuestion[N].getQuestion() + "     (" + Allpoint[N] + ")points");
				Answer1RB.setText("1) " + AllQuestion[N].getAnswer1());
				Answer2RB.setText("2) " + AllQuestion[N].getAnswer2());
				Answer3RB.setText("3) " + AllQuestion[N].getAnswer3());
				Answer4RB.setText("4) " + AllQuestion[N].getAnswer4());
				questionIns.setText("instruction:" + AllQuestion[N].getQuestionInstruction());

			} else {
				submitIMG.setVisible(true);

				if (Answer1RB.isSelected())
					StudentAnswer[N] = 1;
				if (Answer2RB.isSelected())
					StudentAnswer[N] = 2;
				if (Answer3RB.isSelected())
					StudentAnswer[N] = 3;
				if (Answer4RB.isSelected())
					StudentAnswer[N] = 4;

				N++;
				if (StudentAnswer[N] == 0) {
					Answer1RB.setSelected(false);
					Answer2RB.setSelected(false);
					Answer3RB.setSelected(false);
					Answer4RB.setSelected(false);
				} else {
					if (StudentAnswer[N] == 1)
						Answer1RB.setSelected(true);
					if (StudentAnswer[N] == 2)
						Answer2RB.setSelected(true);
					if (StudentAnswer[N] == 3)
						Answer3RB.setSelected(true);
					if (StudentAnswer[N] == 4)
						Answer4RB.setSelected(true);
				}

				questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion() + "   (" + Allpoint[N] + ")points");
				Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
				Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
				Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
				Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
				questionIns.setText("instruction:" + AllQuestion[N].getQuestionInstruction());
				submit = true;

			}

		} else {
			if (timefinish == false) {

				if (Answer1RB.isSelected())
					StudentAnswer[N] = 1;

				if (Answer2RB.isSelected())
					StudentAnswer[N] = 2;

				if (Answer3RB.isSelected())
					StudentAnswer[N] = 3;

				if (Answer4RB.isSelected())
					StudentAnswer[N] = 4;
				sum = 0;
				for (N = 0; N < AllQuestion.length; N++) {

					if (StudentAnswer[N] == Integer.parseInt(AllQuestion[N].getRightAnswer()))
						sum += Integer.parseInt(Allpoint[N]);
				}

				for (N = 0; N < AllQuestion.length; N++) {
					ExamResponse ER = new ExamResponse(ExamCode, ChatClient.currentUser.getUserName(),
							AllQuestion[N].QuestionCode, "" + StudentAnswer[N]);
					AddController.ExamResponse(ER);
				}
				String grade = "" + sum;
				StudentGrade SG = new StudentGrade(ChatClient.currentUser.getUserName(), ExaminationController.ExamCode,
						dataList.get(0).getExamCourse(), grade, dataList.get(0).getTeacherName());
				AddController.AddStudentGrade(SG);

				for (N = 0; N < AllQuestion.length; N++) {
					if (!AllQuestion[N].getRightAnswer().equals("" + StudentAnswer[N])) {
						dataList2 = FXCollections.observableArrayList((Collection) controllers.DisplayController
								.GetAllSameAnswer(ExamCode, AllQuestion[N].getQuestionCode(), "" + StudentAnswer[N]));

						for (int i = 0; i < dataList2.size(); i++) {
							commonmistake CM = new commonmistake(ExamCode, AllQuestion[N].getQuestionCode(),
									ChatClient.currentUser.getUserName(), dataList2.get(i).getUserName());
							if (!dataList2.get(i).getUserName().equals(ChatClient.currentUser.getUserName()))
								AddController.AddCommonMistake(CM);
						}
					}
				}

				StatusExam EndStatus;
				Endnumber++;
				EndStatus = ExaminationController.SE;
				EndStatus.setNumberEndExam("" + Endnumber);
				UpgradeConroller.UpgradeStatusEnd(EndStatus);

				if (ExaminationController.starNum == Endnumber)
					LoginController.ChangeLockedEXCODE(ExamCode, "locked");

				NEXTBTN.setVisible(false);
				submitIMG.setVisible(false);
				questionLBL.setVisible(false);
				Answer1RB.setVisible(false);
				Answer2RB.setVisible(false);
				Answer3RB.setVisible(false);
				Answer4RB.setVisible(false);
				prevIMG.setVisible(false);
				PrevBTN.setDisable(true);
				BackToMenu.setVisible(true);
				TheExamDone.setVisible(true);
				label1.setVisible(true);
				questionIns.setVisible(false);
				menuPane.setVisible(false);

			} else {
				UnsubmittedExamController USMEC = new UnsubmittedExamController();
				USMEC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();
			}
		}

	}

	/**
	 * @param event - >join this method when we press prev button in javaFX ,when we press prev change the labels to questions and answers that was before and the answer of student  .
	 */
	@FXML
	void GoPrev(ActionEvent event) {
		submit = false;
		submitIMG.setVisible(false);

		if (Answer1RB.isSelected())
			StudentAnswer[N] = 1;
		if (Answer2RB.isSelected())
			StudentAnswer[N] = 2;
		if (Answer3RB.isSelected())
			StudentAnswer[N] = 3;
		if (Answer4RB.isSelected())
			StudentAnswer[N] = 4;

		N--;
		if (StudentAnswer[N] == 0) {
			Answer1RB.setSelected(false);
			Answer2RB.setSelected(false);
			Answer3RB.setSelected(false);
			Answer4RB.setSelected(false);
		} else {
			if (StudentAnswer[N] == 1)
				Answer1RB.setSelected(true);
			if (StudentAnswer[N] == 2)
				Answer2RB.setSelected(true);
			if (StudentAnswer[N] == 3)
				Answer3RB.setSelected(true);
			if (StudentAnswer[N] == 4)
				Answer4RB.setSelected(true);
		}
		if (N == 0) {
			prevIMG.setVisible(false);
			PrevBTN.setDisable(true);
		}

		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion() + "   (" + Allpoint[N] + ")points");
		Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
		Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
		Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
		Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
		questionIns.setText("instruction:" + AllQuestion[N].getQuestionInstruction());

	}

	/**
	 * @param event -> when we press to return button we back to student menu
	 */
	@FXML
	void BackToMenu(ActionEvent event) {
		thrd.stop();
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event - > when we press to sign out button we back to login frame  
	 * @throws Exception 
	 */
	@FXML
	void SignOut(ActionEvent event) throws Exception {
		thrd.stop();
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 *this method start the class we put the first question and answer labels and make all the arrays ready , and make the count down ready
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CurrSeconds = 0;
		ToggleGroup group = new ToggleGroup();
		Answer1RB.setToggleGroup(group);
		Answer2RB.setToggleGroup(group);
		Answer3RB.setToggleGroup(group);
		Answer4RB.setToggleGroup(group);

		N = 0;
		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowOneExam(ExaminationController.ExamCode));
		String[] QuestionCodes = dataList.get(0).getChosenQuestion().split("\n");
		String[] points = dataList.get(0).getQuestionPoint().split("\n");
		AllQuestion = new Question[QuestionCodes.length];
		Allpoint = new String[QuestionCodes.length];
		StudentAnswer = new int[QuestionCodes.length];

		for (int i = 0; i < QuestionCodes.length; i++) {
			getQuestion = (ArrayList<Object>) controllers.DisplayController.ShowOneQuestions(QuestionCodes[i]);
			AllQuestion[i] = (Question) getQuestion.get(0);
			Allpoint[i] = points[i];
		}

		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion() + "   (" + Allpoint[N] + ")points");
		Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
		Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
		Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
		Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
		questionIns.setText("instruction:" + AllQuestion[N].getQuestionInstruction());

		dataList3 = FXCollections
				.observableArrayList((Collection) controllers.DisplayController.ApprovedChangeTime(ExamCode)); ///// add
		///// manager ///// approved
		if (dataList3.size() != 0) {
			String addtime = dataList3.get(0).getAddtime();
			String[] Addhourmin = addtime.split(":");
			Integer addH = Integer.parseInt(Addhourmin[0]);
			Integer addM = Integer.parseInt(Addhourmin[1]);
			addSec = hmsToSeconds(addH, addM, 0);
			CurrSeconds += addSec;
		}

		String time = ExaminationController.ExamTime;
		String[] hourmin = time.split(":");
		hours = Integer.parseInt(hourmin[0]);
		min = Integer.parseInt(hourmin[1]);
		hoursTimer.setText(hours + "");
		MinutesTimer.setText(min + "");
		SecondsTimer.setText("0");
		CurrSeconds += hmsToSeconds(hours, min, 0);
		// CurrSeconds =200;
		timefinish = false;
		startCountdown();

		numberMap = new TreeMap<Integer, String>();
		for (Integer i = 0; i <= 60; i++) {
			if (i >= 0 && i <= 9)
				numberMap.put(i, "0" + i.toString());
			else
				numberMap.put(i, i.toString());
		}
	}

}