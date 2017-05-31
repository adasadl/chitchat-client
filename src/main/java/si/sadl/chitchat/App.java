package si.sadl.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello ChitChat!
 */
public class App {
    public static void main(String[] args) {
    	seznam_uporabnikov();
    	prijava();
    	 	
    }
    
    public static void seznam_uporabnikov(){
    
    	try {
            String hello = Request.Get("http://chitchat.andrej.com/users")
                                  .execute()
                                  .returnContent().asString();
            System.out.println(hello);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void prijava(){
    	
    	
    	URI uri;
    	String responseBody = "";
		try {
			uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", "ada").build();
			responseBody = Request.Post(uri).execute().returnContent().asString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(responseBody);
    	
    	
    	
    	
    	
    }
    
    
    
    
}


