package com.biometry.app.entity;

public class ArduinoMessage {

	int id;

	
	@Override
	public String toString() {
		return "ArduinoMessage [id=" + id + "]";
	}

	public ArduinoMessage() {
		super();
	}

	public ArduinoMessage(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
