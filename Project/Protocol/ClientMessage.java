package Protocol;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is used to send a message from client to server
 * @author ibrahim
 *
 */
public class ClientMessage implements Serializable {
	private ArrayList<Object> parameters;
	private String methodName;
	private int numParameters;


	/**
	 * 
	 * @return methodName 
	 */
	public String getMethodName() {
		return methodName;
	}////
	/**
	 * sets the methodName 
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 
	 * @return numParameters 
	 */
	public int getNumParameters() {
		return numParameters;
	}

	/**
	 * sets the numParameters
	 * @param numParameters
	 */
	public void setNumParameters(int numParameters) {
		this.numParameters = numParameters;
	}

	/**
	 * @param methodName-the name of method to be called using SQLConnection class
	 * @param parameters-an arraylist that has the input that the SQLConnection method needs
	 * @param numParameters-the number of parameters if it's needed for any method
	 */
	public ClientMessage( String methodName,ArrayList<Object> parameters, int numParameters) {
		super();
		this.parameters = parameters;
		this.methodName = methodName;
		this.numParameters = numParameters;
	}

	/**
	 * 
	 * @return return input
	 */
	public ArrayList<Object> getParameters() {
		return parameters;
	}

	/**
	 * sets input
	 * @param parameters
	 */
	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}



}
