
package client;

import common.ChatIF;
import entities.User;
import ocsf.client.AbstractClient;
import java.io.*;
import java.util.HashMap;

import Protocol.ServerMessage;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
	public static HashMap<String, ServerMessage> messageRecievedFromServerEvents;
	public static User currentUser;
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  public static boolean awaitResponse = false;

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
	//public static ChatClient instance;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		messageRecievedFromServerEvents = new HashMap<String, ServerMessage>();
	}

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  System.out.println("--> handleMessageFromServer");
	  
		awaitResponse = false;

		if (msg instanceof ServerMessage) {
			
			messageRecievedFromServerEvents.put(((ServerMessage) msg).getCommand(), (ServerMessage) msg);
		}
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param str The message from the UI.    
   */
  
  public void handleMessageFromClientUI(Object str)  
  {
	  try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(str);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
  }

  @Override
	protected void connectionClosed() {
		
	}
  
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {
    	
    }
  }
}
