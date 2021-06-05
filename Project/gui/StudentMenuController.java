package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StudentMenuController {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void ExamIn(ActionEvent event) {
		ExamsTableController ETCC = new ExamsTableController();
		ETCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}// In to ExamsTable.fxml a

	@FXML
	public void QuestionIn(ActionEvent event) {
		BuildQuestionsController BQCC = new BuildQuestionsController();
		BQCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	public void StatisticIn(ActionEvent event) {
		TeacherExamStatisticsController TES = new TeacherExamStatisticsController();
		TES.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();

	}
}
