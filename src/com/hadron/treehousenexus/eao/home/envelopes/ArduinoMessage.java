package com.hadron.treehousenexus.eao.home.envelopes;

public class ArduinoMessage {

	private int[] payload;

	public int[] getPayload() {
		return payload;
	}

	public void setPayload(int[] payload) {
		this.payload = payload;
	}

	public ArduinoMessage(int[] payload) {
		this.payload = payload;
	}
	
	public ArduinoMessage(char[] payload) {
		this(new String(payload));
	}

	public ArduinoMessage(String payload) {
		this.payload = new int[payload.length()];
		char[] inputArray = payload.toCharArray();
		for(int i = 0; i < inputArray.length; i++) {
			this.payload[i] = inputArray[i];
		}
	}
	
	public String asciiToString(){
		char[] asciiArray = new char[payload.length - 1];
		int index = 0;
		for(int i : payload) {
			if (i > 0 && i < 256) {
				asciiArray[index++] = (char) i;
			}
		}
		return new String(asciiArray);
	}
	
	public String toString() {
		StringBuilder payloadMessage = new StringBuilder();
		payloadMessage.append("{");
		for(int d : payload) {
			payloadMessage.append(d);
			payloadMessage.append(",");
		}
		payloadMessage.deleteCharAt(payloadMessage.length() - 1);
		payloadMessage.append("}");
		return payloadMessage.toString();
	}

}
