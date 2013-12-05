package com.hadron.treehousenexus.eao.home.xbee;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;

public class XBeeArduino {
	
	private static XBee xbee;
	
	private XBeeArduino() {}
	
	public static XBee getXBeeInstance() throws XBeeException {
		if (xbee == null) {
			xbee = new XBee();
			xbee.open("/dev/ttyUSB1", 9600);
		}
		return xbee;
	}
	
	public static void closeXBee() {
		xbee.close();
		xbee = null;
	}

}
