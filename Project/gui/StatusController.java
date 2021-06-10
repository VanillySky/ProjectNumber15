package gui;

import java.net.URL;
import java.util.ResourceBundle;

import entities.InExam;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StatusController implements Initializable {

	@FXML
	private TableView<InExam> studentTable;

	@FXML
	private TableColumn<InExam, String> UserNameCol;

	@FXML
	private TableColumn<InExam, String> StudentIDcol;

	@FXML
	private Label ExamCodeLBL;

	@FXML
	private TextField ChangeTimeTXT;

	@FXML
	private TextField InstructionsTXT;

	@FXML
	private Circle IsLockCircle;

	@FXML
	private Label ExamDateLBL;

	@FXML
	private Label ExamDuritionLBL;

	@FXML
	private Label StartedExamLBL;

	@FXML
	private Label SubmittedExamLBL;

	@FXML
	private Label NotFinishLBL;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button SignOut;

	@FXML
	private Button AddTimeBTN;

	@FXML
	private Button LockBTN;

	@FXML
	private Button ReturnBTN;
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/Status.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Status Exam");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void PressAddTime(ActionEvent event) {

	}

	@FXML
	void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void PressLock(ActionEvent event) {

	}

	@FXML
	void PressSignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	void PressReturn(ActionEvent event) {
		TeacherExamStatisticsController TESC = new TeacherExamStatisticsController();
		TESC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
		

	}

}