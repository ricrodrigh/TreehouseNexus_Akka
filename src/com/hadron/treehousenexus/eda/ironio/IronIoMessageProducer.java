package com.hadron.treehousenexus.eda.ironio;

import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;
import io.iron.ironmq.Queue;

import java.io.IOException;

import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.hadron.treehousenexus.eao.config.AppSettings;
import com.hadron.treehousenexus.eao.config.SettingsImpl;
import com.hadron.treehousenexus.eda.MessageProducer;

/**
 * 
 * @author ricardo
 *
 */
public class IronIoMessageProducer extends MessageProducer {
	
	private Client client;
	private Queue queue;
	
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public IronIoMessageProducer() {
		final SettingsImpl settings = AppSettings.SettingsProvider.get(getContext().system());
		final String projectName = settings.getPROJECT_NAME();
		final String token = settings.getTOKEN();
		final String queueName = settings.getQUEUE_NAME();
		client = new Client(projectName, token, Cloud.ironAWSUSEast);
		queue = client.queue(queueName);
		log.info("IronIo producer creator");
	}
	@Override
	protected boolean publishMessage(String message) throws IronIoProducerException {
		try {
			// Convert ArduinoMessage to corresponding readings (DepthStrip, Thermometer)
			// Update Aquarium System state
			// Send JSON of each reading
			log.debug("Sending message: " + message);
			queue.push(message);
		} catch (IOException e) {
			throw new IronIoProducerException(e);
		}
		return false;
	}

}
