package com.hadron.treehousenexus.eao.home;

import akka.actor.UntypedActor;

import com.hadron.treehousenexus.eao.home.envelopes.ArduinoAck;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.hadron.treehousenexus.eao.home.xbee.XBeeArduinoTx;

public class ArduinoWorker extends UntypedActor  {

	@Override
	public void onReceive(Object arg0) throws Exception {
		System.out.println(this.getSelf().path() + ":: got a message");
		if(arg0 instanceof ArduinoMessage) {
			ArduinoMessage message = (ArduinoMessage) arg0;
			ArduinoTx tx = new XBeeArduinoTx();
			boolean response = tx.sendMessage(message);
			System.out.println("Got response from sending message: " + response);
			System.out.println("Telling response to: " + getSender());
			getSender().tell(new ArduinoAck(response));
		
		}
	}


}
