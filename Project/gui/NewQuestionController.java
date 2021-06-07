package gui;


import client.ChatClient;
import controllers.AddController;
import entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewQuestionController {

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
    

    @FXML
    void PressAddQuestion(ActionEvent event) {
    	int count=0;
    	if(QuestionNumTXT.getText().isEmpty()||SubjectTXT.getText().isEmpty()||QuestionTXT.getText().isEmpty()
    			|| Answer1TXT.getText().isEmpty()||Answer2TXT.getText().isEmpty()||Answer3TXT.getText().isEmpty()
    			||Answer4TXT.getText().isEmpty()) {
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

			if ((RightAnswerTXT.getText().length() != 1) && (RightAnswerTXT.getText().length() != 0)&& !RightAnswerTXT.getText().equals("1")
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
    	
    	if(count==0) {
    		
    		Question qs = new Question(SubjectTXT.getText()+QuestionNumTXT.getText(),
    				QuestionNumTXT.getText(), SubjectTXT.getText(), QuestionTXT.getText(),
    				QuestionINSTXT.getText(), Answer1TXT.getText(), Answer2TXT.getText(), 
    				Answer3TXT.getText(), Answer4TXT.getText(), RightAnswerTXT.getText(),
    				ChatClient.currentUser.getFirstName(), null);
    		
    		AddController.AddQuestion(qs);
    		BuildQuestionsController BQCC = new BuildQuestionsController();
        	BQCC.start(new Stage());
        	((Node) event.getSource()).getScene().getWindow().hide();
    		
    	}
    }

    @FXML
    void PressCEMS(ActionEvent event) {
    	TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void PressReturn(ActionEvent event) {
    	BuildQuestionsController BQCC = new BuildQuestionsController();
    	BQCC.start(new Stage());
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void SignOut(ActionEvent event) {
    	LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

}
