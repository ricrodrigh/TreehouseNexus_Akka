package com.hadron.treehousenexus.model.home.aquarium;

import java.util.HashMap;

import com.hadron.treehousenexus.model.home.ElectronicsSystem;
import com.hadron.treehousenexus.model.home.sensors.LiquidLevelStrip;
import com.hadron.treehousenexus.model.home.sensors.Sensor;
import com.hadron.treehousenexus.model.home.sensors.TemperatureProbe;

/**
 * 
 * @author ricardo
 *
 */
public class AquariumSystem extends ElectronicsSystem<String> {
	
	TemperatureProbe termometer;
	LiquidLevelStrip liquidLevelStrip;
	
	public AquariumSystem() {
		super(new HashMap<String, Sensor<String>>());
		termometer = new TemperatureProbe("T");
		addSensor(termometer);
		liquidLevelStrip = new LiquidLevelStrip("D");
		addSensor(liquidLevelStrip);
	}

	@Override
	public void activate() {
		// Read temp every X seconds
		// Read water level every X seconds
	}

	@Override
	public boolean doHealthCheck() {
		return true;
	}
	
	public void doPartialWaterChange(){
		
	}
	


}
