/**
 * 
 */
package gui;

import client.ChatClient;
import client.ClientUI;
import controllers.DisplayController;
import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@FXML
	public void PressHelpButton(ActionEvent event) {
		AskTeacherLBL.setVisible(true);
	}

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
	
		if (LoginController.checkLockedM(insertCodeTxtField.getText()).contains("unlocked")) {
			if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("M")) {

				ExamCode = LoginController.checkManual(insertCodeTxtField.getText());
				if (ExamCode != "") {
					ManualController MC = new ManualController();

					MC.getcode(ExamCode);
					MC.start(new Stage());
					((Node) event.getSource()).getScene().getWindow().hide();
				}

			}
		}else {
			ContainLetterMsgLBL.setText("The Exam is locked");
			ContainLetterMsgLBL.setVisible(true);
		}
		
		if (LoginController.checkLockedA(insertCodeTxtField.getText()).contains("unlocked")) {

			if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("A")) {
				ExamCode = LoginController.checkAuto(insertCodeTxtField.getText());
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

	}
}