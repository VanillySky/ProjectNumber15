package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ExamsTableController {
	

	    @FXML
	    private TextField SerchByTeacherNameField;

	    @FXML
	    private TextField SerchByCourseNameField;

	    @FXML
	    private TableColumn<?, ?> ExamNumberTable;

	    @FXML
	    private TableColumn<?, ?> ExamCodeTable;

	    @FXML
	    private TableColumn<?, ?> ChoseQuestionNumberTable;

	    @FXML
	    private TableColumn<?, ?> ExamTimeTable;

	    @FXML
	    private TableColumn<?, ?> QuestionPointsTable;

	    @FXML
	    private TableColumn<?, ?> SubjectTable;

	    @FXML
	    private TableColumn<?, ?> CourseTable;

	    @FXML
	    private TableColumn<?, ?> StudentInstructionTable;

	    @FXML
	    private Button CEMSButton;

	    @FXML
	    private Button AddNewExamButton;

	    @FXML
	    private Button UpdateExamButton;

	    @FXML
	    private Button DeleteExamButton;

	    @FXML
	    private Button CourseNameSerchButton;

	    @FXML
	    private Button TeacherNameSerchButton;

	    @FXML
	    private Button OutButton;

	}
