/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.DisplayController;
import controllers.LoginController;
import controllers.UpgradeConroller;
import entities.Exam;
import entities.InExam;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author shaden
 *
 */
public class ExaminationController {
	@FXML
	private Button CEMSButton;

	@FXML
	private Label AskTeacherLBL;

	@FXML
	private Label ContainLetterMsgLBL;

	@FXML
	private Button OutButton;

	@FXML
	private Button HelpButton;

	@FXML
	private Button returnButton;

	@FXML
	private Button StartExamButton;

	@FXML
	private TextField insertCodeTxtField;

	@FXML
	private Label EmptyFieldLBL;

	@FXML
	private Label IncorrectLBL;

	static String ExamCode;
	static String ExamTime;
	static StatusExam SE;
	static int starNum;
	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();
	private ObservableList<StudentGrade> dataList2 = FXCollections.observableArrayList();

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/Examination.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Examination frame");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method is to go back to the previous frame
	 * 
	 * @param event
	 */
	@FXML
	public void PressBack(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * This method to go to the main menu
	 * 
	 * @param event
	 */
	@FXML
	public void PressCEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	
	/**
	 * @param event
	 * @throws Exception
	 * this method doing sign out 
	 */
	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * @param event
	 * you press helpButton  to help you and told you from where to get the examcode
	 */
	@FXML
	public void PressHelpButton(ActionEvent event) {
		AskTeacherLBL.setVisible(true);
	}

	/**
	 * @param event
	 * this method check the code of the exam, if he manual or auto , if its incorrect dont cant join , check if the exam lock , check if we done this exam before 
	 * and start to calculate the status exam  
	 */
	@FXML
	public void StartExam(ActionEvent event) {
		char[] chars = insertCodeTxtField.getText().toCharArray();
		int countletter = 0;
		for (char c : chars) {
			if (Character.isLetter(c))
				countletter++;
		}

		if (insertCodeTxtField.getText().isEmpty()) {
			EmptyFieldLBL.setVisible(true);
			ContainLetterMsgLBL.setVisible(false);
			IncorrectLBL.setVisible(false);
		} else if (countletter == 0) {
			EmptyFieldLBL.setVisible(false);
			ContainLetterMsgLBL.setText("The code should contains letter");
			ContainLetterMsgLBL.setVisible(true);
			IncorrectLBL.setVisible(false);
		}
		if (!insertCodeTxtField.getText().isEmpty() & !insertCodeTxtField.getText().startsWith("M")
				& !insertCodeTxtField.getText().startsWith("A")) {
			EmptyFieldLBL.setVisible(false);
			ContainLetterMsgLBL.setVisible(false);
			IncorrectLBL.setVisible(true);

		}

		/// get the ExamCode to check if the student done the exam before
		if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("M")) {

			ExamCode = LoginController.checkManual(insertCodeTxtField.getText());
		}

		if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("A")) {

			ExamCode = LoginController.checkAuto(insertCodeTxtField.getText());

		}

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController // check if student done the exam before
				.checkGradeExist(ExamCode, ChatClient.currentUser.getUserName()));
		dataList2 = FXCollections.observableArrayList((Collection) controllers.DisplayController
				.checkapprovalgradeExist(ExamCode, ChatClient.currentUser.getUserName()));
		
		if (dataList.size() == 0 && dataList2.size() == 0) {
			Date date = new Date();
			if (LoginController.checkLockedM(insertCodeTxtField.getText()).contains("unlocked")) {  //get ready to go to manual exam 
				if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("M")) {

					ExamCode = LoginController.checkManual(insertCodeTxtField.getText());
					ArrayList<Object> ArrayList = DisplayController.ShowOneExam(ExamCode);
					Exam exam = (Exam) ArrayList.get(0);
					ExamTime = exam.getExamTime();
					SE = new StatusExam(ExamCode, "0", "0", ExamTime, date.toString());
					if (DisplayController.ShowStatusExam(ExamCode).isEmpty()) { // if this exam not found in Show
																				// status
																				// exam
						AddController.AddNewExamStatus(SE);
					}
					ArrayList<Object> ArrayList1 = DisplayController.GetoneStatusExam(ExamCode);
					StatusExam st = (StatusExam) ArrayList1.get(0);
					starNum = Integer.parseInt(st.getNumberStartExam());
					starNum++;
					SubmitConfirmationController.ExamCode = ExamCode;
					int Endnumber = Integer.parseInt(st.getNumberEndExam());
					SubmitConfirmationController.endExam = Endnumber;
					SE.setNumberStartExam("" + starNum);
					UpgradeConroller.UpgradeStatusStart(SE);
					ManualController.ExamCode = ExamCode;
					ManualController MC = new ManualController();
					MC.start(new Stage());
					((Node) event.getSource()).getScene().getWindow().hide();

				}
			} else {
				ContainLetterMsgLBL.setText("The Exam is locked");
				ContainLetterMsgLBL.setVisible(true);
			}

			if (LoginController.checkLockedA(insertCodeTxtField.getText()).contains("unlocked")) {
				if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("A")) {// get ready to go to auto exam 

					ExamCode = LoginController.checkAuto(insertCodeTxtField.getText());
					ArrayList<Object> ArrayList = DisplayController.ShowOneExam(ExamCode);
					Exam exam = (Exam) ArrayList.get(0);
					ExamTime = exam.getExamTime();
					SE = new StatusExam(ExamCode, "0", "0", ExamTime, date.toString());
					if (DisplayController.ShowStatusExam(ExamCode).isEmpty()) { // if this exam not found in Show
																				// status
																				// exam
						AddController.AddNewExamStatus(SE);
					}
					ArrayList<Object> ArrayList1 = DisplayController.GetoneStatusExam(ExamCode);
					StatusExam st = (StatusExam) ArrayList1.get(0);
					starNum = Integer.parseInt(st.getNumberStartExam());
					starNum++;
					int Endnumber = Integer.parseInt(st.getNumberEndExam());
					AutoController.Endnumber = Endnumber;
					SE.setNumberStartExam("" + starNum);
					AutoLoginController.ExamCode = ExamCode;
					AutoController.ExamCode = ExamCode;
					UpgradeConroller.UpgradeStatusStart(SE);
					if (ExamCode != "") {
						AutoLoginController ALC = new AutoLoginController();
						ALC.start(new Stage());
						((Node) event.getSource()).getScene().getWindow().hide();
					}
				}

			} else {
				ContainLetterMsgLBL.setText("The Exam is locked");
				ContainLetterMsgLBL.setVisible(true);
			}

		} else {
			ContainLetterMsgLBL.setText("You already did the exam ");
			ContainLetterMsgLBL.setVisible(true);
		}

	}

}
