package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import entities.Exam;
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

public class AutoLoginController implements Initializable {

	@FXML
	private Button CEMSBTN;

	@FXML
	private Button GoToExamBTN;

	@FXML
	private Button BackBTN;

	@FXML
	private TextField IDTXT;

	@FXML
	private Label InstructionLBL;

	@FXML
	private Button SignOutBTN;
	
	@FXML
    private Label WrongLBL;

	private ObservableList<Exam> dataList = FXCollections.observableArrayList();

	@FXML
	void CEMS(ActionEvent event) {
		StudentMenuController SMC = new StudentMenuController();
		SMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	@FXML
	void PressBack(ActionEvent event) {

		ExaminationController EXCC = new ExaminationController();
		EXCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void PressSignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}


	@FXML
	void pressGoToExam(ActionEvent event) {
		
		if (IDTXT.getText().equals(ChatClient.currentUser.getUserId())) {
			///////////////////////// start time
			AutoController AC = new AutoController();
			AC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
		else {
	
			WrongLBL.setText("Wrong ID !");
			WrongLBL.setVisible(true);
			
		}
	}

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/AutoLogin.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Auto Exam Login");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowOneExam(ExaminationController.ExamCode));
		InstructionLBL.setText(dataList.get(0).getStudentInstructions());
	}

}