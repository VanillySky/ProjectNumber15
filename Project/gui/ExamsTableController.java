package gui;


import java.io.IOException;

import entities.Exam;
import entities.TableViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamsTableController {
	
	@FXML
	private TableView<TableViewHelper> ExamTable;

    @FXML
    private TextField SerchByTeacherNameTXT;

    @FXML
    private TextField SerchByCourseNameTXT;

    @FXML
    private TableColumn<TableViewHelper, String> ExamNumberTable;

    @FXML
    private TableColumn<TableViewHelper, String> ExamCodeTable;

    @FXML
    private TableColumn<TableViewHelper, String> ChoseQuestionNumberTable;

    @FXML
    private TableColumn<TableViewHelper, Float> ExamTimeTable;

    @FXML
    private TableColumn<TableViewHelper, String> QuestionPointsTable;

    @FXML
    private TableColumn<TableViewHelper, String> SubjectTable;
    
    @FXML
    private TableColumn<TableViewHelper, String> TeacherNameTable;

    @FXML
    private TableColumn<TableViewHelper, String> CourseTable;

    @FXML
    private TableColumn<TableViewHelper, String> StudentInstructionTable;
    
    @FXML
    private TableColumn<TableViewHelper, String> TeacherInstructionTable;

    @FXML
    private Button CEMSButton;

    @FXML
    private Button AddNewExamBTN;

    @FXML
    private Button UpdateExamBTN;

    @FXML
    private Button DeleteExamBTN;

    @FXML
    private Button CourseNameSerchBTN;

    @FXML
    private Button TeacherNameSerchBTN;

    @FXML
    private Button OutButton;
    
    private TableViewHelper selectedExam = null;

    @FXML
    public void AddNewExam() {
    	 AddNewExamBTN.setOnAction(event -> {
				AddNewExamBTN.getScene().getWindow().hide();

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
    public void UpdateExam(ActionEvent event) {
    	if(selectedExam!=null) {
    		Exam exam = new Exam(selectedExam.getExamCode(),selectedExam.getExamNumber(), selectedExam.getExamSubject(),
    				selectedExam.getExamCourse(),selectedExam.getExamTime(), selectedExam.getTeacherName(), null, null);
    		/////////////// send it to build new exam 
    		//func that called makeUpdate 
    		
    	}
    	
    }
    
    @FXML
    public void DeleteExam() {

    	if(selectedExam!=null) {
    		Exam exam = new Exam(selectedExam.getExamCode(),selectedExam.getExamNumber(), selectedExam.getExamSubject(),
    				selectedExam.getExamCourse(),selectedExam.getExamTime(), selectedExam.getTeacherName(), null, null);
    	ExamTable.getItems().removeAll(selectedExam);
    	}    	
    }
    
    @FXML
	void selectSale(MouseEvent event) {
		if (ExamTable.getSelectionModel().getSelectedItem() != null) {
			selectedExam = ExamTable.getSelectionModel().getSelectedItem();
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
    
}
