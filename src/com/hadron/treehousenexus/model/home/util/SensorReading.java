package com.hadron.treehousenexus.model.home.util;

/**
 * 
 * @author ricardo
 *
 * @param <Magnitude>
 * @param <Unit>
 */
public class SensorReading <Magnitude, Unit>{
	
	private Magnitude magnitude;
	private Unit unit;
	
	public SensorReading(Magnitude magnitude, Unit unit) {
		this.magnitude = magnitude;
		this.unit = unit;
	}
	
	public Magnitude getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(Magnitude magnitude) {
		this.magnitude = magnitude;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
