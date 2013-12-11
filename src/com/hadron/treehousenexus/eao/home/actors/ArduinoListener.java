package com.hadron.treehousenexus.eao.home.actors;

import java.io.IOException;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.hadron.treehousenexus.eao.home.ArduinoRx;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.hadron.treehousenexus.eao.home.envelopes.Init;
import com.hadron.treehousenexus.eao.home.xbee.XBeeArduinoRx;
import com.hadron.treehousenexus.eda.ironio.IronIoMessageProducer;
import com.hadron.treehousenexus.model.home.ElectronicsSystem;
import com.hadron.treehousenexus.model.home.sensors.Sensor;

/**
 * 
 * @author ricardo
 * 
 */
public class ArduinoListener extends UntypedActor {

	private LoggingAdapter log;
	private final ActorRef messageProducer;
	private final ElectronicsSystem<String> relatedSystem;

	public ArduinoListener(ElectronicsSystem<String> relatedSystem) {
		log = Logging.getLogger(getContext().system(), this);
		// Can change to any MessageProducer (MQ, other)
		messageProducer = getContext().actorOf(
				new Props(IronIoMessageProducer.class), "ironIoProducerWorker");
		this.relatedSystem = relatedSystem;
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof Init) {
			doPringSystem();
			log.info("Got start message starting XBee RX");
			ArduinoRx arduinoRx = XBeeArduinoRx.startListener(getContext()
					.system());
			log.debug("ArduinoRx instance: " + arduinoRx);
			if (arduinoRx != null) {
				log.debug("Registering self as listener");
				log.info("Suscribe result "
						+ arduinoRx.suscribeCallback(getSelf()));
			}
		} else if (arg0 instanceof ArduinoMessage) {
			// TODO: Handle exceptions from deserialize
			deserialize((ArduinoMessage) arg0);
			//doPringSystem();

		}
	}
	
	private void doPringSystem() {
		/*
		 * --- Debugging purposes ---
		 */
		log.debug("----- Printing system status Only for debug purposes ------");
		for (String sensorKey : relatedSystem.getSensorIds()) {
			log.debug("SensorKey: " + sensorKey);
//			switch (sensorKey) {
//			case "T": {
//				log.debug("T sensor values:");
//				TemperatureProbe tProbe = (TemperatureProbe) relatedSystem.getSensor(sensorKey);
//				log.debug(tProbe.getReading().getMagnitude() + " " + tProbe.getReading().getUnit());
//			}break;
//			case "D": {
//				log.debug("D sensor values");
//				Sensor sensor = relatedSystem.getSensor(sensorKey);
//				log.debug(sensor.getReading().getMagnitude() + " " + sensor.getReading().getUnit());
//			}break;
//			}
			log.debug("Json: " + relatedSystem.getSensor(sensorKey).toJson());
		}
		log.debug("-----------------");
	}

	/**
	 * Translate from Arduino msg to internal data model
	 * 
	 * @param message
	 * @throws IOException
	 */
	private void deserialize(ArduinoMessage message) throws IOException {
		String messageString = message.asciiToString();
		log.debug("Got message from XBee: " + messageString);
		String[] parts = messageString.split(",");

		for (String part : parts) {
			String key = part.substring(0, 1);
			String reading = part.substring(1, part.length());
			Sensor<String> sensor = relatedSystem.getSensor(key);
			if (sensor != null) {
				log.debug("Setting value to sensorId: " + key);
				log.debug("  - InstanceOf: " + sensor.getClass());
				sensor.setReading(reading);
				//messageProducer.tell(part);
				messageProducer.tell(sensor.toJson());
			}
		}
	}
}
