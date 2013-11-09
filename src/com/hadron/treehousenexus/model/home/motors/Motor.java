package com.hadron.treehousenexus.model.home.motors;

public abstract class Motor {

	abstract void run(long millis);
	//TODO: Add a run until method (Register with akka until event)
}
