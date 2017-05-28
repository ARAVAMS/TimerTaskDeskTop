package epat2.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class SenderTextFiles {
    public static  ApplicationContext context = null;
   public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
		MessageSender mp = (MessageSender) context.getBean("messageSender");
	//	mp.sendImageMessage();


		//mp.sendMessage(new MessageObject("9234", "Test Message"));
		//mp.sendURLMessage(); 
		//mp.sendInputStreamMessage();
		//mp.sendByteMessage();
        String imageSourcePath = "C:\\epat2\\TempTextFiles";
       File folder = new File(imageSourcePath);
       File[] listOfFiles = folder.listFiles();


       for(File file : listOfFiles){
           sendTextFileMessage(file);
       }
	}

    public static boolean sendTextFileMessage(File file)
    {

        try{
            if(context==null)
                context = new ClassPathXmlApplicationContext("spring-beans.xml");
            TextFileMessageSender mp = (TextFileMessageSender) context.getBean("textFileMessageSender");
            mp.sendTextFileMessage(file);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}