package client;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.LoginFrameController;

public class ClientUI extends Application {
	public static ClientController chat; 
	
	
	/**function that opened window of LogIn */
	public void start(Stage primaryStage) throws Exception {
		chat = new ClientController("localhost", 5555);
		try {
			LoginFrameController logIn = new LoginFrameController();
			logIn.start(primaryStage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
		System.exit(0);
	}
}
