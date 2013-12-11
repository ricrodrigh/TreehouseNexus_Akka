package com.hadron.treehousenexus.model.home.sensors;

import com.hadron.treehousenexus.model.home.util.SensorReading;

/**
 * Represents an abstract Sensor instance
 * The generic Reading allows to set from arduino any recieved type
 * @author ricardo
 *
 */
public abstract class Sensor<Reading> {
	
	private String sensorId;
	
	public Sensor(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	
	public abstract void setReading(Reading reading);
	public abstract SensorReading<?,?> getReading();
	public abstract String toJson();
}
