package gui;

import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManualController implements Initializable {
	@FXML
	private Pane pane;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button DownloadExamBTN;

	@FXML
	private Button OutButton;

	@FXML
	private Button UploadExamBTN;

	@FXML
	private Button BackButton;

	@FXML
	private Label IsntValidLBL;

	@FXML
	private Button SubmitExamBTN;

	@FXML
	private ListView<String> FileListView;

	@FXML
	private Button DeleteChosenBTN;

	@FXML
	private AnchorPane TimerPane;

	@FXML
	private Text hoursTimer;

	@FXML
	private Text MinutesTimer;

	@FXML
	private Text SecondsTimer;

	@FXML
	private Label SecondsLBL;

	@FXML
	private Label HoursLBL;

	@FXML
	private Label MinutesLBL;
	@FXML
	private AnchorPane menuPane;

	@FXML
	private Label UploadaFileMsg;

	@FXML
	private TextField FileUploadTXT;

	static String returnedFile;
	File selectedFile;
	Map<Integer, String> numberMap;
	Integer CurrSeconds;
	Thread thrd;
	static private String ExamCode;
	private float timevalue = 90;

	/**
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/Manual.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Manual");
			primaryStage.show();
//			String ExamTime = LoginController.GetExamTime(ExamCode);
//			System.out.println(ExamTime);
			//String[] timing = ExamTime.split(":");
			//CurrSeconds = hmsToSeconds(Integer.parseInt(timing[0]), Integer.parseInt(timing[1]), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * The method is to go back to the previous frame
	 * 
	 * @param event
	 */
	@FXML
	public void PressBack(ActionEvent event) {
		ExaminationController EC = new ExaminationController();
		EC.start(new Stage());
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
	public void uploadFileBTN(ActionEvent event) {
		FileChooser fc = new FileChooser();
		selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			FileUploadTXT.setText(selectedFile.getName());
		} else
			IsntValidLBL.setVisible(true);
	}
	
	@FXML
	void SubmitExam(ActionEvent event) {
		// AFTER CHECKING THE TIME
		// IF THE STUDENT STILL HAVE TIME DO
		if (selectedFile != null) {
			returnedFile = FileUploadTXT.getText();
			SubmitConfirmationController.isAuto=false;
			SubmitConfirmationController SCC = new SubmitConfirmationController();
			SCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		} else
			UploadaFileMsg.setVisible(true);
	}

	@FXML
	void deleteFileBTN(ActionEvent event) {
		FileUploadTXT.setText("");
	}

	/**
	 * This method gets the ExamCode from ExaminationController
	 * 
	 * @param event
	 */
	public void getcode(String code) {
		ManualController.ExamCode = code;

	}
	public void unStart(Stage primaryStage) {

	}

	void scrollUp() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(100));
		tr1.setToX(0);
		tr1.setToY(-240);
		tr1.setNode(menuPane);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(100));
		tr2.setToX(0);
		tr2.setToY(311);
		tr2.setToX(0);
		tr1.setNode(TimerPane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.play();
	}

	void scrolldown() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(100));
		tr1.setToX(0);
		tr1.setToY(240);
		tr1.setNode(TimerPane);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(100));
		tr2.setToX(0);
		tr2.setToY(-311);
		tr2.setToX(0);
		tr1.setNode(menuPane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.play();
	}
	
	Integer hmsToSeconds(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		return hToSeconds + mToSeconds + s;
	}

	LinkedList<Integer> secondsToHms(Integer currSeconds) {
		Integer hours = currSeconds / 3600;
		CurrSeconds %= 3600;
		Integer minutes = CurrSeconds / 60;
		CurrSeconds %= 60;
		LinkedList<Integer> answer = new LinkedList<>();
		answer.add(hours);
		answer.add(minutes);
		answer.add(CurrSeconds);
		return answer;
	}

	@FXML
	public void DownloadFileBTN(ActionEvent event) {
		/**
		 * scrollUp(); thrd = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { try { while (true) { // Countdown here
		 *           System.out.println("Start countdown ..."); setOutPut();
		 *           Thread.sleep(1000); if (CurrSeconds == 0) { thrd.stop(); } } }
		 *           catch (Exception e) { } } });
		 */
		FileChooser fc = new FileChooser();
		fc.setTitle("Download File");
		if (timevalue > 0) {
			// timevalue -= Time.deltaTime;
			fc.setInitialFileName(ManualController.ExamCode);

			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word Files", "*.docx"));
			fc.setInitialDirectory(new File("C:\\Users\\Ibrahim Qassem\\Documents"));

			Stage stage = (Stage) pane.getScene().getWindow();
			try {
				File downloadedFile = fc.showSaveDialog(stage);
			} catch (Exception ex) {
			}
		}

	}

	void setOutPut() {
		LinkedList<Integer> cuurHms = secondsToHms(CurrSeconds);
		System.out.println(cuurHms.get(0) + "." + cuurHms.get(1) + "." + cuurHms.get(2));

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * ObservableList<Integer> hoursList = FXCollections.observableArrayList();
		 * ObservableList<Integer> minutesAndSeconds =
		 * FXCollections.observableArrayList(); for (Integer i = 0; i <= 60; i++) { if
		 * (i >= 0 && i <= 24) hoursList.add(i); minutesAndSeconds.add(new Integer(i));
		 * } HoursLBL.setText(hoursList);
		 */
		// IBRAHIM QASSEM PRO

		numberMap = new TreeMap<Integer, String>();
		for (Integer i = 0; i <= 60; i++) {
			if (i >= 0 && i <= 24)
				numberMap.put(i, "0" + i.toString());
			else
				numberMap.put(i, i.toString());
		}
		//if (!returnedFile.equals(""))
			FileUploadTXT.setText(returnedFile);
	}

}