package com.hadron.treehousenexus.eda;

import akka.actor.UntypedActor;

/**
 * 
 * @author ricardo
 *
 */
public abstract class MessageProducer extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {
			publishMessage((String) arg0);
		}
	}
	
	protected abstract boolean publishMessage(String message) throws Exception;
}
