package gui;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Range;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** This class controlls the fxml of exam report it shows the max min grade with average and statistics of grades etc...
 * @author Ibrahim Qassem
 *
 */
public class TeacherExamReportController implements Initializable {

    @FXML
    private TableView<Range> Table;

    @FXML
    private TableColumn<Range, Integer> Range95to100;

    @FXML
    private TableColumn<Range, Integer> Range94to90;

    @FXML
    private TableColumn<Range, Integer> Range85to85;

    @FXML
    private TableColumn<Range, Integer> Range80to84;

    @FXML
    private TableColumn<Range, Integer> Range75to79;

    @FXML
    private TableColumn<Range, Integer> Range70to74;

    @FXML
    private TableColumn<Range, Integer> Range65to69;

    @FXML
    private TableColumn<Range, Integer> Range55to64;

    @FXML
    private TableColumn<Range, Integer> Range0to54;

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
    private Button CEMSbutton;

    @FXML
    private Button ReturnBTN;

    @FXML
    private Label CodeExam;
    
    @FXML
    private BarChart<String, Integer> GradesChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
   static  int numberofStudents;
	static  int MaxGrade, MinGrade;
	static  double Average,median;
	static  int [] GradeRange = new int[9];
	static boolean isTeacher;
	
	ObservableList<Range> List = FXCollections.observableArrayList(
			new Range(GradeRange[0], GradeRange[1], GradeRange[2],GradeRange[3], GradeRange[4], GradeRange[5],
					GradeRange[6],GradeRange[7], GradeRange[8]));
	
    
    
    /** this function starts the fxml teacherexam
     * @param primaryStage
     */
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


	/**
	 *This function starts the initialize situatuin to present all the information that we have about the exam
	 *which is selected by the teacher.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
		
		
		Range95to100.setCellValueFactory(new PropertyValueFactory<Range, Integer>("ramge1"));
		Range94to90.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range2"));
		Range85to85.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range3"));
		Range80to84.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range4"));
		Range75to79.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range5"));
		Range70to74.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range6"));
		Range65to69.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range7"));
		Range55to64.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range8"));
		Range0to54.setCellValueFactory(new PropertyValueFactory<Range, Integer>("range9"));
     	
		Table.setItems(List);
		
		NumberofstudentsLBL.setText(Integer.toString(numberofStudents));
		AverageLBL.setText(Double.toString(Average));
		MedianLBL.setText(Double.toString(median));
		MaxGradeLBL.setText(Integer.toString(MaxGrade));
		MinGradeLBL.setText(Integer.toString(MinGrade));
		
		XYChart.Series<String, Integer> set1 = new XYChart.Series<>();
		
		set1.getData().add(new XYChart.Data<String, Integer>("95-100",GradeRange[0]));
		set1.getData().add(new XYChart.Data<String, Integer>("94-90",GradeRange[1]));
		set1.getData().add(new XYChart.Data<String, Integer>("89-85",GradeRange[2]));
		set1.getData().add(new XYChart.Data<String, Integer>("84-80",GradeRange[3]));
		set1.getData().add(new XYChart.Data<String, Integer>("79-75",GradeRange[4]));
		set1.getData().add(new XYChart.Data<String, Integer>("74-70",GradeRange[5]));
		set1.getData().add(new XYChart.Data<String, Integer>("69-65",GradeRange[6]));
		set1.getData().add(new XYChart.Data<String, Integer>("64-55",GradeRange[7]));
		set1.getData().add(new XYChart.Data<String, Integer>("54-0",GradeRange[8]));
		
		GradesChart.getData().addAll(set1);
		
		
		
	}	
	
	/**Cems button it returns the teacher to his Menu
	 * @param event
	 */
	@FXML
	public void PressCEMS(ActionEvent event) {
		if(isTeacher) {
		TeacherMenuController TMCC = new TeacherMenuController();
		TMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();}
		else {
			ManagerStatisticsController MSCC = new ManagerStatisticsController();
			MSCC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
			
		}
	}
	
	/**this function logs out from the system and returns the online situation to offline "0"
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void SignOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}
	
	/**this function returns the teacher to the examstatistics fxml 
	 * @param event
	 */
	@FXML 
	public void PressReturn(ActionEvent event) {
		if(isTeacher) {
		TeacherExamStatisticsController TESC = new TeacherExamStatisticsController();
		TESC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();}
		else {
			ManagerStatisticsController MASC = new ManagerStatisticsController();
			MASC.start(new Stage());
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}

}