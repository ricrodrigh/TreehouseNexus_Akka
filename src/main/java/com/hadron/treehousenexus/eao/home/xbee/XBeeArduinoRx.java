package com.hadron.treehousenexus.eao.home.xbee;

import java.util.HashSet;
import java.util.Set;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.hadron.treehousenexus.eao.config.AppSettings;
import com.hadron.treehousenexus.eao.config.SettingsImpl;
import com.hadron.treehousenexus.eao.home.ArduinoRx;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.PacketListener;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.wpan.RxResponse16;

public class XBeeArduinoRx extends ArduinoRx implements PacketListener {

	private static XBee xbee = new XBee();
	private static XBeeArduinoRx instance;
	private static Set<ActorRef> suscribedCallbackActors;
	private static LoggingAdapter log;

	public static ArduinoRx startListener(ActorSystem system) {
		log = Logging.getLogger(system, XBeeArduinoRx.class);
		if (instance == null) {
			try {
				instance = new XBeeArduinoRx(system);
			} catch (XBeeException xbe) {
				log.error("Failed to open XBee port", xbe);
				instance = null;
			}
		}
		return instance;
	}

	public void closeListener() {
		log.debug("closing listener");
		instance = null;
		xbee.close();
	}

	public boolean suscribeCallback(ActorRef callbackActor) {
		if (instance == null) {
			return false;
		}
		return suscribedCallbackActors.add(callbackActor);
	}

	private XBeeArduinoRx(ActorSystem system) throws XBeeException {
		log.debug("Starting XBee listener");
		suscribedCallbackActors = new HashSet<ActorRef>();
		final SettingsImpl settings = AppSettings.SettingsProvider.get(system);
		final String port = settings.getXBEE_PORT();
		final int baudRate = settings.getXBEE_BAUD_RATE();

		log.info("Openning XBee port: " + port + ", baudRate: " + baudRate);
		xbee.open(port, baudRate);
		log.debug("Registering this as packetListener");
		xbee.addPacketListener(this);
	}

	public void processResponse(XBeeResponse arg0) {
		log.debug("Got XBee Message");
		if (arg0.getApiId() == ApiId.RX_16_RESPONSE) {
			RxResponse16 response = (RxResponse16) arg0;
			ArduinoMessage message = new ArduinoMessage(response.getData());
			log.debug("Data sent: " + message);
			log.debug("Suscriptions: " + suscribedCallbackActors);
			for (ActorRef callbackActor : suscribedCallbackActors) {
				callbackActor.tell(message);
			}
		}
	}
}
