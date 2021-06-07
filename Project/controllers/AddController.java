package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.Question;

public class AddController {
	public static boolean AddQuestion(Question question) {
		ArrayList<Object> list = new ArrayList<Object>();
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
}
