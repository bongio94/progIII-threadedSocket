package common;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class JsonHelper {

	private static final TypeToken<ArrayList<Email>> TYPE = new TypeToken<ArrayList<Email>>() {};

	public ArrayList<Email> getEmailsFromJson(String address) {

		ArrayList<Email> mailLists = new ArrayList<>();
		String path = "";
		Gson gson = new Gson();

		switch (address) {
			case "user@gmail.com":
				path = "C:\\Backup HDD\\backup_windows\\IdeaProjects\\" +
						"progIII-threadedSocket\\src\\common\\user1Mailbox.json";
				break;
			case "user2@gmail.com":
				path = "C:\\Backup HDD\\backup_windows\\IdeaProjects\\" +
						"progIII-threadedSocket\\src\\common\\user2Mailbox.json";
				break;
			case "user3@gmail.com":
				path = "C:\\Backup HDD\\backup_windows\\IdeaProjects\\" +
						"progIII-threadedSocket\\src\\common\\user3Mailbox.json";
				break;

			default:
				System.err.println("User not found");
				break;
		}


		try {
			JsonReader reader = new JsonReader(new FileReader(path));
			mailLists = gson.fromJson(reader, TYPE.getType());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return mailLists;
	}
}
