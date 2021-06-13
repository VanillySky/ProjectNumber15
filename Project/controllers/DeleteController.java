package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.User;

/**This class is made to call the sql methods from SQLConnection which has the method "DELETE"
 * @author Ibrahim Qassem
 *
 */
public class DeleteController {

	
	
	/** function that delete exist exam */

	public void DeleteExam(String Examcode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Examcode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteExam", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	
	/** function that delete exist question */

	public void DeleteQuestion(String Questioncode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Questioncode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteQuestion", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	/**delete menager message  */
	public void ManagerMessage(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteManagerMessage", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	
	public void DeleteInExam(String ExamCode,String UserName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(UserName);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteInExam", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	/**delete all exam  */

	public void DeleteAllExam() {
	
		ClientMessage msgFromClient = new ClientMessage("DeleteAllInExam", null, 0);
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	
	/**delete approval grade  */

	public void DeleteApprovalStudentGrade(String Username, String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Username);
		list.add(ExamCode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteApprovalStudentGrade", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
}
