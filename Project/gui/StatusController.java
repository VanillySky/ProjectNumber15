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

/**status controller to show status of an exam num of student date etc..
 * @author Ibrahim Qassem
 *
 */
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

	/**it launchs the status fxml that shows the status of an exam
	 * @param primaryStage
	 */
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

	/**add time button to add time to exam
	 * @param event
	 */
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

	/**returns back to the menu of the teacher
	 * @param event
	 */
	@FXML
	void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**lock button to switch exam from locked/unlocked
	 * @param event
	 */
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

	/**sign out button to get out of the system and turns online to 0
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void PressSignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**return to exam statistic 
	 * @param event
	 */
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