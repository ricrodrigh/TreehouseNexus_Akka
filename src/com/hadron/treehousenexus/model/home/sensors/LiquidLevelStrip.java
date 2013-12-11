package com.hadron.treehousenexus.model.home.sensors;

import com.google.gson.Gson;
import com.hadron.treehousenexus.model.home.HealthCheckable;
import com.hadron.treehousenexus.model.home.util.SensorReading;
import com.hadron.treehousenexus.model.home.util.SensorUnits;

/**
 * 
 * @author ricardo
 *
 */
public class LiquidLevelStrip extends Sensor<String> implements HealthCheckable {

	private SensorReading<Float, SensorUnits> probeResistance;
	private SensorReading<Float, SensorUnits> depth;
	
	public LiquidLevelStrip(String sensorId) {
		super(sensorId);
		// Define new LiquidLevelStrip with initial depth of 0 and units in inches
		depth = new SensorReading<Float, SensorUnits>(0f, SensorUnits.INCH);
		probeResistance = new SensorReading<Float, SensorUnits>(0f, SensorUnits.OHM);
	}
	
	@Override
	public boolean doHealthCheck() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public SensorReading<Float, SensorUnits> getReading() {
		return getProbeResistance();
	}
	
	/**
	 * We are interested in the probeResistance reading
	 */
	@Override
	public void setReading(String reading) {
		probeResistance.setMagnitude(new Float(reading));
		// Do resistance-depth conversion
	}

	public SensorReading<Float, SensorUnits> getDepth() {
		return depth;
	}
	
	public SensorReading<Float, SensorUnits> getProbeResistance() {
		return probeResistance;
	}

	@Override
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
