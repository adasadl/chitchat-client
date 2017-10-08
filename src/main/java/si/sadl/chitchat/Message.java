package si.sadl.chitchat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	boolean global;
	String recipient;
	String sender;
	String text;
	Date sent_at;

	public Message() {
	};

	public Message(String sender, String text) {
		this.global = true;
		this.sender = sender;
		this.text = text;
	}

	public Message(String sender, String recipient, String text) {
		this.global = false;
		this.sender = sender;
		this.recipient = recipient;
		this.text = text;
	}

	@Override
	public String toString() {
		return (text + "\n" + sent_at);
	}

	@JsonProperty("global")
	public boolean isGlobal() {
		return global;
	}

	@JsonProperty("recipient")
	public String getRecipient() {
		return recipient;
	}

	@JsonProperty("sender")
	public String getSender() {
		return sender;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("sent_at")
	public Date getSent_at() {
		return sent_at;
	}
}
