package gui;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Range;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TeacherExamReportController implements Initializable {

    @FXML
    private TableView<Range> Table;

    @FXML
    private TableColumn<Range, Number> Range95to100;

    @FXML
    private TableColumn<Range, Number> Range94to90;

    @FXML
    private TableColumn<Range, Number> Range85to85;

    @FXML
    private TableColumn<Range, Number> Range80to84;

    @FXML
    private TableColumn<Range, Number> Range75to79;

    @FXML
    private TableColumn<Range, Number> Range70to74;

    @FXML
    private TableColumn<Range, Number> Range65to69;

    @FXML
    private TableColumn<Range, Number> Range55to64;

    @FXML
    private TableColumn<Range, Number> Range0to54;

    @FXML
    private BarChart<?, ?> ExamTable;

    @FXML
    private Button SignOutBTN;

    @FXML
    private Label NumberofstudentsLBL;

    @FXML
    private Label AverageLBL;

    @FXML
    private Label MedianLBL;

    @FXML
    private Label MaxGradeLBL;

    @FXML
    private Label MinGradeLBL;

    @FXML
    private Button CEMSBTN;

    @FXML
    private Button ReturnBTN;

    @FXML
    private Label CodeExam;
    
    
    public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/TeacherExamReport.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Exam Report");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Range95to100.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range95to100"));
		Range94to90.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range94to90"));
		Range85to85.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range85to85"));
		Range80to84.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range80to84"));
		Range75to79.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range75to79"));
		Range70to74.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range70to74"));
		Range65to69.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range65to69"));
		Range55to64.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range55to64"));
		Range0to54.setCellValueFactory(new PropertyValueFactory<Range, Number>("Range0to54"));
		
	}	
	
	@FXML
	public void PressCEMS(ActionEvent event) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	public void SignOut(ActionEvent event) {
		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	public void PressReturn(ActionEvent event) {
		TeacherExamStatisticsController TESC = new TeacherExamStatisticsController();
		TESC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}

