package epat2.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class SenderImages {
    public static  ApplicationContext context = null;
   public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
		MessageSender mp = (MessageSender) context.getBean("messageSender");
	//	mp.sendImageMessage();


		//mp.sendMessage(new MessageObject("9234", "Test Message"));
		//mp.sendURLMessage(); 
		//mp.sendInputStreamMessage();
		//mp.sendByteMessage();
        String imageSourcePath = "C:\\epat2\\TempImageFiles";
       File folder = new File(imageSourcePath);
       File[] listOfFiles = folder.listFiles();


       for(File file : listOfFiles){
           sendMessage(file);
       }
	}
    public static boolean sendMessage(File file)
    {
        try{
            if(context==null)
                context = new ClassPathXmlApplicationContext("spring-beans.xml");
            MessageSender mp = (MessageSender) context.getBean("messageSender");
            mp.sendImageMessage(file);

        }catch (Exception e){
            return false;
        }
        return true;
    }

}