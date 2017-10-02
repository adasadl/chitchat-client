package si.sadl.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//public static void main(String[] args) {
	//seznam_uporabnikov();
	//prijava();
	 	
//}


public class App {
    
    public static void seznamUporabnikov(){
    
    	try {
            String uporabniki = Request.Get("http://chitchat.andrej.com/users")
                                  .execute()
                                  .returnContent().asString();
            System.out.println(uporabniki);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void prijava(String uporabnik){
    	
    	
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
    
    
    public static void odjava(String uporabnik){
        URI uri;
        String responseBody = "";
        try {
            uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", "ada").build();
            responseBody = Request.Delete(uri).execute().returnContent().asString();
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


