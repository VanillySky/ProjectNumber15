package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;

public class DisplayExams {
	@SuppressWarnings("unchecked")
	public static ArrayList<Exam> ShowExams() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		ClientMessage msgFromClient = new ClientMessage("getAllexams", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Exam>) msgFromServer.getData();
		return list;
	}
}
