package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.Question;

public class UpgradeConroller {
	
	
	public static boolean UpgradeExam(Exam exam) {
		ArrayList<Object> newExam = new ArrayList<>();
		newExam.add(exam);
		System.out.println(exam.getExamNumber());
		ClientMessage msgFromClient = new ClientMessage("UpgradeExam", newExam, newExam.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	
	public static boolean UpgradeQuestion(Question question) {
		ArrayList<Object> newQuestion = new ArrayList<>();
		newQuestion.add(question);
		ClientMessage msgFromClient = new ClientMessage("UpgradeQuestion", newQuestion, newQuestion.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}

}
