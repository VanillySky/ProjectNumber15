package gui;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Connection;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginFrameController implements Initializable{
	@FXML
	private AnchorPane AnPane;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBTN;
	
	@FXML
	private Label incorrectLogin;
	/**
	 * this method is launched when the user presses a key if the key pressed is
	 * enter,the method calls the sign in button method
	 * 
	 * @param event-javaFx KeyEvent,the event that started this method
	 * @throws Exception 
	 */
	@FXML
	void EnterPressed(KeyEvent event) throws Exception {
		if (event.getCode().equals(KeyCode.ENTER))
			actionLogInBtn(new ActionEvent());
	}

	@FXML
	void mouseEntered(MouseEvent event) {
		loginBTN.getScene().setCursor(Cursor.HAND);
	}

	@FXML
	void mouseExit(MouseEvent event) {
		loginBTN.getScene().setCursor(Cursor.DEFAULT);
	}
	/**
	 * this method loads the fxml file for this gui and displays it on primaryStage
	 * Stage parameter
	 * 
	 * @param primaryStage
	 */
	
	
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/LoginFrame.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Home Page");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void actionLogInBtn(ActionEvent event) {
		String textUserName;
		String textPassword;
		textUserName = userName.getText();
		textPassword = password.getText();
		ArrayList<Object> params = new ArrayList<Object>();
		User user ;
		user = LoginController.checkUser(textUserName, textPassword);
		ChatClient.currentUser = user;
		if(user!=null) {
		if (user.getRole().equals("Teacher")) {
			try {
				params.add(new Connection(InetAddress.getLocalHost().getHostAddress(),
						InetAddress.getLocalHost().getHostName(), "Teacher", user.getUserId()));
				} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ClientMessage msg = new ClientMessage("", params, params.size());
			ClientUI.chat.accept(msg);
			ServerMessage sm = ChatClient.messageRecievedFromServerEvents.get(msg.getMethodName());
			if ((boolean) sm.getData()) {
				TeacherMenuController vSHPCC = new TeacherMenuController();
				vSHPCC.start(new Stage());
				AnPane.getScene().getWindow().hide();
			} else {
				incorrectLogin.setText("You are already logged in!");
				incorrectLogin.setVisible(true);
			}
		}
		
		
		}else {
			System.out.println("This Account is not exist");
			incorrectLogin.setText("*Login details are incorrect*");
			incorrectLogin.setVisible(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		 final ArrayList<String> msg = new ArrayList<String>();
//	        msg.add("");
//	        System.out.println("1");
//	        ClientUI.chat.accept(msg);
//	        System.out.println("10");
	}
}
