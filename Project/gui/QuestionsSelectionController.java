package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Exam;
import entities.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuestionsSelectionController implements Initializable {
	
	@FXML
    private Button CEMSButton;

    @FXML
    private Button OutButton;
    
    @FXML
    private TableView<Question> Table;

    @FXML
    private TableColumn<Question, String> QuestionNumberTable;

    @FXML
    private TableColumn<Question, String> QuestionCodeTable;

    @FXML
    private TableColumn<Question, String> SubjectTable;
    
    @FXML
    private TableColumn<Question, String> QuestionTable;

    @FXML
    private TableColumn<Question, String> QuestionInstractionsTable;

    @FXML
    private TableColumn<Question, String> AnswersTable;

    @FXML
    private TableColumn<Question, String> RightAnswerTable;

    @FXML
    private TableColumn<Question, String> AuthorTable;
    
    @FXML
    private TableView<Question> Table2;

    @FXML
    private TableColumn<Question, String> QuestionNumberTable2;

    @FXML
    private TableColumn<Question, String> QuestionCodeTable2;

    @FXML
    private TableColumn<Question, String> subjectTable2;
    
    @FXML
    private TableColumn<Question, String> QuestionTable2;

    @FXML
    private TableColumn<Question, String> QuestionInstractionsTable2;

    @FXML
    private TableColumn<Question, String> AnswersTable2;

    @FXML
    private TableColumn<Question, String> RightAnswerTable2;

    @FXML
    private TableColumn<Question, String> AuthorTable2;

    @FXML
    private TableColumn<Question, String> PointsTable;

    @FXML
    private TextField SerchBySubjectNameTXT;

    @FXML
    private Button SubjectNameSearchButton;

    @FXML
    private TextField SerchByTeacherNameTXT;

    @FXML
    private Button TeacherNameSearchButton;

    @FXML
    private Button AddnewQuestionBTN;

    @FXML
    private Button RemoveQuestionBTN;

    @FXML
    private Button ReturnBTN;

    @FXML
    private Button DoneBTN;

    private Question selectedQuestion = null;
    
    private final ObservableList<Question> dataList = FXCollections.observableArrayList();
    
    
    @FXML
    void search(ActionEvent event) {
    	
    	FilteredList<Question> filteredData = new FilteredList<Question>(dataList,b-> true);
    	if (TeacherNameSearchButton.isPressed()) {
			SerchByTeacherNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
				filteredData.setPredicate(Question -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();
					if (Question.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1)
						return true;
					return false;// doesnt match

				});
			});
    	} else {
    		SerchBySubjectNameTXT.textProperty().addListener((Observable, oldValue, newValue) -> {
				filteredData.setPredicate(Question-> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();
					if (Question.getSubject().toLowerCase().indexOf(lowerCaseFilter) != -1)
						return true;
					return false;// doesnt match
				});
			});
    		
    	}
    	
    	SortedList<Question> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);

    }
 
    
    @FXML
    public void PressCEMS() {
   	 CEMSButton.setOnAction(event -> {
   		 CEMSButton.getScene().getWindow().hide();

   			FXMLLoader loader = new FXMLLoader();

   			loader.setLocation(getClass().getResource("/gui/TeacherMain.fxml"));

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
    }
    
    
    @FXML
    public void AddNewQuestion(ActionEvent event) {
    	
    }
    
    @FXML
    public void RemoveQuestion(ActionEvent event) {
    	
    }
    
    @FXML
    public void PressReturn() {
    	 ReturnBTN.setOnAction(event -> {
       		 ReturnBTN.getScene().getWindow().hide();

       			FXMLLoader loader = new FXMLLoader();

       			loader.setLocation(getClass().getResource("/gui/BuildNewExam.fxml"));

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
    }
    
    @FXML
    public void PressDone() {
   	 ReturnBTN.setOnAction(event -> {
      		 ReturnBTN.getScene().getWindow().hide();

      			FXMLLoader loader = new FXMLLoader();

      			loader.setLocation(getClass().getResource("/gui/ExamTable.fxml"));

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
    }
    
    @FXML
	void selectSale(MouseEvent event) {
		if (Table.getSelectionModel().getSelectedItem() != null) {
	     	selectedQuestion = Table.getSelectionModel().getSelectedItem();
		}
	}
    
    
    
    @FXML
    public void SignOut() {
    	OutButton.setOnAction(event -> {
      		 OutButton.getScene().getWindow().hide();

      			FXMLLoader loader = new FXMLLoader();
      			loader.setLocation(getClass().getResource("/gui/LoginFrame.fxml"));
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
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuestionNumberTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionNumberTable"));
		QuestionCodeTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionCodeTable"));
		SubjectTable.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectTable"));
		QuestionTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionTable"));
		QuestionInstractionsTable.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionInstractionsTable"));
		AnswersTable.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable"));
		RightAnswerTable.setCellValueFactory(new PropertyValueFactory<Question, String>("RightAnswerTable"));
		AuthorTable.setCellValueFactory(new PropertyValueFactory<Question, String>("AuthorTable"));
		QuestionNumberTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionNumberTable2"));
		QuestionCodeTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionCodeTable2"));
		subjectTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectTable2"));
		QuestionTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionTable2"));
		QuestionInstractionsTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionInstractionsTable2"));
		AnswersTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("AnswersTable2"));
		RightAnswerTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("RightAnswerTable2"));
		AuthorTable2.setCellValueFactory(new PropertyValueFactory<Question, String>("AuthorTable2"));
		PointsTable.setCellValueFactory(new PropertyValueFactory<Question, String>("PointsTable"));
		
		
	}
}
