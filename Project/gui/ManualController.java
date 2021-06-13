package gui;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	private AnchorPane menuPane;

	@FXML
	private Text hoursTimer;

	@FXML
	private Text MinutesTimer;

	@FXML
	private Text SecondsTimer;

	@FXML
	private Label UploadaFileMsg;

	@FXML
	private TextField FileUploadTXT;

	@FXML
	private Button DeleteChosenBTN;

	@FXML
	private Label timelbl;

	static String returnedFile;
	File selectedFile;
	Map<Integer, String> numberMap;
	static Integer CurrSeconds;
	Thread thrd;
	static Integer hours, min;
	static boolean timefinish ;
	static String ExamCode;

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
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	void startCountdown() {
		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					while (true) {

						CurrSeconds -= 1;
						setOutput();
						Thread.sleep(1000);
						if (CurrSeconds == 0) {
							timefinish = true;
							doneTime();
							thrd.stop();
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		thrd.start();
	}

	void doneTime() {
		timelbl.setVisible(true);
		LoginController.ChangeLockedEXCODE(ExamCode, "locked");
	}
	
	void setOutput() {
		LinkedList<Integer> currHms = secondsToHms(CurrSeconds);
		hoursTimer.setText(numberMap.get(currHms.get(0)));
		MinutesTimer.setText(numberMap.get(currHms.get(1)));
		SecondsTimer.setText(numberMap.get(currHms.get(2)));
	}

	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		thrd.stop();
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
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
		thrd.stop();
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
		thrd.stop();
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

		if (timefinish == false) {

			if (selectedFile != null) {

				thrd.stop();
				returnedFile = FileUploadTXT.getText();
				SubmitConfirmationController.isAuto = false;
				SubmitConfirmationController SCC = new SubmitConfirmationController();
				SCC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();
			} else
				UploadaFileMsg.setVisible(true);
		} else {
			UnsubmittedExamController USMEC = new UnsubmittedExamController();
			USMEC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
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

	Integer hmsToSeconds(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		Integer total = hToSeconds + mToSeconds + s;
		return total;
	}

	LinkedList<Integer> secondsToHms(Integer currSeconds) {
		Integer hours = currSeconds / 3600;
		currSeconds = CurrSeconds % 3600;
		Integer minutes = CurrSeconds / 60;
		currSeconds = CurrSeconds % 60;
		Integer seconds = currSeconds;
		LinkedList<Integer> answer = new LinkedList<>();
		answer.add(hours);
		answer.add(minutes);
		answer.add(seconds);
		return answer;
	}

	@FXML
	public void DownloadFileBTN(ActionEvent event) {

		CurrSeconds = hmsToSeconds(hours, min, 0);
		//CurrSeconds = 10;
		startCountdown();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timefinish = false;
		String time = ExaminationController.ExamTime;
		String[] hourmin = time.split(":");
		hours = Integer.parseInt(hourmin[0]);
		min = Integer.parseInt(hourmin[1]);
		System.out.println(hours + ":" + min);
		hoursTimer.setText(hours + "");
		MinutesTimer.setText(min + "");
		SecondsTimer.setText("0");

		numberMap = new TreeMap<Integer, String>();
		for (Integer i = 0; i <= 60; i++) {
			if (i >= 0 && i <= 9)
				numberMap.put(i, "0" + i.toString());
			else
				numberMap.put(i, i.toString());
		}
	}

}