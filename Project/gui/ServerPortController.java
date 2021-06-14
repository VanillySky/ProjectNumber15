
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.LoginController;
import entities.Connection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.EchoServer;
import server.ServerUI;
import server.SQLConnection;

/**
 * controller for ServerPort
 * 
 * @author ibrahim Qassem
 *
 */
public class ServerPortController implements Initializable {

	@FXML
	private Button connectBtn;

	@FXML
	private Button disconnectBtn;

	@FXML
	private TextField portTextField;

	@FXML
	private  TableView<Connection> connectedClientsTable;

	@FXML
	private TableColumn<Connection, String> ipAddressColumn;

	@FXML
	private TableColumn<Connection, String> nameColumn;

	@FXML
	private TableColumn<Connection, String> roleColumn;

	@FXML
	private Text numOfConnections;

	@FXML
	private TextArea Console;

	private StringBuilder ConsoleTexter;

	@FXML
	private Label connectionStatusLabel;

	@FXML
	private Circle connectionCircle;

	@FXML
	private TextField mysqlPassword;

	public static String pass_Mysql;

	private ArrayList<Connection> connectionList;
	private Thread connectionUpdater;

	
	

	/**
	 * this function loads the fxml ServerPort
	 * Stage parameter
	 * 
	 * @param primaryStage
	 */
	
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/gui/ServerPort.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		EchoServer.serverController = loader.getController();
	}

	/**
	 * this function is launched when the user clicks on connect button, the function
	 * starts the server on the port number and connects to DB
	 * 
	 * 
	 * @param event-javaFx ActionEvent,the event that started this method
	 * @return 
	 */

	@FXML
	 void connect(ActionEvent event) {
	//LoginController.alloffline();
        
		String port = portTextField.getText();

		pass_Mysql = mysqlPassword.getText();

		if (port.trim().isEmpty()) {
			System.out.println("You must enter a port number");
		} else {

			if (ServerUI.runServer(port)) {
				connectionStatusLabel.setText("Connected");

				connectionCircle.setFill(javafx.scene.paint.Color.GREEN);
				connectionUpdater = new Thread(new Runnable() {
					public void run() {
						while (true) {
							removeConnection();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
					
				if (SQLConnection.getConn() != null) {

					
					connectionUpdater.start();
					writeToConsole("Driver definition succeeded\n" + "SQL connection succeed\n"
							+ "Server listening for connections on port " + port);
				} else {
					writeToConsole("Server listening for connections on port " + port);
					writeToConsole("connection to mysql failed!");
					try {
						disconnect(null);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					}
		 else {
				connectionStatusLabel.setText("Disconnected");
				connectionCircle.setFill(javafx.scene.paint.Color.RED);
			}
		}
}
	
	@FXML
	

	/**
	 * this function is launched when the user clicks on disconnect button, the method
	 * closes the server
	 * 
	 * @param event-javaFx ActionEvent,the event that started this method
	 * @throws InterruptedException
	 */
	
	void disconnect(ActionEvent event) throws InterruptedException {
		ServerUI.closeServer();
		connectionStatusLabel.setText("Disconnected");
		connectionCircle.setFill(javafx.scene.paint.Color.RED);
		portTextField.setText("");
		if (ServerUI.sv == null)
			return;
		
		writeToConsole("Server has stopped listening for connections.");
		numOfConnections.setText("0");
	}

	/**
	 * add connections to the connected users tableview in the ServerPort
	 *            
	 */
	public synchronized void addConnection() {
		numOfConnections.setText(String.valueOf(Integer.parseInt(numOfConnections.getText()) + 1));
		connectedClientsTable.setItems(FXCollections.observableArrayList(EchoServer.clientTypes));
		connectedClientsTable.refresh();
	}

	/**
	 * this function initializes the ServerPort
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connectionList = EchoServer.clientTypes;
		ipAddressColumn.setCellValueFactory(new PropertyValueFactory<Connection, String>("ipAddress"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Connection, String>("hostName"));
		roleColumn.setCellValueFactory(new PropertyValueFactory<Connection, String>("role"));
		connectedClientsTable.setItems(FXCollections.observableArrayList(connectionList));
		ConsoleTexter = new StringBuilder();
		refresh();
	}

	/**
	 * this function removes the connection
	 */
	public synchronized void removeConnection() {
		int num = ServerUI.sv.checkDisconnectedClients();
		numOfConnections.setText(String.valueOf(Integer.parseInt(numOfConnections.getText()) - num));
	}

	/**
	 * this method refreshes the tableview of connected clients in the gui
	 */
	public void refresh() {
		connectedClientsTable.setItems(FXCollections.observableArrayList(EchoServer.clientTypes));
		connectedClientsTable.refresh();
	}

	/**
	 * this method is used to write in the console in the ServerPort
	 * 
	 * @param msg-String to be written in the console
	 */
	public void writeToConsole(String msg) {
		ConsoleTexter.append(msg + "\n");
		Console.setText(ConsoleTexter.toString());
	}

	@FXML
	void mouseEntered(MouseEvent event) {
		connectBtn.getScene().setCursor(Cursor.HAND);
	}

	@FXML
	void mouseExit(MouseEvent event) {
		connectBtn.getScene().setCursor(Cursor.DEFAULT);
	}

}
