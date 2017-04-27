package menu.options;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import util.Jndi;
import alb.util.menu.Action;

public abstract class MessageTemplateAction implements Action {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String GTD_QUEUE = "jms/queue/GTDQueue";

	public Connection con;
	public Session session;
	public MessageProducer sender;

	@Override
	public void execute() {
		try {
			initialize();
			MapMessage msg = createMessage();
			sender.send(msg);
		} catch (JMSException je) {
			throw new RuntimeException();
		} finally {
			close();
		}
	}

	private void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(GTD_QUEUE);

		con = factory.createConnection("sdi","password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);

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

}
