package com.hadron.treehousenexus.eao.home;

import com.hadron.treehousenexus.eao.home.envelopes.Init;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class HomeEAODaemon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create an Akka system
		final ActorSystem system = ActorSystem.create("HomeEAODaemon");
		final ActorRef master = system.actorOf(new Props(Master.class), "master");
		master.tell(new Init());
	}

}
