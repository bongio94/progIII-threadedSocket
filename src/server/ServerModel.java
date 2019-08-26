package server;

import common.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerModel {
	private ServerSocket listener;
	private int clientNumber;
	private ObservableList<Log> logs;


	public ServerModel() throws IOException{
		logs = FXCollections.observableArrayList();
	}

	/*
	public void initServer() throws IOException {
		System.out.print("Server awaiting for connections...");
		try {
			listener = new ServerSocket(7777);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (true) {
				Socket socketOfServer = listener.accept();
				System.out.println("connected");
				System.out.print("Invoking new ServerThread class.start()");

				new ServerThread(socketOfServer, clientNumber++).start();
			}
		} finally {
			listener.close();
		}
	}*/

	public void initServer() {

		clientNumber = 0;
		System.out.print("Server awaiting for connections...\n");
		try {
			listener = new ServerSocket(7777);
			addLog("Server initialized...");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (true) {
				Socket socketOfServer = listener.accept();
				System.out.println("connected\n");

				System.out.print("Invoking new ServerThread class.start()\n");
				Runnable r = new ServerThread(socketOfServer, clientNumber++, getLogs());
				new Thread(r).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<Log> getLogs() {
		return this.logs;
	}

	public void addLog(String event) {
		logs.addAll(new Log(event));
	}

}
