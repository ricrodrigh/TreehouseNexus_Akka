package com.hadron.treehousenexus.eao.home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import akka.actor.UntypedActor;

import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.hadron.treehousenexus.eao.home.envelopes.Init;
import com.hadron.treehousenexus.eao.home.xbee.XBeeArduinoRx;

public class ArduinoListener extends UntypedActor  {

	File file = new File("/home/pi/ARDUINO_OUTPUT/readings.txt");
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		if(arg0 instanceof Init) {
			//XBeeArduinoRx arduinoRx = new XBeeArduinoRx(getSelf());
			XBeeArduinoRx.startListener();
			XBeeArduinoRx.suscribeCallback(getSelf());
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
		} else if(arg0 instanceof ArduinoMessage) {
			System.out.println("Got message from XBee: " + (ArduinoMessage) arg0);
			//Phase 1 ... Just log to file
			deserialize((ArduinoMessage) arg0);
		}
	}
	
	/*
	 * Maybe send the deserializer elsewhere
	 */
	private void deserialize(ArduinoMessage message) throws IOException {
		String messageString = message.asciiToString();
		System.out.println("MessageString: " + messageString);
		String[] parts = messageString.split(",");
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(String part : parts) {
			System.out.println(part);
			bw.write(new Timestamp(new Date().getTime()) + ":" + part);
			bw.newLine();
		}
		
		bw.close();
		
	}
}
