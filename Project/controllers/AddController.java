package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.ManagerMessage;
import entities.Question;
import entities.StudentGrade;

public class AddController {
	public static boolean AddQuestion(Question question) {
		ArrayList<Object> list = new ArrayList<>();
		list.add(question);
		ClientMessage msgFromClient = new ClientMessage("AddNewQuestion", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		//return bool;
	}
	
	
	
	public static boolean AddExam(Exam exam) {
		ArrayList<Object> newExam = new ArrayList<>();
		newExam.add(exam);
		ClientMessage msgFromClient = new ClientMessage("AddNewExam", newExam, newExam.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}



	public static boolean AddStudentGrade(StudentGrade sG) {
		ArrayList<Object> newStudentGrade = new ArrayList<>();
		newStudentGrade .add(sG);
		ClientMessage msgFromClient = new ClientMessage("AddNewStudentGrade", newStudentGrade, newStudentGrade.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		
	}
	
	public static boolean AddApprovalStudentGrade(StudentGrade sG) {
		ArrayList<Object> newStudentGrade = new ArrayList<>();
		newStudentGrade.add(sG);
		ClientMessage msgFromClient = new ClientMessage("AddNewApprovalStudentGrade", newStudentGrade, newStudentGrade.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
		
	}
	
	public static boolean AddMessagetoManager(ManagerMessage MM) {
		ArrayList<Object> NewManagerMessage = new ArrayList<>();
		NewManagerMessage.add(MM);
		ClientMessage msgFromClient = new ClientMessage("AddMessagetoManager", NewManagerMessage, NewManagerMessage.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
}
