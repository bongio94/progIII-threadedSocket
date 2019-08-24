package common;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Email implements Serializable {

	private int id;
	private String sender;
	private String recipient;
	private ArrayList<String> cc;
	private String subject;
	private String body;
	private String incomingDate;
	private boolean read;
	private boolean deleted;

	public Email() {}

	public Email(String sender, String recipient) {
		this.sender = sender;
		this.recipient = recipient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public ArrayList<String> getCc() {
		return cc;
	}

	public void setCc(ArrayList<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getIncomingDate() {
		Date date = null;

		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(this.incomingDate);
		}
		catch (ParseException e){
			System.out.println(e);
		}

		return date;
	}

	public void setIncomingDate(Date date) {
		this.incomingDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public boolean isRead() {
		return read;
	}

	public void setRead() {
		this.read = true;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void delete() {
		this.deleted = true;
	}
	/*

	public String getJsonFromEmail() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

	public EmailOLD getEmailFromJson(String jsonString) {
		Gson gson = new GsonBuilder().create();

		return gson.fromJson(jsonString, EmailOLD.class);
	}

	@Override
	public String toString() {
		return this.getJsonFromEmail();
	}*/

	@Override
	public String toString() {
		return "Email{" +
				"id=" + id +
				", sender='" + sender + '\'' +
				", recipient='" + recipient + '\'' +
				", cc=" + cc +
				", subject='" + subject + '\'' +
				", body='" + body + '\'' +
				", incomingDate='" + incomingDate + '\'' +
				", read=" + read +
				", deleted=" + deleted +
				'}';
	}
}
