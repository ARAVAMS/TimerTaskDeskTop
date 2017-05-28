package epat2.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.imageio.ImageIO;
import javax.jms.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MessageSender {

	private JmsTemplate jmsTemplate;
	private Destination destination;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void sendImageMessage(final File file)  {
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				BytesMessage message = session.createBytesMessage();
				byte[] imageInByte;
				BufferedImage originalImage;
				try {

					originalImage = ImageIO.read(file);
				// convert BufferedImage to byte array
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(originalImage, "png", baos);
				baos.flush();
				imageInByte = baos.toByteArray();
				baos.close();
				message.writeBytes(imageInByte);
                message.setJMSCorrelationID(file.getName());
                    System.out.println("--sendImageMessage------"+file.getName());
                } catch (IOException e) {
					e.printStackTrace();
				}
				return message;
			}
		});

	}




	
	/*public void sendMessage(final MessageObject messageObj) {
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("mailId", messageObj.getMailId());
				message.setString("message", messageObj.getMessage());
				return message;
			}
		});
	}
	
	public void sendTextMessage() {
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ActiveMQSession activeMQSession = ((ActiveMQSession) session);
				//BlobMessage message = activeMQSession.createBlobMessage(inputStream);
				Queue queue = new ActiveMQQueue("MyQ");
		        QueueSender queueSender = activeMQSession.createSender(queue);
		        BlobMessage message = activeMQSession.createBlobMessage(new File("C://Documents and Settings//Administrator//Desktop//sendActiveMQ.txt"));
		        queueSender.send(message);
		        return message;
			}
		});
	}
	
	public void sendURLMessage() {
		jmsTemplate.send(destination, new MessageCreator() {
			BlobMessage message;
			public Message createMessage(Session session) throws JMSException {
				ActiveMQSession activeMQSession = ((ActiveMQSession) session);
				try {
					message = activeMQSession.createBlobMessage(new URL("http://some.shared.site.com"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return message;
				
			}
		});
	}
		
		public void sendInputStreamMessage() {
			jmsTemplate.send(destination, new MessageCreator() {
				BlobMessage message;
				public Message createMessage(Session session) throws JMSException {
					ActiveMQSession activeMQSession = ((ActiveMQSession) session);
					File file = new File("C:/Documents and Settings/Administrator/Desktop/activemq/acd.jpg");
					InputStream inputStream;
					try {
						inputStream = new FileInputStream(file);
						message = activeMQSession.createBlobMessage(inputStream);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return message;
				}
			});
	}
		
		public void sendByteMessage() {
			jmsTemplate.send(destination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					BytesMessage message = session.createBytesMessage();
					 message.writeInt(2);
					 return message;
				}
			});
		}*/
	
}

