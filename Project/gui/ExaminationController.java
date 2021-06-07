/**
 * 
 */
package gui;

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
import javafx.stage.Window;

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
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void PressHelpButton(ActionEvent event) {
		AskTeacherLBL.setVisible(true);
	}

	@FXML
	public void StartExam(ActionEvent event) {
		if (insertCodeTxtField.getText().isEmpty()) {
			EmptyFieldLBL.setVisible(true);
			ContainLetterMsgLBL.setVisible(false);
			IncorrectLBL.setVisible(false);
		}
		if (!insertCodeTxtField.getText().isEmpty() & !insertCodeTxtField.getText().contains("[a-zA-Z]+")) {
			EmptyFieldLBL.setVisible(false);
			ContainLetterMsgLBL.setVisible(true);
			IncorrectLBL.setVisible(false);
		} else if (!insertCodeTxtField.getText().isEmpty() & !insertCodeTxtField.getText().startsWith("M")
				& !insertCodeTxtField.getText().startsWith("A")) {
			EmptyFieldLBL.setVisible(false);
			ContainLetterMsgLBL.setVisible(false);
			IncorrectLBL.setVisible(true);
		}
		if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("M")) {
			ManualController MC = new ManualController();
			MC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}

		if (!insertCodeTxtField.getText().isEmpty() & insertCodeTxtField.getText().startsWith("A")) {
		}
	
	}
}
