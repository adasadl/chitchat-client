package si.sadl.chitchat;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

//import javax.swing.text.BadLocationException;

public class PrimeRobot extends TimerTask {
	private ChatFrame chat;
	
	public PrimeRobot(ChatFrame chat) {
		this.chat = chat;
	}


	/**
	 * Activate the robot!
	 */
	public void activate() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 5000, 1000);
	}
	
	@Override
	public void run() {
		if (chat.prijavljen) {
			try {
				chat.receiveMessage();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
		
	}
}
