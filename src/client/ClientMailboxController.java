package client;

import common.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class ClientMailboxController {

	ObservableList<Email> emails = null;
	private String address;
	private ArrayList<Email> emailArrayList = new ArrayList<>();
	private ClientModel clientModel;
	private ClientHelper clientHelper;

	@FXML
	private Label topLabel;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private ListView<Email> emailListView;

	@FXML
	private TextField destField;

	@FXML
	private TextField objField;

	@FXML
	private TextField ccField;

	@FXML
	private TextField txtField;

	@FXML
	private MenuButton menuButton;

	@FXML
	private MenuItem menuItemSend;

	@FXML
	private MenuItem menuItemRec;

	@FXML
	void handleSendButton(ActionEvent event) {

	}

	@FXML
	public void initialize() {
		ObservableList<Email> received;
		ObservableList<Email> sended;
		ArrayList<Email> rec = new ArrayList<>();
		ArrayList<Email> send = new ArrayList<>();
		System.out.println(emailArrayList.size() + " clienthelper size");
		this.address = getAddress();
		System.out.println(address);


		for (Email email : emailArrayList) {
			if (email.getSender().equals(address)) {
				send.add(email);
			} else {
				rec.add(email);
			}
		}

		//emails = FXCollections.observableArrayList(emailArrayList);
		received = FXCollections.observableArrayList(rec);
		sended = FXCollections.observableArrayList(send);
		emailListView.setItems(received);

		menuItemRec.setOnAction(actionEvent -> {
			emailListView.setItems(received);
		});

		menuItemSend.setOnAction(actionEvent -> {
			emailListView.setItems(sended);
		});


		System.out.println(emailArrayList.size() + " size in initialize\n");

	}

	public ClientMailboxController() {
		//mails = new ArrayList<>();
		clientModel = new ClientModel();
		this.address = getAddress();
		/*this.mails = getMails();*/
		System.out.println(emailArrayList.size() + " size in constructor" +
				" of clientmailboxcontroller\n");

	}


	public void setEmails(ArrayList<Email> mails) {
		emailArrayList = mails;
		System.out.println(emailArrayList.size() + " size in setEmails() of client mailbox controller\n");


	}

	public ArrayList<Email> getMails() {
		return emailArrayList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress (String address) {
		this.address = address;
		System.out.println(address);
	}

	public void prova(ArrayList<Email> received) {

		/*for (Email mail : mails) {
			if (!(mail.getSender().equals(address)))
				received.add(mail);
		}*/
		System.out.println("received " + received.size());


		//emails = FXCollections.observableArrayList(mails);
		System.out.println("emails " + emailArrayList.size());
		//listView = new ListView<Email>();
		//emailListView.setItems(emails);
	}
}
