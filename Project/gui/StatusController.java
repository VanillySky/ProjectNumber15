package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import controllers.LoginController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

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

	private ObservableList<InExam> dataList = FXCollections.observableArrayList();
	private ObservableList<StatusExam> dataList2 = FXCollections.observableArrayList();

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
		System.out.println(TeacherExamStatisticsController.Examcode);
		this.UserNameCol.setCellValueFactory((Callback) new PropertyValueFactory("userName"));
		this.StudentIDcol.setCellValueFactory((Callback) new PropertyValueFactory("userId"));
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController
				.ShowStudentsInExam(TeacherExamStatisticsController.Examcode));

		studentTable.setItems(dataList);
		ExamCodeLBL.setText(TeacherExamStatisticsController.Examcode);

		dataList2 = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowStatusExam(TeacherExamStatisticsController.Examcode));

		ExamDateLBL.setText(dataList2.get(0).getDate());
		ExamDuritionLBL.setText(TeacherExamStatisticsController.Durition);
		StartedExamLBL.setText(dataList2.get(0).getNumberStartExam());// the students who start the exam.
		SubmittedExamLBL.setText(dataList2.get(0).getNumberEndExam());

		if (!dataList2.get(0).getNumberStartExam().equals("") && !dataList2.get(0).getNumberEndExam().equals("")) {
			String NotFinished = "" + (Integer.parseInt(dataList2.get(0).getNumberStartExam())
					- Integer.parseInt(dataList2.get(0).getNumberEndExam()));
			NotFinishLBL.setText(NotFinished);
		} else if (!dataList2.get(0).getNumberStartExam().equals("") && dataList2.get(0).getNumberEndExam().equals(""))
			NotFinishLBL.setText("" + Integer.parseInt(dataList2.get(0).getNumberStartExam()));
		else if (dataList2.get(0).getNumberStartExam().equals("") && !dataList2.get(0).getNumberEndExam().equals(""))
			NotFinishLBL.setText("" + Integer.parseInt(dataList2.get(0).getNumberEndExam()));
		else
			NotFinishLBL.setText("NULL");
		if (LoginController.checkLocked(ExamCodeLBL.getText()).equals("islocked")) 
			IsLockCircle.setFill(javafx.scene.paint.Color.RED);
		else
		IsLockCircle.setFill(javafx.scene.paint.Color.GREEN);
		
	}
}