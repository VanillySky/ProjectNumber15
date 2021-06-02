package gui;

import java.io.IOException;
import java.util.ArrayList;

import entities.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuildNewQuestionController {

	@FXML
	private Button MainButton;

	@FXML
	private Button SignOutButton;

	@FXML
	private Button ReturnButton;

	@FXML
	private Button AddQuestionButton;

	@FXML
	private TextField RightAnswerTxt;

	@FXML
	private TextField CourseTxt;

	@FXML
	private TextField Answer1Txt;

	@FXML
	private TextField QuestionNumberTxt;

	@FXML
	private TextField Answer3Txt;

	@FXML
	private TextField Answer4Txt;

	@FXML
	private TextField QustionInstructionTxt;

	@FXML
	private TextField TheQuestionTxt;

	@FXML
	private TextField Answer2Txt;

	@FXML
	// DatabaseHandler dbHandler
	public ArrayList<String> AL = new ArrayList();
	//private Client cl = new Client();

	public void AddQuestionButton() {
		String author="AAAAA";
		Question Qer= new Question (QuestionNumberTxt.getText(),CourseTxt.getText()+QuestionNumberTxt.getText(),CourseTxt.getText(),TheQuestionTxt.getText()
				,QustionInstructionTxt.getText(),Answer1Txt.getText(),Answer2Txt.getText(),Answer3Txt.getText()
				,Answer4Txt.getText(),RightAnswerTxt.getText(),author,null);
		if(CourseTxt.getText().equals("")||QuestionNumberTxt.getText().equals("")||
				TheQuestionTxt.getText().equals("")||Answer1Txt.getText().equals("")||
				Answer2Txt.getText().equals("")||Answer3Txt.getText().equals("")||Answer4Txt.getText().equals("")
				||RightAnswerTxt.getText().equals("")||
		QustionInstructionTxt.getText().equals(""))
		{
			
			CourseTxt.setText("Error");
			return;
		}
		
		AddQuestionButton.setOnAction(event -> {
			AL.add(CourseTxt.getText());
			AL.add(QuestionNumberTxt.getText());
			AL.add(TheQuestionTxt.getText());
			AL.add(Answer1Txt.getText());
			AL.add(Answer2Txt.getText());
			AL.add(Answer3Txt.getText());
			AL.add(Answer4Txt.getText());
			AL.add(RightAnswerTxt.getText());
			AL.add(QustionInstructionTxt.getText());

			//cl.(AL,"addQuestion");

			AddQuestionButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/BuildQuestion.fxml"));

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
		MainButton.setOnAction(event -> {
			MainButton.getScene().getWindow().hide();

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
	public void PressReturnButton() {
		ReturnButton.setOnAction(event -> {
			ReturnButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/gui/BuildQuestions.fxml"));

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
		SignOutButton.setOnAction(event -> {
			SignOutButton.getScene().getWindow().hide();

			FXMLLoader loader = new FXMLLoader();
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			loader.setLocation(getClass().getResource("/gui/Signin.fxml"));
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

}
