package com.hadron.treehousenexus.eao.home.actors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.hadron.treehousenexus.eao.home.envelopes.Init;
import com.hadron.treehousenexus.eao.home.xbee.XBeeArduinoRx;

public class ArduinoListener extends UntypedActor  {

	private File file = new File("/home/pi/ARDUINO_OUTPUT/readings.txt");
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		log.debug("Got msg");
		if(arg0 instanceof Init) {
			log.info("Got start message starting XBee RX");
			XBeeArduinoRx.startListener(getContext().system());
			log.debug("Registering self as listener");
			boolean suscribeSuccesfull = XBeeArduinoRx.suscribeCallback(getSelf());
			log.info("Was suscribe sucessfull? " + suscribeSuccesfull);
			if (!file.exists()) {
				file.createNewFile();
			}			
		} else if(arg0 instanceof ArduinoMessage) {
			log.debug("Got message from XBee: " + (ArduinoMessage) arg0);
			//Phase 1 ... Just log to file
			deserialize((ArduinoMessage) arg0);
		}
	}
	
	/*
	 * Maybe send the deserializer elsewhere
	 */
	private void deserialize(ArduinoMessage message) throws IOException {
		String messageString = message.asciiToString();
		log.debug("MessageString: " + messageString);
		String[] parts = messageString.split(",");
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(String part : parts) {
			log.debug(part);
			bw.write(new Timestamp(new Date().getTime()) + ":" + part);
			bw.newLine();
		}
		
		bw.close();
		
	}
}
