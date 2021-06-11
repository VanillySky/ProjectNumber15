package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.User;

public class DeleteController {

	public void DeleteExam(String Examcode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Examcode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteExam", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	public void DeleteQuestion(String Questioncode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Questioncode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteQuestion", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	public void ManagerMessage(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteManagerMessage", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
	
	public void DeleteApprovalStudentGrade(String Username, String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(Username);
		list.add(ExamCode);
	
		ClientMessage msgFromClient = new ClientMessage("DeleteApprovalStudentGrade", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	}
	
}
