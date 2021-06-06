package gui;


import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.DeleteController;
import controllers.DisplayController;
import controllers.AddController;

import java.net.URL;
import entities.Exam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
public class BuildNewQuestionController    {

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

	
	public ArrayList<String> AL = new ArrayList();
	@FXML
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/BuildNewQuestion.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Build Question");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/*
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

		});*/
		
	
	@FXML
	public void AddQ() {
		controllers.AddController.AddQuestion("1","1","1","1","1","1","1","1","1","1","1","1");
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
