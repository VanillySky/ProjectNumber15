package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.User;


public class LoginController {

	public static User checkUser(String userName, String password) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(userName);
		list.add(password);
		
		ClientMessage msgFromClient = new ClientMessage("checkUser", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		User user = (User) msgFromServer.getData();
		return user;
	}

}
