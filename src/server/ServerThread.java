package server;

import common.Email;
import common.JsonHelper;
import common.Log;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {

	private Socket incoming;
	private int clientCounter;
	private ObservableList<Log> logs;
	//private ServerController serverController;

	ServerThread(Socket socket, int count, ObservableList<Log> logs) {
		incoming = socket;
		clientCounter = count;
		this.logs = logs;
	}

	@Override
	public void run() {

		ArrayList<Email> mailList = new ArrayList<>();
		JsonHelper jsonHelper = new JsonHelper();


		try {
			ServerModel serverModel = new ServerModel();

		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw;
		BufferedReader br;
		ObjectOutputStream oos;



		try {
			try {
				//serverController = new ServerController();
				br = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
				oos = new ObjectOutputStream(incoming.getOutputStream());

				String line = br.readLine();
				System.out.print("Message recieved from client: " + line + "\n");
				System.out.print("client no: " + clientCounter + "\n");

				System.out.print("Welcome: " + line + "\n");

				logs.add(new Log(line + "Connected"));

				bw.write(line + " connected\n");
				System.out.print("line written to client\n");
				bw.flush();

				System.out.println("Loading " + line + " emails\n");
				mailList = jsonHelper.getEmailsFromJson(line);
				passSelectedMailBox(mailList);
				System.out.println("Mail list sent.\n");



				oos.flush();
				bw.flush();
				bw.close();
				br.close();
				//serverController.appendToLog(line + " Connected");


			} finally {

				incoming.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void passSelectedMailBox(ArrayList<Email> mailList) throws IOException {
		int port = 7776;
		String host = "localhost";
		ServerSocket serverSocket = new ServerSocket(port);
		Socket clientSocket = serverSocket.accept();
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());


		System.out.println("Loading complete\n");
		System.out.println("Sending to client...\n");
		oos.writeObject(mailList);
		oos.close();
		oos.flush();
		clientSocket.close();
	}
}
