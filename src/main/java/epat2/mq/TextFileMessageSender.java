package epat2.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextFileMessageSender {

	private JmsTemplate textFilejmsTemplate;
	private Destination textFileDestination;

	public void setTextFilejmsTemplate(JmsTemplate jmsTemplate) {
		this.textFilejmsTemplate = jmsTemplate;
	}

	public void setTextFileDestination(Destination destination) {
		this.textFileDestination = destination;
	}

    public void sendTextFileMessage(final File file) {
        textFilejmsTemplate.send(textFileDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage streamMessage = session.createBytesMessage();


                byte[] b = new byte[(int)file.length()];
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    fileInputStream.read(b);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                streamMessage.writeBytes(b);
                streamMessage.setJMSCorrelationID(file.getName());
                
                return streamMessage;
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

