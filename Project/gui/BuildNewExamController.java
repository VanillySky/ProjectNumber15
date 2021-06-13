package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Exam;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Date: June, 01 - 2021
 * 
 * @author Ahmad
 * 
 */
	

	public class BuildNewExamController implements Initializable {

		
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

		@FXML
		private Label onlytwonumberLBL1;

		@FXML
		private Label onlytwonumberLBL11;
		
		static ArrayList<String> AllExamsCode = new ArrayList<String>();
		static boolean temp,help;
		static String Examcode, Examnumber, examSubject, ExamCourse, ExamTime, StudentIns, TeacherIns,
				getquestionscodes, getpoints;

		/**
		 * The method is the main entry point for JavaFX applications.
		 * 
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
		 * 
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
		 * 
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
		 * 
		 * @param event
		 * @throws Exception 
		 */

		@FXML
		public void SignOut(ActionEvent event) throws Exception {
			LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

			ClientUI clientUI = new ClientUI();
			((Node) event.getSource()).getScene().getWindow().hide();
			clientUI.chat.quit();
			clientUI.start(new Stage());
		}

		/**
		 * This method to go to build new exam frame
		 * 
		 * @param event
		 */

		@FXML
		public void AddQuestions(ActionEvent event) {
			int count = 0;
				if (ExamNumberField.getText().isEmpty() || ExamSubjectField.getText().isEmpty()
						|| ExamCourseField.getText().isEmpty() || ExamTimeField.getText().isEmpty()) {
					
					emptyfieldLBL.setText("* please fill an important empty fields !!");
					emptyfieldLBL.setVisible(true);
					count++;
				}

			try {
				Integer.parseInt(ExamNumberField.getText());

				if ((ExamNumberField.getText().length() != 2) && (ExamNumberField.getText().length() != 0)) {
					onlytwonumberLBL.setVisible(true); // show the suitable warning message
					count++;
				}

			} catch (NumberFormatException e) {
				// if
				if (ExamNumberField.getText().length() != 0) {
					onlytwonumberLBL.setVisible(true);// show the suitable warning message
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
					onlytwonumberLBL1.setVisible(true);// show the suitable warning message
					count++;
				}
			}

			try {
				Integer.parseInt(ExamCourseField.getText());
				if ((ExamCourseField.getText().length() != 2) && (ExamCourseField.getText().length() != 0)) {
					onlytwonumberLBL11.setVisible(true);// show the suitable warning message
					count++;
				}

			} catch (NumberFormatException e) {
				if (ExamCourseField.getText().length() != 0) {
					onlytwonumberLBL11.setVisible(true);// show the suitable warning message
					count++;
				}
			}

			String ExamCodeTemp = ExamSubjectField.getText()+ExamCourseField.getText()+ExamNumberField.getText();
			for(int i=0 ; i < AllExamsCode.size();i++) 	
				if(ExamCodeTemp.equals(AllExamsCode.get(i))&&(temp==false)) {
					emptyfieldLBL.setText("this Exam code is exist");
					emptyfieldLBL.setVisible(true);
					count++;
				}
			
			
				if (count == 0) {
					Saveargs();
					QuestionsSelectionController QSCC = new QuestionsSelectionController();
					QSCC.getarugments(Examnumber, examSubject, ExamCourse, ExamTime, StudentIns, TeacherIns,
							getquestionscodes, getpoints);
					QSCC.start(new Stage());
					((Node) event.getSource()).getScene().getWindow().hide();
				}
			}

		

		/**
		 * save all the arguments because if we go another next frame and back
		 */
		public void Saveargs() {
			Examnumber = ExamNumberField.getText();
			examSubject = ExamSubjectField.getText();
			ExamCourse = ExamCourseField.getText();
			ExamTime = ExamTimeField.getText();
			StudentIns = StudentInstructionField.getText();
			TeacherIns = TeacherInstructionField.getText();
			temp=true;
		}
		
		// temp = true if we join as update 
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			if (temp) {
				ExamNumberField.setText(Examnumber);
				ExamSubjectField.setText(examSubject);
				ExamCourseField.setText(ExamCourse);
				ExamTimeField.setText(ExamTime);
				StudentInstructionField.setText(StudentIns);
				TeacherInstructionField.setText(TeacherIns);
				if(help) { // if we in add put help true we dont need to disable fields
				}
				else {
				ExamNumberField.setDisable(true);
				ExamSubjectField.setDisable(true);
				ExamCourseField.setDisable(true);}
			} else {
				ExamNumberField.setText("");
				ExamSubjectField.setText("");
				ExamCourseField.setText("");
				ExamTimeField.setText("");
				StudentInstructionField.setText("");
				TeacherInstructionField.setText("");
			}
		}

	
	}

