package gui;


import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ManualController {
	/**
	 * Button for the Cems button the get back to the main menu 
	 */
	@FXML
	private Button CEMSButton;

	/**
	 * Button to signup
	 */
	@FXML
	private Button OutButton;

	/**
	 * Button to back to the previous frame
	 */
	@FXML
	private Button BackButton;

    @FXML
    private Button DownloadExamBTN;

    @FXML
    private Button UploadExamBTN;

    @FXML
    private Label FileLBL;

    @FXML
    private Label IsntValidLBL;
    static private String ExamCode;
    
	int secondsPassed = 0;
	Timer timer = new Timer();
	TimerTask timerTask = new TimerTask() {
		public void run() {
			secondsPassed++;
		}
	};
	
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
	
	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	/**
	 * The method is to go back to the previous frame
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
		File selectedFile = fc.showSaveDialog(null);
		if(selectedFile != null) {
			FileLBL.setText(selectedFile.getName());
			
			// timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		}
		else
			IsntValidLBL.setVisible(true);
	}
	/**
	 * This method gets the ExamCode from ExaminationController 
	 * @param event
	 */
	public void getcode(String code) {
		ManualController.ExamCode = code;
		
	}
}
