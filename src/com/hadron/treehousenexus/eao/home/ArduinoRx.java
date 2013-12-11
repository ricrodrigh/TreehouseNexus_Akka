package com.hadron.treehousenexus.eao.home;

import akka.actor.ActorRef;


public abstract class ArduinoRx {
	public abstract boolean suscribeCallback(ActorRef callbackActor);

}
