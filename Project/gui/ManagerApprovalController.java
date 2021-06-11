package gui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import controllers.DeleteController;
import entities.Exam;
import entities.ManagerMessage;
import entities.StudentGrade;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagerApprovalController implements Initializable {

    @FXML
    private TableView<ManagerMessage> MessageTable;

    @FXML
    private TableColumn<ManagerMessage, String> ExamCodeCol;

    @FXML
    private TableColumn<ManagerMessage, String>TeacherNameCol;

    @FXML
    private TableColumn<ManagerMessage, String> TimeChangeCol;

    @FXML
    private TableColumn<ManagerMessage, String> InstructionsCol;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button SignOutButton;

    @FXML
    private Button rejectBTN;

    @FXML
    private Button ApproveBTN;
    
    @FXML
    private Label ERRLabel;

    
	private ManagerMessage selectedMessage = null;
    
	private ObservableList<ManagerMessage> dataList = FXCollections.observableArrayList();
    
    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ManagerApproval.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Manager Approval");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    void PressApprove(ActionEvent event) {

    }

    @FXML
    void PressCEMS(ActionEvent event) {
    	ManagerMenuController MMC = new ManagerMenuController();
		MMC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void PressReject(ActionEvent event) {
    	if(selectedMessage!=null) {
    	DeleteController DCC = new DeleteController();
    	DCC.ManagerMessage(selectedMessage.getExamcode());
    	
    	dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.showManagerMessage());
		MessageTable.setItems(dataList);
    		
    		
    	}else {
    		
    	ERRLabel.setVisible(true);	
    
    	}

    }

    @FXML
    void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

    	ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
    }

    @FXML
    void selectMessage(MouseEvent event) {
    	if (MessageTable.getSelectionModel().getSelectedItem() != null) {
			selectedMessage = MessageTable.getSelectionModel().getSelectedItem();
		}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("Examcode"));
		this.TeacherNameCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		this.TimeChangeCol.setCellValueFactory((Callback) new PropertyValueFactory("addtime"));
		this.InstructionsCol.setCellValueFactory((Callback) new PropertyValueFactory("instruction"));
		dataList = FXCollections.observableArrayList(
				(Collection) controllers.DisplayController.showManagerMessage());
		MessageTable.setItems(dataList);

		
	}
    

}