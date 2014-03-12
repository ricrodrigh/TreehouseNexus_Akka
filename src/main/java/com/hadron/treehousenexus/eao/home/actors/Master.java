package com.hadron.treehousenexus.eao.home.actors;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.hadron.treehousenexus.eao.home.envelopes.Init;
import com.hadron.treehousenexus.model.home.ElectronicsSystem;

/**
 * 
 * @author ricardo
 *
 */
public class Master extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public Master(ElectronicsSystem<String> system) {
		log.debug("Master initialized, dispatching children");
		log.info("Creating AquariumListener");
		final ActorRef aquariumRx = getContext().actorOf(new Props(new SystemUntypedActorFactory(system)), "aquariumRx");
		aquariumRx.tell(new Init());
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
	}
	
	//TODO: Add error handling logic for child errors
}

class SystemUntypedActorFactory implements UntypedActorFactory{

	private static final long serialVersionUID = 2175051224965213881L;
	final ElectronicsSystem<String> system;

	public SystemUntypedActorFactory(ElectronicsSystem<String> system) {
		this.system = system;
	}
	
	public Actor create() throws Exception {
		return new ArduinoListener(system);
	}
	
}
