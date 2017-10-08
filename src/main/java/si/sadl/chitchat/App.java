package si.sadl.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;


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
			uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", uporabnik).build();
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
        	
            uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", uporabnik).build();
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
    
    public static List<Message> getMessages(String uporabnik) throws ClientProtocolException, IOException, URISyntaxException {
        URI uri = new URIBuilder("http://chitchat.andrej.com/messages").addParameter("username", uporabnik).build();
        String responseBody = Request.Get(uri).execute().returnContent().asString();     
        ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		TypeReference<List<Message>> t = new TypeReference<List<Message>>() { };
		List<Message> messages = mapper.readValue(responseBody, t);


	return messages;
    }
   
    public static void SendMessage(boolean global, String posiljatelj, String prejemnik, String vsebina){
		URI uri;
		ObjectMapper mapper = new ObjectMapper();
		String responseBody = null;
		try {
			uri = new URIBuilder("http://chitchat.andrej.com/messages").addParameter("username", posiljatelj).build();
			Message sporocilo = new Message();
			if (global) {
				sporocilo = new Message(posiljatelj, vsebina);
			}
			else {
				sporocilo = new Message(posiljatelj, prejemnik, vsebina);
			}
			String jsonMessage = mapper.writeValueAsString(sporocilo);
			responseBody = Request.Post(uri)
			          .bodyString(jsonMessage, ContentType.APPLICATION_JSON)
			          .execute()
			          .returnContent()
			          .asString();
		} catch (URISyntaxException e1) {
			System.out.println(e1.getMessage());
		} catch (JsonProcessingException e) {
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


