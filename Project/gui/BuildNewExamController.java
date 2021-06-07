package gui;

import java.io.IOException;

import entities.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * Date: June, 01 - 2021
 * @author Ahmad
 * 
 */
public class BuildNewExamController {

	/**
	 * Label for the Update exam label in the scenebuilder 
	 */
	@FXML
	private Label UpdateExamLBL;

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

	/**
	 * Button to moving to Add question frame 
	 */
	@FXML
	private Button AddQuestionsButton;

	/**
	 * Text Field to insert the number of the exam
	 */
	@FXML
	private TextField ExamNumberField;

	/**
	 * Text Field to insert the subject of the exam
	 */
	@FXML
	private TextField ExamSubjectField;

	/**
	 * Text Field to insert the course of the exam
	 */
	@FXML
	private TextField ExamCourseField;

	/**
	 * Text Field to insert the time of the exam
	 */
	@FXML
	private TextField ExamTimeField;

	/**
	 * Text Field to insert the student instructions of the exam
	 */
	@FXML
	private TextField StudentInstructionField;

	/**
	 * Text Field to insert the teacher instructions of the exam
	 */
	@FXML
	private TextField TeacherInstructionField;

	/**
	 * Image view shows that the field should be fill 
	 */
	@FXML
	private ImageView ExamNumberER;

	/**
	 * Image view shows that the field should be fill 
	 */
	@FXML
	private ImageView ExamSubjectER;

	/**
	 * Image view shows that the field should be fill 
	 */
	@FXML
	private ImageView ExamCourseER;

	/**
	 * Image view shows that the field should be fill 
	 */
	@FXML
	private ImageView ExamTimeER;
	
	/**
	 * Label that showing warning message "Only two digits please"
	 */
	@FXML
	private Label onlytwonumberLBL;
	
	/**
	 * Label that showing warning message "please fill an important empty..."
	 */
	@FXML
	private Label emptyfieldLBL;
	
	/**
	 * Label that showing warning message "Only digits!!"
	 */
	@FXML
	private Label onlynumbersLBL1;

	/**
	 * Label that showing warning message "only two digitals please!!!"
	 */
	@FXML
    private Label onlytwonumberLBL1;

    /**
     * Label that showing warning message "only two digitals please!!!"
     */
    @FXML
    private Label onlytwonumberLBL11;

    /**
     * String value for the teacher name
     */
    private String TeacherName;
	
	/**
	 * The method is the main entry point for JavaFX applications.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/BuildNewExam.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Build Exam");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * The method is to go back to the previous frame
	 * @param event
	 */
	@FXML
	public void PressBack(ActionEvent event) {
		ExamsTableController ETCC = new ExamsTableController();
		ETCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * This method to go to the main menu 
	 * @param event
	 */
	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	/**
	 * This method to sign out. 
	 * @param event
	 */
	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	/**
	 * This method to go to build new exam frame
	 * @param event
	 */
	@FXML
	public void AddQuestions(ActionEvent event) {
		System.out.println(TeacherName);//At the first prints the teacher name
		int count = 0;
		//if the teacher did't enter any of the fileds marked with *
		if (ExamNumberField.getText().isEmpty() || ExamSubjectField.getText().isEmpty()
				|| ExamCourseField.getText().isEmpty() || ExamTimeField.getText().isEmpty()) {
			emptyfieldLBL.setVisible(true);//show the suitable warning message
			count++;
		}
		try {
			 Integer.parseInt(ExamNumberField.getText());
			 //if the teacher insered a number less than 2 digits
			if ((ExamNumberField.getText().length() != 2) && (ExamNumberField.getText().length() != 0)) {
				onlytwonumberLBL.setVisible(true); //show the suitable warning message
				count++;
			}

		} catch (NumberFormatException e) {
			//if 
			if (ExamNumberField.getText().length() != 0) {
				onlytwonumberLBL.setVisible(true);//show the suitable warning message
				count++;
			}
		}
		
		try {
			Integer.parseInt(ExamSubjectField.getText());
			if ((ExamSubjectField.getText().length() != 2) && (ExamSubjectField.getText().length() != 0)) {
				onlytwonumberLBL1.setVisible(true);
				count++;
			}

		} catch (NumberFormatException e) {
			if (ExamSubjectField.getText().length() != 0) {
				onlytwonumberLBL1.setVisible(true);//show the suitable warning message
				count++;
			}
		}
		
		try {
			 Integer.parseInt(ExamCourseField.getText());
			if ((ExamCourseField.getText().length() != 2) && (ExamCourseField.getText().length() != 0)) {
				onlytwonumberLBL11.setVisible(true);//show the suitable warning message
				count++;
			}

		} catch (NumberFormatException e) {
			if (ExamCourseField.getText().length() != 0) {
				onlytwonumberLBL11.setVisible(true);//show the suitable warning message
				count++;
			}
		}

		try {
		 Integer.parseInt(ExamTimeField.getText());
		} catch (NumberFormatException e) {
			if (ExamTimeField.getText().length() != 0) {
				onlynumbersLBL1.setVisible(true);//show the suitable warning message
				count++;
			}
		}
		if (count == 0) {//if every textfield filled right
		final String Examcode = ExamSubjectField.getText()+ExamCourseField.getText()+ExamNumberField.getText();				
			QuestionsSelectionController QSCC = new QuestionsSelectionController();
			QSCC.GetExam(Examcode,ExamSubjectField.getText());
			System.out.println(Examcode);
			QSCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}
	/**
	 * method that sets the teacher name
	 * @param TeacherName
	 */
	public void getTeacherName(String TeacherName) {
		this.TeacherName = TeacherName;
	}

	/**
	 * method that updated the exam-
	 * @param exam
	 */
	public void UpdateExam(Exam exam) {
		ExamNumberField.setText(exam.getExamNumber());
		ExamSubjectField.setText(exam.getExamSubject());
		ExamCourseField.setText(exam.getExamCourse());
		ExamTimeField.setText(exam.getExamTime());
		StudentInstructionField.setText(exam.getStudentInstructions());
		TeacherInstructionField.setText(exam.getTeacherInstructions());
		// go to update on add question
	}
}
