package com.hadron.treehousenexus.model.home.aquarium;

import java.util.List;

import com.hadron.treehousenexus.model.home.ElectronicsSystem;
import com.hadron.treehousenexus.model.home.sensors.LiquidLevelStrip;
import com.hadron.treehousenexus.model.home.sensors.Sensor;
import com.hadron.treehousenexus.model.home.sensors.Termometer;

public class AquariumSystem extends ElectronicsSystem {
	
	Termometer termometer;
	LiquidLevelStrip liquidLevelStrip;
	
	public AquariumSystem(List<Sensor> sensors) {
		super(sensors);
	}

	@Override
	public void activate() {
		// Read temp every X seconds
		// Read water level every X seconds
	}

	@Override
	public boolean doHealthCheck() {
		return termometer.doHealthCheck() 
				&& liquidLevelStrip.doHealthCheck();
	}
	
	public void doPartialWaterChange(){
		
	}
	


}
