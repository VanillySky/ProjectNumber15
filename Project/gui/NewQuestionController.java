package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.AddController;
import controllers.LoginController;
import controllers.UpgradeConroller;
import entities.Question;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 * @author shaden
 *
 */
public class NewQuestionController implements Initializable {

	/*
	 * 
	 */
    @FXML
    private ImageView Updatephoto;
    
    /**
     * 
     */
    @FXML
    private TextField QuestionNumTXT;

    @FXML
    private TextField SubjectTXT;

    @FXML
    private TextField QuestionTXT;

    @FXML
    private TextField Answer1TXT;

    @FXML
    private TextField Answer2TXT;

    @FXML
    private TextField Answer3TXT;

    @FXML
    private TextField Answer4TXT;

    @FXML
    private TextField QuestionINSTXT;

    @FXML
    private Button ReturnBTN;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button AddQuestionBTN;

    @FXML
    private Button SignOutButton;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Label ErrorTwoDigitLBL;

    @FXML
    private Label Error3DigitLBL;

    @FXML
    private Label ErrorSelectRDLBL;

    @FXML
    private TextField RightAnswerTXT;

    static ArrayList<String> AllQuestionscode = new ArrayList<String>();
    static String QuestionNum , Subject , Question , Answer1,Answer2,Answer3,Answer4,Rigthanswer ,QuestionIns;
    static boolean temp;// true if update ..
    
	/**
	 * The method is the main entry point for JavaFX applications.
	 * 
	 * @param primaryStage
	 */
    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/NewQuestion.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Question");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Whith this button the teacher goes to add a question frame, their he fill the question in the suitable textfeilds
     * @param event
     */
    @FXML
    void PressAddQuestion(ActionEvent event) {
    	int count=0;
    	if(QuestionNumTXT.getText().isEmpty()||SubjectTXT.getText().isEmpty()||QuestionTXT.getText().isEmpty()
    			|| Answer1TXT.getText().isEmpty()||Answer2TXT.getText().isEmpty()||Answer3TXT.getText().isEmpty()
    			||Answer4TXT.getText().isEmpty() || RightAnswerTXT.getText().isEmpty()) {
    		ErrorLabel.setText("* please fill an important empty fields !!");
			ErrorLabel.setVisible(true);
    		count++;	
    	}
    	try {
			Integer.parseInt(QuestionNumTXT.getText());

			if ((QuestionNumTXT.getText().length() != 3) && (QuestionNumTXT.getText().length() != 0)) {
				ErrorTwoDigitLBL.setVisible(true); // show the suitable warning message
				count++;
			}

		} catch (NumberFormatException e) {
			// if
			if (QuestionNumTXT.getText().length() != 0) {
				ErrorTwoDigitLBL.setVisible(true);// show the suitable warning message
				count++;
			}
		}
    	try {
			Integer.parseInt(SubjectTXT.getText());

			if ((SubjectTXT.getText().length() != 2) && (SubjectTXT.getText().length() != 0)) {
				Error3DigitLBL.setVisible(true); // show the suitable warning message
				count++;
			}

		} catch (NumberFormatException e) {
			// if
			if (SubjectTXT.getText().length() != 0) {
				Error3DigitLBL.setVisible(true);// show the suitable warning message
				count++;
			}
		}
    	
    	try {
			Integer.parseInt(RightAnswerTXT.getText());

			if (!RightAnswerTXT.getText().equals("1")
					&& !RightAnswerTXT.getText().equals("2")&& !RightAnswerTXT.getText().equals("3") &&
					!RightAnswerTXT.getText().equals("4")) {
				ErrorSelectRDLBL.setVisible(true); // show the suitable warning message
				count++;
			}

		} catch (NumberFormatException e) {
			// if
			if (RightAnswerTXT.getText().length()!= 0) {
				ErrorSelectRDLBL.setVisible(true);// show the suitable warning message
				count++;
			}
		}
    	
    	String QuestionCodeTemp = SubjectTXT.getText() + QuestionNumTXT.getText() ;
    	for(int i=0 ; i< AllQuestionscode.size();i++)
    		if(QuestionCodeTemp.equals(AllQuestionscode.get(i))&&(temp==false)) {
    			ErrorLabel.setText("this question code is exist");
    			ErrorLabel.setVisible(true);
        		count++;	
    		}
    	
    	
    	if(count==0) {
    		
    		Question qs = new Question(SubjectTXT.getText()+QuestionNumTXT.getText(),
    				QuestionNumTXT.getText(), SubjectTXT.getText(), QuestionTXT.getText(),
    				QuestionINSTXT.getText(), Answer1TXT.getText(), Answer2TXT.getText(), 
    				Answer3TXT.getText(), Answer4TXT.getText(), RightAnswerTXT.getText(),
    				ChatClient.currentUser.getFirstName(), null);
    		
    		if(temp) { 
    			UpgradeConroller.UpgradeQuestion(qs);
    		}
    		else {
    		AddController.AddQuestion(qs);
    		}
    		BuildQuestionsController BQCC = new BuildQuestionsController();
        	BQCC.start(new Stage());
        	((Node) event.getSource()).getScene().getWindow().hide();
    		
    	}
    }

	/**
	 * This method to go to the main menu
	 * @param event
	 */
    @FXML
    void PressCEMS(ActionEvent event) {
    	TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

	/**
	 * The method is to go back to the previous frame
	 * @param event
	 */
    @FXML
    void PressReturn(ActionEvent event) {
    	BuildQuestionsController BQCC = new BuildQuestionsController();
    	BQCC.start(new Stage());
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

	/**
	 * this method logs out from the system and updates the connection situation
	 * @param event
	 * @throws Exception 
	 */
    @FXML
    void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

    	ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
    }
    
	/**
	 * initialize the controls in the fxml
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(temp) {
			Updatephoto.setVisible(true);
			QuestionNumTXT.setText(QuestionNum);
			SubjectTXT.setText(Subject);
			QuestionTXT.setText(Question);
			Answer1TXT.setText(Answer1);
			Answer2TXT.setText(Answer2);
			Answer3TXT.setText(Answer3);
			Answer4TXT.setText(Answer4);
			RightAnswerTXT.setText(Rigthanswer);
			QuestionINSTXT.setText(QuestionIns);
			QuestionNumTXT.setDisable(true);
			SubjectTXT.setDisable(true);
			
		}else {
			Updatephoto.setVisible(false);
			QuestionNumTXT.setText("");
			SubjectTXT.setText("");
			QuestionTXT.setText("");
			Answer1TXT.setText("");
			Answer2TXT.setText("");
			Answer3TXT.setText("");
			Answer4TXT.setText("");
			RightAnswerTXT.setText("");
			QuestionINSTXT.setText("");
		}
	}
}