package com.hadron.treehousenexus.eao.home;

import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;

public abstract class ArduinoTx {
	public abstract boolean sendMessage(ArduinoMessage message);
}
