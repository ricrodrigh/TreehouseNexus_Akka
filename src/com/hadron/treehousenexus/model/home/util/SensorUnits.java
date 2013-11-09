package com.hadron.treehousenexus.model.home.util;

public enum SensorUnits {
	
	NONE("none"),
	FARENHEITH("F");
	
	private String unitName;
	
	private SensorUnits(String unitName) {
		this.unitName = unitName;
	}

}
