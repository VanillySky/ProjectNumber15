package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import controllers.LoginController;
import entities.Exam;
import entities.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

/**
 * @author axwara1
 * this class is a manager statics , appear a information for students in a table and he can do more than report 
 */
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

	@FXML
	private Label ErrorLbl;
	
    @FXML
    private Button GetTeacherReport;

    @FXML
    private Button GetCourseReport;

	private StudentGrade selectedExam = null;
	private ObservableList<StudentGrade> AllExamGrades = FXCollections.observableArrayList();
	private ArrayList<StudentGrade> ExamGrades = new ArrayList<StudentGrade>();
	private int[] Grades;
	boolean NoGrades;

	private ObservableList<StudentGrade> dataList = FXCollections.observableArrayList();

	/**
	 * @param primaryStage
	 * this method start the manager Statics FXML
	 */
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

	/**
	 * @param event
	 * go to CEMS after press CEMS logo
	 */
	@FXML
	void PressCEMS(ActionEvent event) {
		ManagerMenuController MMCC = new ManagerMenuController();
		MMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * get a report of exams by a student .
	 */
	@FXML
	void PressGetExamReport(ActionEvent event) {
		if (selectedExam != null) {
			TeacherExamReportController.numberofStudents=0;
			TeacherExamReportController.MaxGrade=0;
			TeacherExamReportController.MinGrade=0;
			TeacherExamReportController.Average=0;
			TeacherExamReportController.median=0;
			for(int i=0 ; i < 9 ;i++)
				TeacherExamReportController.GradeRange[i]=0;
			TeacherExamReportController.isTeacher = false;
			AllExamGrades = FXCollections.observableArrayList((Collection) controllers.DisplayController
					.ShowApprovedStudentStudent(selectedExam.getStudentUserName()));

			
			if (AllExamGrades.size() != 0) {
				for (int i = 0; i < AllExamGrades.size(); i++) {
					if (selectedExam.getExamCode().equals(AllExamGrades.get(i).getExamCode()))
						ExamGrades.add(AllExamGrades.get(i));
				}

				Grades = new int[ExamGrades.size()];

				TeacherExamReportController.numberofStudents = ExamGrades.size();
				int sum = 0;
				for (int i = 0; i < ExamGrades.size(); i++) {
					Grades[i] = Integer.parseInt(ExamGrades.get(i).getExamGrade());
					sum += Integer.parseInt(ExamGrades.get(i).getExamGrade());
				}
				Arrays.sort(Grades);
				if(Grades.length!=0) {
				TeacherExamReportController.MinGrade = Grades[0];
				TeacherExamReportController.MaxGrade = Grades[Grades.length - 1];
				TeacherExamReportController.Average = sum / (double) Grades.length;

				if (Grades.length % 2 == 1)
					TeacherExamReportController.median = Grades[(Grades.length + 1) / 2 - 1];
				else
					TeacherExamReportController.median = (Grades[Grades.length / 2 - 1] + Grades[Grades.length / 2])
							/ 2;

				for (int i = 0; i < Grades.length; i++) {
					if (Grades[i] >= 95 && Grades[i] <= 100)
						TeacherExamReportController.GradeRange[0]++;

					if (Grades[i] >= 90 && Grades[i] <= 94)
						TeacherExamReportController.GradeRange[1]++;

					if (Grades[i] >= 85 && Grades[i] <= 89)
						TeacherExamReportController.GradeRange[2]++;

					if (Grades[i] >= 80 && Grades[i] <= 84)
						TeacherExamReportController.GradeRange[3]++;

					if (Grades[i] >= 75 && Grades[i] <= 79)
						TeacherExamReportController.GradeRange[4]++;

					if (Grades[i] >= 70 && Grades[i] <= 74)
						TeacherExamReportController.GradeRange[5]++;

					if (Grades[i] >= 65 && Grades[i] <= 69)
						TeacherExamReportController.GradeRange[6]++;

					if (Grades[i] >= 55 && Grades[i] <= 64)
						TeacherExamReportController.GradeRange[7]++;

					if (Grades[i] >= 0 && Grades[i] <= 54)
						TeacherExamReportController.GradeRange[8]++;
				}
				}
				TeacherExamReportController TERC = new TeacherExamReportController();
				TERC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();

			} else {
				ErrorLbl.setText("there is no grades");
				ErrorLbl.setVisible(true);
			}
		} else {
			ErrorLbl.setText("please chose any exam!!");
			ErrorLbl.setVisible(true);
		}


	}

	/**
	 * @param event
	 * get a report of exams by a teacher.
	 */
	@FXML
	void PressGetTeacherReport(ActionEvent event) {
		if (selectedExam != null) {
			TeacherExamReportController.numberofStudents=0;
			TeacherExamReportController.MaxGrade=0;
			TeacherExamReportController.MinGrade=0;
			TeacherExamReportController.Average=0;
			TeacherExamReportController.median=0;
			for(int i=0 ; i < 9 ;i++)
				TeacherExamReportController.GradeRange[i]=0;
			
			TeacherExamReportController.isTeacher=false;
			AllExamGrades = FXCollections.observableArrayList(
					(Collection) controllers.DisplayController.ShowApprovedStudentTeacher(selectedExam.getTeacherName()));
			for (int i = 0; i < AllExamGrades.size(); i++) {
				if (selectedExam.getTeacherName().equals(AllExamGrades.get(i).getTeacherName()))
					ExamGrades.add(AllExamGrades.get(i));
			}

			if (ExamGrades.size() == 0)
				NoGrades = true;
			else
				NoGrades = false;

			if (NoGrades == false) {
				Grades = new int[ExamGrades.size()];

				TeacherExamReportController.numberofStudents = ExamGrades.size();
				int sum = 0;
				for (int i = 0; i < ExamGrades.size(); i++) {
					Grades[i] = Integer.parseInt(ExamGrades.get(i).getExamGrade());
					sum += Integer.parseInt(ExamGrades.get(i).getExamGrade());
				}
				Arrays.sort(Grades);
				TeacherExamReportController.MinGrade = Grades[0];
				TeacherExamReportController.MaxGrade = Grades[Grades.length - 1];
				TeacherExamReportController.Average = sum / (double) Grades.length;

				if (Grades.length % 2 == 1)
					TeacherExamReportController.median = Grades[(Grades.length + 1) / 2 - 1];
				else
					TeacherExamReportController.median = (Grades[Grades.length / 2 - 1] + Grades[Grades.length / 2])
							/ 2;

				for (int i = 0; i < Grades.length; i++) {
					if (Grades[i] >= 95 && Grades[i] <= 100)
						TeacherExamReportController.GradeRange[0]++;

					if (Grades[i] >= 90 && Grades[i] <= 94)
						TeacherExamReportController.GradeRange[1]++;

					if (Grades[i] >= 85 && Grades[i] <= 89)
						TeacherExamReportController.GradeRange[2]++;

					if (Grades[i] >= 80 && Grades[i] <= 84)
						TeacherExamReportController.GradeRange[3]++;

					if (Grades[i] >= 75 && Grades[i] <= 79)
						TeacherExamReportController.GradeRange[4]++;

					if (Grades[i] >= 70 && Grades[i] <= 74)
						TeacherExamReportController.GradeRange[5]++;

					if (Grades[i] >= 65 && Grades[i] <= 69)
						TeacherExamReportController.GradeRange[6]++;

					if (Grades[i] >= 55 && Grades[i] <= 64)
						TeacherExamReportController.GradeRange[7]++;

					if (Grades[i] >= 0 && Grades[i] <= 54)
						TeacherExamReportController.GradeRange[8]++;
				}

				TeacherExamReportController TERC = new TeacherExamReportController();
				TERC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();
			} else {
				ErrorLbl.setText("there is no grades");
				ErrorLbl.setVisible(true);
			}
		} else {
			ErrorLbl.setText("please chose any exam!!");
			ErrorLbl.setVisible(true);
		}

	}
	/**
	 * @param event
	 * get a report of exams by a course.
	 */
	@FXML
	void PressGetCourseReport(ActionEvent event) {
		if (selectedExam != null) {
			TeacherExamReportController.numberofStudents=0;
			TeacherExamReportController.MaxGrade=0;
			TeacherExamReportController.MinGrade=0;
			TeacherExamReportController.Average=0;
			TeacherExamReportController.median=0;
			for(int i=0 ; i < 9 ;i++)
				TeacherExamReportController.GradeRange[i]=0;
			TeacherExamReportController.isTeacher=false;
			AllExamGrades = FXCollections.observableArrayList(
					(Collection) controllers.DisplayController.ShowApprovedStudentCourse(selectedExam.getExamCourse()));
			for (int i = 0; i < AllExamGrades.size(); i++) {
				if (selectedExam.getExamCourse().equals(AllExamGrades.get(i).getExamCourse()))
					ExamGrades.add(AllExamGrades.get(i));
			}

			if (ExamGrades.size() == 0)
				NoGrades = true;
			else
				NoGrades = false;

			if (NoGrades == false) {
				Grades = new int[ExamGrades.size()];

				TeacherExamReportController.numberofStudents = ExamGrades.size();
				int sum = 0;
				for (int i = 0; i < ExamGrades.size(); i++) {
					Grades[i] = Integer.parseInt(ExamGrades.get(i).getExamGrade());
					sum += Integer.parseInt(ExamGrades.get(i).getExamGrade());
				}
				Arrays.sort(Grades);
				TeacherExamReportController.MinGrade = Grades[0];
				TeacherExamReportController.MaxGrade = Grades[Grades.length - 1];
				TeacherExamReportController.Average = sum / (double) Grades.length;

				if (Grades.length % 2 == 1)
					TeacherExamReportController.median = Grades[(Grades.length + 1) / 2 - 1];
				else
					TeacherExamReportController.median = (Grades[Grades.length / 2 - 1] + Grades[Grades.length / 2])
							/ 2;

				for (int i = 0; i < Grades.length; i++) {
					if (Grades[i] >= 95 && Grades[i] <= 100)
						TeacherExamReportController.GradeRange[0]++;

					if (Grades[i] >= 90 && Grades[i] <= 94)
						TeacherExamReportController.GradeRange[1]++;

					if (Grades[i] >= 85 && Grades[i] <= 89)
						TeacherExamReportController.GradeRange[2]++;

					if (Grades[i] >= 80 && Grades[i] <= 84)
						TeacherExamReportController.GradeRange[3]++;

					if (Grades[i] >= 75 && Grades[i] <= 79)
						TeacherExamReportController.GradeRange[4]++;

					if (Grades[i] >= 70 && Grades[i] <= 74)
						TeacherExamReportController.GradeRange[5]++;

					if (Grades[i] >= 65 && Grades[i] <= 69)
						TeacherExamReportController.GradeRange[6]++;

					if (Grades[i] >= 55 && Grades[i] <= 64)
						TeacherExamReportController.GradeRange[7]++;

					if (Grades[i] >= 0 && Grades[i] <= 54)
						TeacherExamReportController.GradeRange[8]++;
				}

				TeacherExamReportController TERC = new TeacherExamReportController();
				TERC.start(new Stage());
				((Node) event.getSource()).getScene().getWindow().hide();
			} else {
				ErrorLbl.setText("there is no grades");
				ErrorLbl.setVisible(true);
			}
		} else {
			ErrorLbl.setText("please chose any exam!!");
			ErrorLbl.setVisible(true);
		}

	}
	/**
	 * @param event
	 * @throws Exception
	 * this method sign out from CEMS
	 */
	@FXML
	void PressOut(ActionEvent event) throws Exception {
		LoginController.ChangeOnline(ChatClient.currentUser.getUserName(),"0");

		ClientUI clientUI = new ClientUI();
		((Node) event.getSource()).getScene().getWindow().hide();
		clientUI.chat.quit();
		clientUI.start(new Stage());
	}

	/**
	 * @param event
	 * Search by course name
	 */
	@FXML
	void SerchByCourseName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));
		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowApprovaleStudentGrade());
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

	/**
	 * @param event
	 * Search by Student Name
	 */
	@FXML
	void SerchByStudentName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowApprovaleStudentGrade());
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

	/**
	 * @param event
	 * Search by Teacher Name
	 */
	@FXML
	void SerchByTeacherName(ActionEvent event) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowApprovaleStudentGrade());
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

	/**
	 * @param event
	 * press return button to back to managerMenu
	 */
	public void PressReturn(ActionEvent event) {
		ManagerMenuController MMCC = new ManagerMenuController();
		MMCC.start(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * @param event
	 * select a grade from the table
	 */
	@FXML
	void selectExam(MouseEvent event) {
		if (TableStat.getSelectionModel().getSelectedItem() != null) {
			selectedExam = TableStat.getSelectionModel().getSelectedItem();
		}
	}

	/**
	 *start this class by put all the grades in the Table 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.StudentNameCol.setCellValueFactory((Callback) new PropertyValueFactory("StudentUserName"));
		this.ExamCodeCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCode"));
		this.CourseCol.setCellValueFactory((Callback) new PropertyValueFactory("ExamCourse"));
		this.ExamGrade.setCellValueFactory((Callback) new PropertyValueFactory("ExamGrade"));
		this.AuthorCol.setCellValueFactory((Callback) new PropertyValueFactory("TeacherName"));

		dataList = FXCollections.observableArrayList((Collection) controllers.DisplayController.ShowApprovaleStudentGrade());
		TableStat.setItems(dataList);
	}
}
