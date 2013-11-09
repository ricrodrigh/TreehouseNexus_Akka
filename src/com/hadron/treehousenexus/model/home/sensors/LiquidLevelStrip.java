package com.hadron.treehousenexus.model.home.sensors;

import com.hadron.treehousenexus.model.home.HealthCheckable;
import com.hadron.treehousenexus.model.home.SensorEventProducer;

public class LiquidLevelStrip extends Sensor implements HealthCheckable, SensorEventProducer{

	@Override
	public boolean doHealthCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getReading() {
		// TODO Auto-generated method stub
		return null;
	}

}
