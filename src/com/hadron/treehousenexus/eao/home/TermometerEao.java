package com.hadron.treehousenexus.eao.home;
	
import com.hadron.treehousenexus.model.home.sensors.Sensor;
import com.hadron.treehousenexus.model.home.util.SensorReading;



public class TermometerEao {
	
	public static final String FARENHEITH = "F";
	
	/* 
	 * - Send request to arduino (Via USB/Serial)
	 *   - Using sensor's id
	 *   
	 * - Get reading from arduino (Via USB/Serial)
	 *   - Reading in JSON format
	 * 
	 * - Unmarshal input from arduino
	 * 
	 * 
	 */
	public SensorReading<Integer, String> getReading(Sensor sensor) {		
		//sensor.getSensorId();
		SensorReading<Integer, String> reading = new SensorReading<Integer, String>(25, FARENHEITH); 
		//Go to USB -> Arduino -> Termometer and get reading
		return reading;
	}
}
