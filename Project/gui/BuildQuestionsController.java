package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BuildQuestionsController {

    @FXML
    private TableView<?> QuestionTable;

    @FXML
    private TableColumn<?, ?> QuestNum;

    @FXML
    private TableColumn<?, ?> QuestCode;

    @FXML
    private TableColumn<?, ?> Course;

    @FXML
    private TableColumn<?, ?> Question;

    @FXML
    private TableColumn<?, ?> QuestInst;

    @FXML
    private TableColumn<?, ?> Answers;

    @FXML
    private TableColumn<?, ?> RightAns;

    @FXML
    private TableColumn<?, ?> Author;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button DeleteQuestionButton;

    @FXML
    private Button UpdateQuestionButton;

    @FXML
    private Button AddQuestionButton;

    @FXML
    private Button SignOutButton;

    @FXML
    private Button SerchByCourseButton;

    @FXML
    private Button SerchByTeacherButton;

    @FXML
    private TextField CourseNameTXT;

    @FXML
    private TextField teacherNameTXT;

}
