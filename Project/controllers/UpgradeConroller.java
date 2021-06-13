package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.Question;
import entities.StatusExam;

/**This class is made to call all the methods from SQLConnection which has the method "UPDATE ....FROM ..."
 * @author Ibrahim Qassem
 *
 */
public class UpgradeConroller {
	
	/**update exam that already exist*/
	public static boolean UpgradeExam(Exam exam) {
		ArrayList<Object> newExam = new ArrayList<>();
		newExam.add(exam);
		System.out.println(exam.getExamNumber());
		ClientMessage msgFromClient = new ClientMessage("UpgradeExam", newExam, newExam.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	
	/**upgrade question that already exist*/
	public static boolean UpgradeQuestion(Question question) {
		ArrayList<Object> newQuestion = new ArrayList<>();
		newQuestion.add(question);
		ClientMessage msgFromClient = new ClientMessage("UpgradeQuestion", newQuestion, newQuestion.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	
	/**change status of the user that he is in  */
	public static boolean UpgradeStatusStart(StatusExam Status) {
		ArrayList<Object> newStatus = new ArrayList<>();
		newStatus.add(Status);
		ClientMessage msgFromClient = new ClientMessage("UpgradeStart", newStatus, newStatus.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	
	
	/**change status of the user that he is out  */
	public static boolean UpgradeStatusEnd(StatusExam Status) {
		ArrayList<Object> newStatus = new ArrayList<>();
		newStatus.add(Status);
		ClientMessage msgFromClient = new ClientMessage("UpgradeEnd", newStatus, newStatus.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	public static boolean UpgradeToApproved(String ExamCode,String Approved ) {
		ArrayList<Object> Approve = new ArrayList<>();
		Approve.add(Approved);
		Approve.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("UpgradeApprove", Approve, Approve.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	

}
