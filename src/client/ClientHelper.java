package client;

import common.Email;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHelper {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ClientMailboxController clientMailboxController;


	public ClientHelper() {
		clientMailboxController = new ClientMailboxController();
	}

	public ClientHelper(ObjectOutputStream oos, Socket socket) {
		this.oos = oos;
		this.socket = socket;
		clientMailboxController = new ClientMailboxController();

	}

	public ClientHelper(ObjectInputStream ois, Socket socket) {
		this.ois = ois;
		this.socket = socket;
		clientMailboxController = new ClientMailboxController();

	}


	public ArrayList<Email> getEmails() {
		ArrayList<Email> emails = new ArrayList<>();
		try {
			//this.ois = new ObjectInputStream(this.socket.getInputStream());

			boolean done = false;
			while (!done) {
				System.out.println("Reading object...");
				emails = (ArrayList<Email>) ois.readObject();
				ois.close();
				done = true;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(emails.size());
		clientMailboxController.setEmails(emails);
		return emails;
	}

}
