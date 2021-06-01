package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BuildNewExamController {

	@FXML
	private  Label AddExamLBL;

	@FXML
	private  Label UpdateExamLBL;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button OutButton;

	@FXML
	private Button BackButton;

	@FXML
	private Button AddQuestionsButton;

	@FXML
	private  TextField ExamNumberField;

	@FXML
	private  TextField ExamSubjectField;

	@FXML
	private  TextField ExamCourseField;

	@FXML
	private  TextField ExamTimeField;

	@FXML
	private  TextField StudentInstructionField;

	@FXML
	private  TextField TeacherInstructionField;

	@FXML
	private  ImageView ExamNumberER;

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
	public void PressBack() {
		BackButton.setOnAction(event -> {
			BackButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/ExamsTable.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});

	}

	@FXML
	public void PressCEMS() {
		CEMSButton.setOnAction(event -> {
			CEMSButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/TeacherMain.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

	@FXML
	public void SignOut() {
		OutButton.setOnAction(event -> {
			OutButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/Signin.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

	@SuppressWarnings("unused")
	@FXML
	public void AddQuestions() {
		int count = 0;
		boolean temp1 = true;
		while (temp1) {
			if (ExamNumberField.getText().isEmpty() || ExamSubjectField.getText().isEmpty()
					|| ExamCourseField.getText().isEmpty() || ExamTimeField.getText().isEmpty()) {
				emptyfieldLBL.setVisible(true);
				count++;

			}
			try {
				int temp = Integer.parseInt(ExamNumberField.getText());
				if (ExamNumberField.getText().length() != 2) {
					onlytwonumberLBL.setVisible(true);// more than two numbers
					count++;
				}

			} catch (NumberFormatException e) {
				onlytwonumberLBL.setVisible(true);
				count++;
			}

			try {
				int temp = Integer.parseInt(ExamTimeField.getText());
			} catch (NumberFormatException e) {
				onlynumbersLBL1.setVisible(true);
				count++;
			}
			if (count > 0)
				temp1 = true;
			else
				temp1 = false;
			count = 0;

		}
		AddQuestionsButton.setOnAction(event -> {
			AddQuestionsButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/QuestionSelection.fxml"));

			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
	}

	public  void changetoNew() {
		AddExamLBL.setText("Build New Exam");
		
	}

	public  void changetoupdate() {
		AddExamLBL.setText("Update Exam");
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
