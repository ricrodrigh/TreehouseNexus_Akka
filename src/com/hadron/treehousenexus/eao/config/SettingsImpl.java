package com.hadron.treehousenexus.eao.config;

import akka.actor.Extension;

import com.typesafe.config.Config;

public class SettingsImpl implements Extension {

	private final String XBEE_PORT;
	private final int XBEE_BAUD_RATE;

	public String getXBEE_PORT() {
		return XBEE_PORT;
	}

	public int getXBEE_BAUD_RATE() {
		return XBEE_BAUD_RATE;
	}

	public SettingsImpl(Config config) {
		XBEE_PORT = config.getString("homeEao.xbee.port");
		XBEE_BAUD_RATE = config.getInt("homeEao.xbee.baudRate");
	}

}
