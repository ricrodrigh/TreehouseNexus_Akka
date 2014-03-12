package com.hadron.treehousenexus.eao.home.xbee;

import com.hadron.treehousenexus.eao.home.ArduinoTx;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeTimeoutException;
import com.rapplogic.xbee.api.wpan.TxRequest16;
import com.rapplogic.xbee.api.wpan.TxStatusResponse;

public class XBeeArduinoTx extends ArduinoTx {

	private XBee xbee;
	private static XBeeAddress16 destination = new XBeeAddress16(0x00, 0x02);

	@Override
	public synchronized boolean sendMessage(ArduinoMessage message) {
		try {
			xbee = XBeeArduino.getXBeeInstance();
			Thread.sleep(100);
			System.out.println("Sending: " + message.toString());
			TxRequest16 tx = new TxRequest16(destination, message.getPayload());
			TxStatusResponse status = (TxStatusResponse) xbee.sendSynchronous(tx);
			return status.isSuccess();
		} catch (XBeeTimeoutException e) {
			System.err.println("request timed out. make sure you remote XBee is configured and powered on");
		} catch (XBeeException xbe) {
			System.err.println("XBE: ");
			xbe.printStackTrace();
		} catch (Exception e) {
			System.err.println("unexpected error" + e.getMessage());
		}
		return false;
	}
}
