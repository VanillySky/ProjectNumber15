package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TeacherMenuController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button CEMSButton;

	@FXML
	private Button OutButton;

	@FXML
	private Button StatisticsButton;

	@FXML
	private Button QuestionButton;

	@FXML
	private Button ExamButton;

	public void start(Stage primaryStage) throws Exception {
	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/TeacherMain.fxml"));
		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.show();
	}

	@FXML
	void initialize() {
		{
			ExamButton.setOnAction(event -> {
				ExamButton.getScene().getWindow().hide();

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
		}//In to  ExamsTable.fxml
	}
}
