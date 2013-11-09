package com.hadron.treehousenexus.model.home.sensors;


public class TemperatureProbe extends Sensor implements Termometer {


	@Override
	public boolean doHealthCheck() {
		// Get a Temp reading and verify it's in valid ranges
		return false;
	}

}
