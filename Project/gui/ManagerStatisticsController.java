package gui;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import entities.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;


public class ManagerStatisticsController implements Initializable {

	@FXML
	private TextField SearchStudentTXT;

	@FXML
	private TextField SearchCourseTXT;

	@FXML
	private TextField SearchTeacherTXT;

	@FXML
	private TableView<StudentGrade> TableStat;

	@FXML
	private TableColumn<StudentGrade, String> StudentNameCol;

	@FXML
	private TableColumn<StudentGrade, String> ExamCodeCol;

	@FXML
	private TableColumn<StudentGrade, String> CourseCol;

	@FXML
	private TableColumn<StudentGrade, String> ExamGrade;

	@FXML
	private TableColumn<StudentGrade, String> AuthorCol;

	@FXML
	private Button CEMSBTN;

	@FXML
	private Button SearchStudentBTN;

	@FXML
	private Button SearchCourseBTN;

	@FXML
	private Button SearchTeacherBTN;

	@FXML
	private Button SignOutBTN;

	@FXML
	private Button GetReportBTN;

	@FXML
	private Button ReturnBTN;

	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ManagerStatistics.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Statistics");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void PressCEMS(ActionEvent event) {
		ManagerStatisticsController MSCC = new ManagerStatisticsController();
		MSCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void PressGetReport(ActionEvent event) {

	}

	@FXML
	void PressOut(ActionEvent event) {

		LoginFrameController LFCC = new LoginFrameController();
		LFCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void SerchByCourseName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowStudentGrade());
		TableStat.setItems(dataList);
		FilteredList<StudentGrade> filteredData = new FilteredList<StudentGrade>(dataList, b -> true);
		SearchCourseTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(StudentGrade -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (StudentGrade.getExamCourse().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<StudentGrade> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TableStat.comparatorProperty());
		TableStat.setItems(sortedData);
	}

	
	@FXML
	void SerchByStudentName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));


		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowStatistics());
		TableStat.setItems(dataList);

		FilteredList<StudentGrade> filteredData = new FilteredList<StudentGrade>(dataList, b -> true);
		SearchStudentTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(StudentGrade -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (StudentGrade.getStudentUserName().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<StudentGrade> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TableStat.comparatorProperty());
		TableStat.setItems(sortedData);
	}

	
	@FXML
	void SerchByTeacherName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowStatistics());
		TableStat.setItems(dataList);

		FilteredList<StudentGrade> filteredData = new FilteredList<StudentGrade>(dataList, b -> true);
		SearchTeacherTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(StudentGrade -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (StudentGrade.getTeacherName().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				return false;// doesnt match

			});
		});
		SortedList<StudentGrade> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TableStat.comparatorProperty());
		TableStat.setItems(sortedData);
	}


	public void PressReturn(ActionEvent event) {
		ManagerStatisticsController MSCC = new ManagerStatisticsController();
		MSCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowStudentGrade());
		TableStat.setItems(dataList);
	}
}
