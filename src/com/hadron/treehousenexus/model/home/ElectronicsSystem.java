package com.hadron.treehousenexus.model.home;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.hadron.treehousenexus.model.home.sensors.Sensor;

/**
 * Represents an elecronic's system
 * Its composed of sensors
 * 
 * @author ricardo
 *
 */
public abstract class ElectronicsSystem<Reading> implements HealthCheckable{
	
	/*
	 * The sensor map must remain private and its elements available only 
	 * through access modifiers.
	 * Operations like put, remove and get must be encapsulated
	 */
	private Map<String, Sensor<Reading>> sensors;
	private final String systemId;
	
	public ElectronicsSystem(String systemId, Map<String, Sensor<Reading>> sensors){
		this.systemId = systemId;
		this.sensors = sensors;
		doHealthCheck();
	}
	
	public Sensor<Reading> getSensor(String key) {
		return sensors.get(key);
	}
	
	public Set<String> getSensorIds() {
		return sensors.keySet();
	}
	
	public Collection<Sensor<Reading>> getSensors() {
		return sensors.values();
	}
	
	public abstract void activate();

	@Override
	public boolean doHealthCheck() {		
		return true;
	}
	
	public void addSensor(Sensor<Reading> sensor) {
		String key = systemId + sensor.getSensorId();
		sensor.setSensorId(key);
		sensors.put(key, sensor);
	}
	
	
}
