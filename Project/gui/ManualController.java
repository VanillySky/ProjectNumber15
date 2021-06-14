package gui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.ManagerMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * The manual conroller is a class to control the manual fxml
<<<<<<< Upstream, based on branch 'main' of https://github.com/VanillySky/ProjectNumber15.git
 * 
 * @author Ahmed
=======
 * @author Ahmad
>>>>>>> cf3fe4b ..
 *
 */
public class ManualController implements Initializable {

	// The main pane that conatains evreything in the gui
	@FXML
	private Pane pane;

	/**
	 * Cems button that allows the user to return to the main menu
	 */
	@FXML
	private Button CEMSButton;

	/**
	 * Download button that allows the student to download the exam to solve it
	 * manually
	 */
	@FXML
	private Button DownloadExamBTN;

	/**
	 * Out button allows the student to signout from the system
	 */
	@FXML
	private Button OutButton;

	/**
	 * Upload button allows the student to upload the exam after he solve it
	 */
	@FXML
	private Button UploadExamBTN;

	/**
	 * Back button allows the student to return to the previous frame/page (in this
	 * case to the examanation page)
	 */
	@FXML
	private Button BackButton;

	/**
	 * Is Valid label to warn the student that he didn't upload any file
	 */
	@FXML
	private Label IsntValidLBL;

	/**
	 * Submit exam button allows the student to submit the exam whenever he want
	 */
	@FXML
	private Button SubmitExamBTN;

	/**
	 * The menupane is an anchor pane that we saves the time on it
	 */
	@FXML
	private AnchorPane menuPane;

	/**
	 * This text to fill the hours in the exam time
	 */
	@FXML
	private Text hoursTimer;

	/**
	 * This text to fill the minutes in the exam time
	 */
	@FXML
	private Text MinutesTimer;

	/**
	 * This text to fill the seconds in the exam time
	 */
	@FXML
	private Text SecondsTimer;

	/**
	 * in case the student press "Submit" witout uploading any file, he recieves:
	 * "Please upload a file"
	 */
	@FXML
	private Label UploadaFileMsg;

	/*
	 * after uploading the file, the name of the uploaded file appers her
	 */
	@FXML
	private TextField FileUploadTXT;

	/*
	 * Delete button deletes the file that the student uploaded in case he uploaded
	 * a wrong
	 */
	@FXML
	private Button DeleteChosenBTN;

	/*
	 * When the exam time is done the student receives
	 * "The time is done , you can't Submit the exam"
	 */
	@FXML
	private Label timelbl;

	/*
	 * When the teacher adds time the student receives a warning message
	 */
	@FXML
	private Label AddLBL;

	static String returnedFile;
	File selectedFile;
	Map<Integer, String> numberMap;
	static Integer CurrSeconds;
	Thread thrd;
	Integer hours, min;
	static boolean timefinish;
	static String ExamCode;
	private ObservableList<ManagerMessage> dataList = FXCollections.observableArrayList();

	/**
	 * The method is the main entry point for JavaFX applications.
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
	
	/*
	 * This function  starts the timer as a countdown
	 */
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
				}
			}
		});
		thrd.start();
	}
	
	/*
	 * This function lockes the exam when the exam time ends
	 */
	void doneTime() {
		timelbl.setVisible(true);
		LoginController.ChangeLockedEXCODE(ExamCode, "locked");
	}

	/*
	 * This function prints the time in the suitable text 
	 */
	void setOutput() {
		LinkedList<Integer> currHms = secondsToHms(CurrSeconds);
		hoursTimer.setText(numberMap.get(currHms.get(0)));
		MinutesTimer.setText(numberMap.get(currHms.get(1)));
		SecondsTimer.setText(numberMap.get(currHms.get(2)));
	}
	
	/**
	 * This function signs out the student from the system and retuens to the login frame 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		thrd.stop();	//stop the timer when the student get out
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * The method is to go back to the previous frame
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

	
	/**
	 * In this function the student uploads the file to submit it 
	 * @param event
	 */
	@FXML
	public void uploadFileBTN(ActionEvent event) {
		FileChooser fc = new FileChooser();
		selectedFile = fc.showOpenDialog(null);	
		if (selectedFile != null) {	
			FileUploadTXT.setText(selectedFile.getName());
		} else	//if he didn't upload any file
			IsntValidLBL.setVisible(true);
	}

	/**
	 * Allows the student to submit the exam after he uploaded it to the system
	 * @param event
	 */
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

	/**
	 * Delete the selected file in case the student want to change it
	 * @param event
	 */
	@FXML
	void deleteFileBTN(ActionEvent event) {
		selectedFile = null;
		FileUploadTXT.setText("");
	}
	
	/**
	 * This method converts to hourse and the minutes and the seconds to the seconds
	 * @param h
	 * @param m
	 * @param s
	 * @return
	 */
	Integer hmsToSeconds(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		Integer total = hToSeconds + mToSeconds + s;
		return total;
	}
	
	/**
	 * Converts the seconds to hours, minutes and seconds 
	 * @param currSeconds
	 * @return as linked list
	 */
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

	/**
	 * Download the file to the user computer
	 * @param event
	 */
	@FXML
	public void DownloadFileBTN(ActionEvent event) {
		AddLBL.setVisible(false);
		Integer temp = hmsToSeconds(hours, min, 0);
		CurrSeconds = CurrSeconds + temp;
		// CurrSeconds = 10;
		startCountdown();

		// UserController.byteManualTest = null;
		// Message msg = new Message(MessageType.downloadedManualTest,
		// TestTypeController.code);
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Download File");
			fc.setInitialFileName(ManualController.ExamCode);
			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word Files", "*.docx"));
			File downloadedFile = fc.showSaveDialog(null);// UserController.currentStage
			System.out.println("Downloaded");
			if (downloadedFile == null)
				return;
			File ManualExam = new File(downloadedFile.getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(ManualExam);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			// bos.write(0,0,0); //UserController.byteManualTest,0,
			// UserController.byteManualTest.length
			bos.flush();
			fos.flush();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * initialize the controls in the fxml
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CurrSeconds = 0;
		timefinish = false;
		String time = ExaminationController.ExamTime;
		String[] hourmin = time.split(":");
		hours = Integer.parseInt(hourmin[0]);
		min = Integer.parseInt(hourmin[1]);
		hoursTimer.setText(hours + "");
		MinutesTimer.setText(min + "");
		SecondsTimer.setText("0");

		dataList = FXCollections
				.observableArrayList((Collection) controllers.DisplayController.ApprovedChangeTime(ExamCode)); ///// add time to examtime.. check if the manager approved
		if (dataList.size() != 0) {
			String addtime = dataList.get(0).getAddtime();
			String[] Addhourmin = addtime.split(":");
			Integer addH = Integer.parseInt(Addhourmin[0]);
			Integer addM = Integer.parseInt(Addhourmin[1]);
			Integer addSec = hmsToSeconds(addH, addM, 0);
			AddLBL.setVisible(true);
			AddLBL.setText("the Additional time: " + Addhourmin[0] + ":" + Addhourmin[1]);
			CurrSeconds += addSec;
		}
				numberMap = new TreeMap<Integer, String>();
		for (Integer i = 0; i <= 60; i++) {
			if (i >= 0 && i <= 9)
				numberMap.put(i, "0" + i.toString());
			else
				numberMap.put(i, i.toString());
		}
	}
}