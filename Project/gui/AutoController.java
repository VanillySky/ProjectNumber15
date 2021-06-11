package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.LoginController;
import entities.Exam;
import entities.Question;
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
import javafx.stage.Stage;

public class AutoController implements Initializable {

	@FXML
	private Label questionLBL;

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

	int sum;
	boolean submit = false;
	int[] StudentAnswer;
	String[] Allpoint;
	Question[] AllQuestion;
	static int N;
	private ObservableList<Exam> dataList = FXCollections.observableArrayList();
	private ArrayList<Object> getQuestion = new ArrayList<Object>();

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

	@FXML
	void CEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

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
				if(StudentAnswer[N]==0) {
					Answer1RB.setSelected(false);
					Answer2RB.setSelected(false);
					Answer3RB.setSelected(false);
					Answer4RB.setSelected(false);
				}else {
					if(StudentAnswer[N]==1)
						Answer1RB.setSelected(true);
					if(StudentAnswer[N]==2)
						Answer2RB.setSelected(true);
					if(StudentAnswer[N]==3)
						Answer3RB.setSelected(true);
					if(StudentAnswer[N]==4)
						Answer4RB.setSelected(true);			
				}
					
				

				questionLBL.setText(N + 1 + "- " + AllQuestion[N].getQuestion()+"     ("+Allpoint[N]+")points");
				Answer1RB.setText("1) " + AllQuestion[N].getAnswer1());
				Answer2RB.setText("2) " + AllQuestion[N].getAnswer2());
				Answer3RB.setText("3) " + AllQuestion[N].getAnswer3());
				Answer4RB.setText("4) " + AllQuestion[N].getAnswer4());
				questionIns.setText("instruction:"+ AllQuestion[N].getQuestionInstruction());

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
				if(StudentAnswer[N]==0) {
					Answer1RB.setSelected(false);
					Answer2RB.setSelected(false);
					Answer3RB.setSelected(false);
					Answer4RB.setSelected(false);
				}else {
					if(StudentAnswer[N]==1)
						Answer1RB.setSelected(true);
					if(StudentAnswer[N]==2)
						Answer2RB.setSelected(true);
					if(StudentAnswer[N]==3)
						Answer3RB.setSelected(true);
					if(StudentAnswer[N]==4)
						Answer4RB.setSelected(true);			
				}
				

				questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion()+"   ("+Allpoint[N]+")points");
				Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
				Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
				Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
				Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
				questionIns.setText("instruction:"+ AllQuestion[N].getQuestionInstruction());
				submit = true;

			}

		}else {
			
			if (Answer1RB.isSelected()) 
				StudentAnswer[N] = 1;
			
				
			if (Answer2RB.isSelected())
				StudentAnswer[N] = 2;
	
			if (Answer3RB.isSelected())
				StudentAnswer[N] = 3;
	
			if (Answer4RB.isSelected())
				StudentAnswer[N] = 4;
			

			for(N=0;N<AllQuestion.length;N++) {
				if(StudentAnswer[N]==Integer.parseInt(AllQuestion[N].getRightAnswer()))
					sum+=Integer.parseInt(Allpoint[N]);
			}
			String grade = ""+sum;
			StudentGrade  SG = new StudentGrade(ChatClient.currentUser.getUserName() ,ExaminationController.ExamCode , dataList.get(0).getExamCourse(), grade , dataList.get(0).getTeacherName());
			AddController.AddStudentGrade(SG);
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
			
		}
	
	}

	@FXML
	void GoPrev(ActionEvent event) {
		submit=false;
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
		if(StudentAnswer[N]==0) {
			Answer1RB.setSelected(false);
			Answer2RB.setSelected(false);
			Answer3RB.setSelected(false);
			Answer4RB.setSelected(false);
		}else {
			if(StudentAnswer[N]==1)
				Answer1RB.setSelected(true);
			if(StudentAnswer[N]==2)
				Answer2RB.setSelected(true);
			if(StudentAnswer[N]==3)
				Answer3RB.setSelected(true);
			if(StudentAnswer[N]==4)
				Answer4RB.setSelected(true);			
		}
		if (N == 0) {
			prevIMG.setVisible(false);
			PrevBTN.setDisable(true);
		}

		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion()+"   ("+Allpoint[N]+")points");
		Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
		Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
		Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
		Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
		questionIns.setText("instruction:"+ AllQuestion[N].getQuestionInstruction());

	}
	
	@FXML
    void BackToMenu(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

	@FXML
	void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ToggleGroup group = new ToggleGroup();
		Answer1RB.setToggleGroup(group);
		Answer2RB.setToggleGroup(group);
		Answer3RB.setToggleGroup(group);
		Answer4RB.setToggleGroup(group);

		N = 0;
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowOneExam(ExaminationController.ExamCode));
	   String[] QuestionCodes = dataList.get(0).getChosenQuestion().split("\n");
		String[] points = dataList.get(0).getQuestionPoint().split("\n");
		AllQuestion = new Question[QuestionCodes.length];
		Allpoint = new String [QuestionCodes.length];
		StudentAnswer = new int[QuestionCodes.length];

		for (int i = 0; i < QuestionCodes.length; i++) {
			getQuestion =   (ArrayList<Object>) controllers.DisplayController.ShowOneQuestions(QuestionCodes[i]);
			AllQuestion[i]=(Question) getQuestion.get(0);
			Allpoint[i]=points[i];
		}

		
		
		questionLBL.setText(N + 1 + "-" + AllQuestion[N].getQuestion()+"   ("+Allpoint[N]+")points");
		Answer1RB.setText("1)" + AllQuestion[N].getAnswer1());
		Answer2RB.setText("2)" + AllQuestion[N].getAnswer2());
		Answer3RB.setText("3)" + AllQuestion[N].getAnswer3());
		Answer4RB.setText("4)" + AllQuestion[N].getAnswer4());
		questionIns.setText("instruction:"+ AllQuestion[N].getQuestionInstruction());

	}

}