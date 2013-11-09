package com.hadron.treehousenexus.eao.home;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.hadron.treehousenexus.eao.home.envelopes.ArduinoAck;
import com.hadron.treehousenexus.eao.home.envelopes.ArduinoMessage;
import com.hadron.treehousenexus.eao.home.envelopes.Init;

public class Master extends UntypedActor implements MessageListener {

    private static int ackMode;
    private static final String queueName;
    private static Logger log = LoggerFactory.getLogger(Master.class);
    
    private boolean transacted = false;
 
    static {
        ackMode = Session.AUTO_ACKNOWLEDGE;
        queueName = "EAO.IN";
    }
    
	private final ActorRef arduinoRx;

	public Master() {
		arduinoRx = getContext().actorOf(new Props(ArduinoListener.class), "workerRx");
		arduinoRx.tell(new Init());
		//gsonRouter = this.getContext().actorOf(new Props(ArduinoWorker.class), "gsonRouter");
		//initAmqConnection();
	}

	private void initAmqConnection() {
		log.info("Start AMQ connection");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"failover:tcp://localhost:61616");
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(transacted, ackMode);
			Destination eaoInQueue = session.createQueue(queueName);

			MessageConsumer responseConsumer = session.createConsumer(eaoInQueue);

			// This class will handle the messages to the temp queue as well
			responseConsumer.setMessageListener(this);
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		System.out.println("Got:" + arg0.getClass());
		if(arg0 instanceof Init) {
		}
	}

	@Override
	public void onMessage(Message arg0) {
		// Deserialize
		//gsonRouter.tell(new Deserialize());
	}

}
