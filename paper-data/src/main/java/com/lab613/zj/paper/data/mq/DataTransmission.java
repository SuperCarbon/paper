package com.lab613.zj.paper.data.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lab613.zj.paper.classification.Classification;

public class DataTransmission implements MessageListener, ExceptionListener {

	private String url = "tcp://127.0.0.1:61616";

	private String subject = "text-identify";

	private Destination destination = null;

	private Connection connection = null;

	private Session session = null;

	private MessageConsumer consumer = null;

	// private ClassificationService classificationService = null;

	private Log LOG = LogFactory.getLog(DataTransmission.class);

	@Override
	public void onException(JMSException jmsException) {
		LOG.error("MQ接收出错:" + jmsException.getMessage());

	}

	// 初始化
	private void initialize() throws JMSException, Exception {
		// 连接工厂是用户创建连接的对象，这里使用的是ActiveMQ的ActiveMQConnectionFactory根据url，username和password创建连接工厂。
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				url);
		// 连接工厂创建一个jms connection
		connection = connectionFactory.createConnection();
		// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 不支持事务
		// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象，两种消息传递方式：点对点和发布/订阅
		destination = session.createQueue(subject);
		// 会话创建消息的生产者将消息发送到目的地
		consumer = session.createConsumer(destination);

	}

	// 消费消息
	public void consumeMessage() throws JMSException, Exception {
		initialize();
		connection.start();

		System.out.println("Consumer:->Begin listening...");
		// 开始监听
		consumer.setMessageListener(this);
		// Message message = consumer.receive();
	}

	// 关闭连接
	public void close() throws JMSException {
		System.out.println("Consumer:->Closing connection");
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof MapMessage) {
				MapMessage txtMsg = (MapMessage) message;

				String sourceCode = txtMsg.getString("sourceCode");
				String title = txtMsg.getString("title");
				String ip = txtMsg.getString("ip");
				String url = txtMsg.getString("url");
				String description = txtMsg.getString("description");
				
//				Classification classification = new Classification();
//				classification.getClassificationResult(sourceCode, ip, url,
//						title, description);

			} else {
				System.out.println("Consumer Others:->Received: " + message);

			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		DataTransmission dataTransmission = new DataTransmission();
		try {
			dataTransmission.consumeMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
