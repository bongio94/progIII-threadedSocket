package client;

import common.Email;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ClientModel {
	Socket socketOfClient;
	BufferedWriter os;
	BufferedReader is;

	ObjectInputStream ois;
	private ArrayList<Email> emails;

	final String serverHost = "localhost";
	private ClientHelper clientHelper;

	public ClientModel() {
		emails = new ArrayList<>();
	}



	public ArrayList<Email> runClient(String address) {

		System.out.print("Waiting for server connection...\n");

		ClientMailboxController clientMailboxController = new ClientMailboxController();

		try {
			socketOfClient = new Socket(serverHost, 7777);

			os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			ois = new ObjectInputStream((socketOfClient.getInputStream()));

			clientHelper = new ClientHelper(ois, socketOfClient);

		} catch (UnknownHostException e) {
			System.err.println("Host error: " + serverHost);

		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}

		boolean done = false;

		try {
			while (!done) {

				os.write(address + "\n");
				os.flush();

				System.out.println(address + "##\n");
				clientMailboxController.setAddress(address);

				String line;
				line = is.readLine();
				System.out.println(line);

				/*
				emails = (ArrayList<Email>) ois.readObject();
				//System.out.println(mailList.toString() + "\n");
				System.out.println("########");
				System.out.println("########");
				System.out.println(emails.get(0).getBody());


				is.close();
				os.close();
				ois.close();
				done = true;*/
			}
		} catch (UnknownHostException e) {
			System.err.println("Unknown host");
		} catch (IOException e) {
			System.err.println("IO Exception");
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/ finally {
			try {
				socketOfClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return emails;
	}



	public ArrayList<Email> retrieveObject() {
		ObjectInputStream in = null;
		final String serverHost = "localhost";
		Socket socketOfClient = null;
		ArrayList<Email> mailboxEmails = new ArrayList<>();

		while(true) {
			try {
				socketOfClient = new Socket(serverHost, 7776);
				System.out.println("CLIENT Socket opened..2");

				in = new ObjectInputStream(socketOfClient.getInputStream());
				mailboxEmails = new ArrayList<>();

				mailboxEmails = (ArrayList<Email>) in.readObject();
				String response = mailboxEmails.toString();
				System.out.println("Server response: " + response);

				in.close();
				socketOfClient.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return mailboxEmails;
		}
	}

}
