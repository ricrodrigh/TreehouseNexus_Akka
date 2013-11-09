package com.hadron.treehousenexus.model.home;

import java.util.List;

import com.hadron.treehousenexus.model.home.sensors.Sensor;

public abstract class ElectronicsSystem implements HealthCheckable{
	
	List<Sensor> sensors;
	
	public ElectronicsSystem(List<Sensor> sensors){
		this.sensors = sensors;
		doHealthCheck();
	}
	
	public abstract void activate();
}
