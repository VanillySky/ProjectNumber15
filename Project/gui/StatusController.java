package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.LoginController;
import entities.InExam;
import entities.ManagerMessage;
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
	
	@FXML
    private Label ERRLabel;
	
	@FXML
    private Label succedLBL;

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
		int count=0;
		if(ChangeTimeTXT.getText().equals("")) {
			ERRLabel.setText("There is no time to add , dont forget the instructions");
			ERRLabel.setVisible(true);
			count++;
		}else if(InstructionsTXT.getText().equals("")) {
			ERRLabel.setText("Please add instructions ");
			ERRLabel.setVisible(true);
			count++;
		}
		
		if(count==0) {
			
			ManagerMessage MM = new ManagerMessage(ExamCodeLBL.getText(), ChatClient.currentUser.getUserName(), ChangeTimeTXT.getText(), InstructionsTXT.getText(),"-");
			AddController.AddMessagetoManager(MM);
			succedLBL.setVisible(true);	
		}
	}

	@FXML
	void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@SuppressWarnings("unlikely-arg-type")
	@FXML
	void PressLock(ActionEvent event) {
		String Locked = "locked";
		String Unlocked = "unlocked";
	
		if (LoginController.checkLockedEXCODE(ExamCodeLBL.getText()).contains("locked")){
			LoginController.ChangeLockedEXCODE(ExamCodeLBL.getText(),Unlocked);
			IsLockCircle.setFill(javafx.scene.paint.Color.GREEN);
		}else {
				LoginController.ChangeLockedEXCODE(ExamCodeLBL.getText(),Locked);
				IsLockCircle.setFill(javafx.scene.paint.Color.RED);
		}
	}

	@FXML
	void PressSignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@FXML
	void PressReturn(ActionEvent event) {
		TeacherExamStatisticsController TESC = new TeacherExamStatisticsController();
		TESC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ExamCodeLBL.setText(TeacherExamStatisticsController.Examcode);

		dataList2 = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowStatusExam(TeacherExamStatisticsController.Examcode));
if(dataList2.size()!=0) {
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
}
		if (LoginController.checkLockedEXCODE(ExamCodeLBL.getText()).contains("locked"))
			IsLockCircle.setFill(javafx.scene.paint.Color.RED);
		else
			IsLockCircle.setFill(javafx.scene.paint.Color.GREEN);

	}
}