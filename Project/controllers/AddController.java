package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.InExam;
import entities.ManagerMessage;
import entities.Question;
import entities.StatusExam;
import entities.StudentGrade;
import entities.*;

/**This class is made to call all the SQLConnection methods that have sql method "INSERT INTO"
 * @author Ibrahim Qassem
 *
 */
public class AddController {
	
	
	/**addnew question function  */

	public static boolean AddQuestion(Question question) {
		ArrayList<Object> list = new ArrayList<>();
		list.add(question);
		ClientMessage msgFromClient = new ClientMessage("AddNewQuestion", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		//return bool;
	}
	
	
	/**add new  exam function  */

	public static boolean AddExam(Exam exam) {
		ArrayList<Object> newExam = new ArrayList<>();
		newExam.add(exam);
		ClientMessage msgFromClient = new ClientMessage("AddNewExam", newExam, newExam.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}


	/**add student grade  */

	public static boolean AddStudentGrade(StudentGrade sG) {
		ArrayList<Object> newStudentGrade = new ArrayList<>();
		newStudentGrade .add(sG);
		ClientMessage msgFromClient = new ClientMessage("AddNewStudentGrade", newStudentGrade, newStudentGrade.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		
	}
	
	// add a new exam Response
	public static boolean ExamResponse(ExamResponse ER) {
		ArrayList<Object> newExamResponse = new ArrayList<>();
		newExamResponse.add(ER);
		ClientMessage msgFromClient = new ClientMessage("AddNewExamResponse", newExamResponse, newExamResponse.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		
	}
	
	
	/**add approval student grade  */

	public static boolean AddApprovalStudentGrade(StudentGrade sG) {
		ArrayList<Object> newStudentGrade = new ArrayList<>();
		newStudentGrade.add(sG);
		ClientMessage msgFromClient = new ClientMessage("AddNewApprovalStudentGrade", newStudentGrade, newStudentGrade.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		
	}
	
	
	/**add new message to manager  */

	public static boolean AddMessagetoManager(ManagerMessage MM) {
		ArrayList<Object> NewManagerMessage = new ArrayList<>();
		NewManagerMessage.add(MM);
		ClientMessage msgFromClient = new ClientMessage("AddMessagetoManager", NewManagerMessage, NewManagerMessage.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	public static boolean AddCommonMistake(commonmistake CM) {
		ArrayList<Object> NewCommonMistake = new ArrayList<>();
		NewCommonMistake.add(CM);
		ClientMessage msgFromClient = new ClientMessage("AddCommonMistake", NewCommonMistake, NewCommonMistake.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	
	/**  */

	public static boolean AddInExam (InExam MM) {
		ArrayList<Object> IE = new ArrayList<>();
		IE.add(MM);
		ClientMessage msgFromClient = new ClientMessage("AddInExam", IE, IE.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	/**add new exam status  */

	public static boolean AddNewExamStatus(StatusExam NewExamStatus) {
		ArrayList<Object> ANES = new ArrayList<>();
		ANES.add(NewExamStatus);
		ClientMessage msgFromClient = new ClientMessage("AddNewExamStatus", ANES, ANES.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	
	
	
	
}
