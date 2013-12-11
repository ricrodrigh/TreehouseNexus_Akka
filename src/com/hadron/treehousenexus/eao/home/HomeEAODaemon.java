package com.hadron.treehousenexus.eao.home;

import com.hadron.treehousenexus.eao.home.actors.Master;
import com.hadron.treehousenexus.eao.home.envelopes.Init;
import com.hadron.treehousenexus.model.home.aquarium.AquariumSystem;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;

public class HomeEAODaemon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// Create an Akka system
		final ActorSystem system = ActorSystem.create("HomeEAODaemon");
		//final ActorRef master = system.actorOf(new Props(Master.class), "master");
		final ActorRef master = system.actorOf(new Props(new UntypedActorFactory() {
			private static final long serialVersionUID = 8448860786305435112L;
			private final AquariumSystem aquariumSystem = new AquariumSystem();
			
			@Override
			public Actor create() throws Exception {
				return new Master(aquariumSystem);
			}
			
		}), "master");
		master.tell(new Init());
	}

}
