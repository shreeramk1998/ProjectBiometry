package com.biometry.app.entity;

public class WebsocketMessage {

	int id;
	String className;
	private boolean arduinoMessage;
	private boolean newConnection;
	
	public WebsocketMessage() {
		super();
	}



	public WebsocketMessage(int id, String className, boolean arduinoMessage, boolean newConnection) {
		super();
		this.id = id;
		this.className = className;
		this.arduinoMessage = arduinoMessage;
		this.newConnection = newConnection;
	}
	
	
	
	
	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "WebsocketMessage [id=" + id + ", className=" + className + ", arduinoMessage=" + arduinoMessage
				+ ", newConnection=" + newConnection + "]";
	}



	public boolean isArduinoMessage() {
		return arduinoMessage;
	}



	public void setArduinoMessage(boolean arduinoMessage) {
		this.arduinoMessage = arduinoMessage;
	}



	public boolean isNewConnection() {
		return newConnection;
	}



	public void setNewConnection(boolean newConnection) {
		this.newConnection = newConnection;
	}
}
