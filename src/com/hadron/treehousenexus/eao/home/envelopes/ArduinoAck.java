package com.hadron.treehousenexus.eao.home.envelopes;

public class ArduinoAck {

	private final boolean response;
	
	public boolean isResponse() {
		return response;
	}

	public ArduinoAck(boolean response) {
		this.response = response;
	}

}
