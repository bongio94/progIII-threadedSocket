package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private String body;
	private String date;

	public Log(String body){
		this.setBody(body);
		this.setDate(new SimpleDateFormat("yyyy/MM/dd - HH:mm")
				.format(new Date()));

	}

	public Log(){ }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return this.getBody() + " ---- " + this.getDate() + "\n";
	}

	public Log newLogMessage(String body) {
		Log newLog = new Log(body);
		return newLog;
	}

}