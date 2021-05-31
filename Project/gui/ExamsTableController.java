package gui;


import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import java.net.URL;
import entities.Exam;
import entities.TableViewHelper;
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

public class ExamsTableController implements Initializable {
	
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
    
    private final ObservableList<Exam> dataList = FXCollections.observableArrayList();

    
    @Override
 public void initialize(URL url, ResourceBundle rb) {
    	
    	ExamCodeTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("ExamCodeTable"));
    	ExamNumberTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("ExamNumberTable"));
    	SubjectTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("SubjectTable"));
    	CourseTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("CourseTable"));
    	ExamTimeTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, Float>("ExamTimeTable"));
    	TeacherNameTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("TeacherNameTable"));
       	StudentInstructionTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("StudentInstructionTable"));
       	TeacherInstructionTable.setCellValueFactory(new PropertyValueFactory<TableViewHelper, String>("TeacherInstructionTable"));
        
    	
    }
    
    @FXML
    public void Search(ActionEvent event) {
    
    	FilteredList<Exam> filteredData = new FilteredList<Exam>(dataList,b -> true);
    	
    	if(TeacherNameSerchBTN.isPressed()) {
    		SerchByTeacherNameTXT.textProperty().addListener((Observable,oldValue,newValue)->{
    			filteredData.setPredicate(Exam->{
    				if(newValue==null || newValue.isEmpty()) {
    					return true;
    				}
    				
    				String lowerCaseFilter= newValue.toLowerCase();
    				if(Exam.getTeacherName().toLowerCase().indexOf(lowerCaseFilter)!= -1)
    					return true ;
    				return false;// doesnt match
    				
    				
    				
    			});
    		});
    		
    		
    	}else {
    		SerchByCourseNameTXT.textProperty().addListener((Observable,oldValue,newValue)->{
    			filteredData.setPredicate(Exam->{
    				if(newValue==null || newValue.isEmpty()) {
    					return true;
    				}
    				
    				String lowerCaseFilter= newValue.toLowerCase();
    				if(Exam.getExamCourse().toLowerCase().indexOf(lowerCaseFilter)!= -1)
    					return true ;
    				return false;// doesnt match
    			});
    		});	
    	}
    	
    	SortedList<Exam> sortedData = new SortedList<>(filteredData);
    //	sortedData.comparatorProperty().bind(ExamTable.comparatorProperty());
    //	ExamTable.setItems(sortedData);
    	
    		
    	
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
    public void UpdateExam(ActionEvent event) {
    	if(selectedExam!=null) {
    		Exam exam = new Exam(selectedExam.getExamCode(),selectedExam.getExamNumber(), selectedExam.getExamSubject(),
    				selectedExam.getExamCourse(),selectedExam.getExamTime(), selectedExam.getTeacherName(), null, null);
    		/////////////// send it to build new exam 
    		//func that called makeUpdate 
    		
    	}
    	
    }
    
    @FXML
    public void DeleteExam(ActionEvent event) {

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
