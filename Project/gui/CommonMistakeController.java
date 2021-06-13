package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Exam;
import entities.commonmistake;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CommonMistakeController implements Initializable{

	@FXML
	private Button ReturnBTN;

	@FXML
	private Button CEMSBTN;

	@FXML
	private Button SignoutBTN;

	@FXML
	private TableView<commonmistake> CommonMisTable;

	@FXML
	private TableColumn<commonmistake, String> ExamCodeCol;

	@FXML
	private TableColumn<commonmistake, String> QuestionCodeCol;

	@FXML
	private TableColumn<commonmistake, String> userName1col;

	@FXML
	private TableColumn<commonmistake, String> userName2Col;
	
	private ObservableList<commonmistake> dataList = FXCollections.observableArrayList();

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/CommonMistake.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Common Mistake");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void PressSignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(), "0");
		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	@FXML
	void pressReturn(ActionEvent event) {
		TeacherExamStatisticsController TECC = new TeacherExamStatisticsController();
		TECC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.QuestionCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("QuestionCode"));
		this.userName1col.setCellValueFactory((Callback) new PropertyValueFactory("UserName1"));
		this.userName2Col.setCellValueFactory((Callback) new PropertyValueFactory("UserName2"));
		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.ShowCommonMistake());
		CommonMisTable.setItems(dataList);
		
		
	}

}
