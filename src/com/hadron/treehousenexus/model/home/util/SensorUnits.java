package com.hadron.treehousenexus.model.home.util;

/**
 * 
 * @author ricardo
 *
 */
public enum SensorUnits {
	
	NONE("none"),
	FARENHEITH("F"),
	CELSIUS("C"),
	OHM("OHM"),
	INCH("IN");
	
	private String unitName;
	
	private SensorUnits(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return unitName;
	}
}
