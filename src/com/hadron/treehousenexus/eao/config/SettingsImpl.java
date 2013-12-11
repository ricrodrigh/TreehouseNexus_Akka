package com.hadron.treehousenexus.eao.config;

import akka.actor.Extension;

import com.typesafe.config.Config;

public class SettingsImpl implements Extension {

	private final String XBEE_PORT;
	private final int XBEE_BAUD_RATE;
	
	private final String PROJECT_NAME;
	private final String TOKEN;
	private final String QUEUE_NAME;
	
	public String getXBEE_PORT() {
		return XBEE_PORT;
	}

	public int getXBEE_BAUD_RATE() {
		return XBEE_BAUD_RATE;
	}

	public SettingsImpl(Config config) {
		XBEE_PORT = config.getString("homeEao.xbee.port");
		XBEE_BAUD_RATE = config.getInt("homeEao.xbee.baudRate");
		
		PROJECT_NAME = config.getString("homeEao.ironio.projectName");
		TOKEN = config.getString("homeEao.ironio.token");
		QUEUE_NAME = config.getString("homeEao.ironio.queueName");
	}

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public String getTOKEN() {
		return TOKEN;
	}

	public String getQUEUE_NAME() {
		return QUEUE_NAME;
	}

}
