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

public class BuildNewExamController {


	@FXML
	private Label UpdateExamLBL;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button OutButton;

	@FXML
	private Button BackButton;

	@FXML
	private Button AddQuestionsButton;

	@FXML
	private TextField ExamNumberField;

	@FXML
	private TextField ExamSubjectField;

	@FXML
	private TextField ExamCourseField;

	@FXML
	private TextField ExamTimeField;

	@FXML
	private TextField StudentInstructionField;

	@FXML
	private TextField TeacherInstructionField;

	@FXML
	private ImageView ExamNumberER;

	@FXML
	private ImageView ExamSubjectER;

	@FXML
	private ImageView ExamCourseER;

	@FXML
	private ImageView ExamTimeER;

	@FXML
	private Label onlytwonumberLBL;

	@FXML
	private Label emptyfieldLBL;

	@FXML
	private Label onlynumbersLBL1;
	
	@FXML
    private Label onlytwonumberLBL1;

    @FXML
    private Label onlytwonumberLBL11;
	private String TeacherName;
	
	
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

	@FXML
	public void PressBack(ActionEvent event) {
		ExamsTableController ETCC = new ExamsTableController();
		ETCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();


	}

	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	public void AddQuestions(ActionEvent event) {
		int count = 0;
		if (ExamNumberField.getText().isEmpty() || ExamSubjectField.getText().isEmpty()
				|| ExamCourseField.getText().isEmpty() || ExamTimeField.getText().isEmpty()) {
			emptyfieldLBL.setVisible(true);
			count++;

		}
		try {
			 Integer.parseInt(ExamNumberField.getText());
			if ((ExamNumberField.getText().length() != 2) && (ExamNumberField.getText().length() != 0)) {
				onlytwonumberLBL.setVisible(true);
				count++;
			}

		} catch (NumberFormatException e) {
			if (ExamNumberField.getText().length() != 0) {
				onlytwonumberLBL.setVisible(true);
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
				onlytwonumberLBL1.setVisible(true);
				count++;
			}
		}
		
		try {
			 Integer.parseInt(ExamCourseField.getText());
			if ((ExamCourseField.getText().length() != 2) && (ExamCourseField.getText().length() != 0)) {
				onlytwonumberLBL11.setVisible(true);
				count++;
			}

		} catch (NumberFormatException e) {
			if (ExamCourseField.getText().length() != 0) {
				onlytwonumberLBL11.setVisible(true);
				count++;
			}
		}

		try {
		 Integer.parseInt(ExamTimeField.getText());
		} catch (NumberFormatException e) {
			if (ExamTimeField.getText().length() != 0) {
				onlynumbersLBL1.setVisible(true);
				count++;
			}
		}
		if (count == 0) {
			String Examcode = ExamSubjectField.getText()+ExamCourseField.getText()+ExamNumberField.getText();
			
			Exam exam = new Exam(Examcode, ExamNumberField.getText(), ExamSubjectField.getText(), ExamCourseField.getText(),
					ExamTimeField.getText(), TeacherName, null, null, StudentInstructionField.getText(), TeacherInstructionField.getText());
			QuestionsSelectionController QSCC = new QuestionsSelectionController();
			QSCC.GetExam(exam);
			QSCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}

	public void getTeacherName(String TeacherName) {
		this.TeacherName = TeacherName;
	}

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
