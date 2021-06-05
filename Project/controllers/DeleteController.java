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
}
