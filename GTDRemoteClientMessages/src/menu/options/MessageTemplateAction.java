package menu.options;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import util.Jndi;
import alb.util.menu.Action;

public abstract class MessageTemplateAction implements Action {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String GTD_REQUEST_QUEUE = "jms/queue/GTDQueue";

	private Connection con;
	protected Session session;
	private MessageProducer sender;
	
	private MessageConsumer replier;
	private Destination replyQueue;

	@Override
	public void execute() {
		try {
			initialize();
			MapMessage msg = createMessage();
			send(msg);
			receiveSync();
		} catch (JMSException je) {
			throw new RuntimeException();
		} finally {
			close();
		}
	}

	

	private void send(MapMessage msg) throws JMSException {
		msg.setJMSReplyTo(replyQueue);
		msg.setJMSCorrelationID(createRandomString());
		sender.send(msg);
		System.out.println("SENDDDD!");
	}



	private void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination mainQueue = (Destination) Jndi.find(GTD_REQUEST_QUEUE);
		
		con = factory.createConnection("sdi","password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		replyQueue = session.createTemporaryQueue();
		sender = session.createProducer(mainQueue);
		replier = session.createConsumer(replyQueue);

		con.start();

	}

	//Quizas generalizar mas y requerir solo un mapa, da igual el tipo.
	public abstract MapMessage createMessage() throws JMSException;

	private void close() {
		if (sender != null) {
			try {
				sender.close();
			} catch (Exception e) {

			}
		}
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {

			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {

			}
		}

	}
	
	

	public void receiveSync() throws JMSException{
		Message msg = replier.receive();
		if(validMessageType(msg)){
			process(msg);
		}
		else{
			System.out.print("Message Type Error");
		}
	}

	protected boolean validMessageType(Message msg){
		return msg instanceof TextMessage;
	}

	protected void process(Message msg) throws JMSException{
		TextMessage tmsg = (TextMessage) msg;
		System.out.println(tmsg.getText());
	}
	
	 private String createRandomString() {
	        Random random = new Random(System.currentTimeMillis());
	        long randomLong = random.nextLong();
	        return Long.toHexString(randomLong);
	    }
}
