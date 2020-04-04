package com.biometry.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.biometry.app.entity.StudentMaster;
import com.biometry.app.entity.WebsocketMessage;
import com.biometry.app.service.StudentMasterService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {
	Map<String, WebSocketSession> browserMap = new HashMap();
	Map<String,WebSocketSession> arduinoMap = new HashMap(); 
	@Autowired
	StudentMasterService studentMasterService;


	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
		System.out.println("New Text Message Received");
		WebsocketMessage wm = new ObjectMapper().readValue(message.getPayload(), WebsocketMessage.class);
		if(wm.isArduinoMessage()) {
			if( !arduinoMap.containsKey(wm.getClassName()) ){
				System.out.println("true");
				arduinoMap.put(wm.getClassName(), session);
			}else if ( !wm.isNewConnection() ){
				String className = wm.getClassName();
				System.out.println("****************\n browse MAP : "+browserMap+"\n***********");
				if(browserMap.containsKey(className)) {
					StudentMaster studentMaster = studentMasterService.getByRollAndDIv(wm.getId(), wm.getClassName());
					System.out.println("Message :" +wm+" about to be sent \n");
					if(studentMaster!=null) {
						String studentJSON = new ObjectMapper().writeValueAsString(studentMaster);
						browserMap.get(className).sendMessage(new TextMessage(studentJSON));
					}else {
						System.out.println("Student not found");
					}
				}
			}
			
		}else {
			if(!browserMap.containsKey(wm.getClassName())) {
				browserMap.put(wm.getClassName(), session);
			}
		}
			
		System.out.println("");
		System.out.println("id:"+ session.getId()+" att:"+session.getAttributes());
		System.out.println("websocketMessage :" +wm);
		Thread.sleep(2000);
		session.sendMessage(message);
		System.out.println(arduinoMap);
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("session "+session.getId()+" closed with status s" +status);
		browserMap.entrySet() 
        .removeIf( 
            entry -> (session.equals(entry.getValue()))); 
		System.err.println("REMOVING RENTRY from browserMap ");
		super.afterConnectionClosed(session, status);
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
		System.out.println("New Binary Message Received");
		session.sendMessage(message);
	}
}