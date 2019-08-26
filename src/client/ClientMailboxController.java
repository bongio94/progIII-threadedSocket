package client;

import common.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
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
	private TextArea bodyTextArea;

	@FXML
	private Text senderText;
	@FXML
	private Text subjectText;
	@FXML
	private Text ccText;
	@FXML
	private Button writeButton;



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

		received = FXCollections.observableArrayList(rec);
		sended = FXCollections.observableArrayList(send);
		emailListView.setItems(received);

		menuItemRec.setOnAction(actionEvent -> {
			emailListView.setItems(received);
			menuButton.setText("Ricevute");
		});

		menuItemSend.setOnAction(actionEvent -> {
			emailListView.setItems(sended);
			menuButton.setText("Inviate");
		});

		emailListView.setCellFactory(emailListView1 -> new EmailListViewCell());
		emailListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				senderText.setText(emailListView.getSelectionModel().getSelectedItem().getSender());
				subjectText.setText(emailListView.getSelectionModel().getSelectedItem().getSubject());
				bodyTextArea.setText(emailListView.getSelectionModel().getSelectedItem().getBody());
				ccText.setText(emailListView.getSelectionModel().getSelectedItem().getCc().toString());
				if (!emailListView.getSelectionModel().getSelectedItem().getSender().equals(address))
					emailListView.getSelectionModel().getSelectedItem().setRead();
			}
		});
		System.out.println(emailArrayList.size() + " size in initialize\n");

		writeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/resources/write_email_view.fxml"));
					Stage stage = new Stage();
					stage.setTitle("My New Stage Title");
					stage.setScene(new Scene(root, 450, 450));
					stage.show();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
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

	public Text getSenderText() {
		return senderText;
	}

	public void setSenderText(String text) {
		this.senderText.setText(text);
	}

	private void handleWriteButton() {

	}
}
