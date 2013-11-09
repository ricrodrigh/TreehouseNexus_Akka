package com.hadron.treehousenexus.eao.home.xbee;

import java.util.HashSet;
import java.util.Set;

import akka.actor.ActorRef;

import com.hadron.treehousenexus.eao.home.ArduinoRx;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.PacketListener;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.wpan.RxResponse16;

public class XBeeArduinoRx extends ArduinoRx implements PacketListener {

	private static XBee xbee= new XBee();
	private static XBeeArduinoRx instance;
	private static Set<ActorRef> suscribedCallbackActors;

	public static void startListener() {
		if (instance == null) {
			instance = new XBeeArduinoRx();
		}
	}

	public void closeListener() {
		instance = null;
		xbee.close();
	}

	public static void suscribeCallback(ActorRef callbackActor) {
		suscribedCallbackActors.add(callbackActor);
	}

	private XBeeArduinoRx() {
		suscribedCallbackActors = new HashSet<ActorRef>();
		//suscribedCallbackActors.add(callbackActor);
		try {
        	xbee.open("/dev/ttyUSB0", 9600);
        	xbee.addPacketListener(this);
//			xbee.addPacketListener(instance);
		} catch (XBeeException xbe) {
			instance = null;
		}
	}

	@Override
	public void processResponse(XBeeResponse arg0) {
		System.out.println("Got XBee Message");
		if (arg0.getApiId() == ApiId.RX_16_RESPONSE) {
			RxResponse16 response = (RxResponse16) arg0;
			ArduinoMessage message = new ArduinoMessage(response.getData());
			System.out.println("Data sent: " + message);
			System.out.println("Suscriptions: " + suscribedCallbackActors);
			for (ActorRef callbackActor : suscribedCallbackActors) {
				callbackActor.tell(message);
			}
		}
	}
}
