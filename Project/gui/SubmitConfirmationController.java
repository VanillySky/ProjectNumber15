package gui;

import controllers.LoginController;
import controllers.UpgradeConroller;
import entities.StatusExam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SubmitConfirmationController {

	@FXML
	private AnchorPane ReturnAncoPane;

	@FXML
	private Button GoBackBTN;

	@FXML
	private AnchorPane SubmitAncoPane;

	@FXML
	private CheckBox ConfirmCheckBTN;

	@FXML
	private Button SubmitExamBTN;
	
    @FXML
    private Label ConfirmationLBL;
    static boolean isAuto = true;
    static int endExam;
    static String ExamCode;

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/SubmitConfirmation.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CONFIRMATION");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void GoBack(ActionEvent event) {
		ManualController MC = new ManualController();
		MC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void SubmitExam(ActionEvent event) {
		if (ConfirmCheckBTN.isSelected()) {
			StatusExam EndStatus;
			
			EndStatus=ExaminationController.SE;
			endExam+=1;
			EndStatus.setNumberEndExam(""+endExam);
			UpgradeConroller.UpgradeStatusEnd(EndStatus);
			if(ExaminationController.starNum==endExam)
				System.out.println("heelllllooooooooo");
				LoginController.ChangeLockedEXCODE(ExamCode, "locked");
			SubmittedExamController SEC = new SubmittedExamController();
			SEC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
		else 
			ConfirmationLBL.setVisible(true);
	}
}
