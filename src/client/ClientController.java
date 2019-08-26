package client;

import common.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ClientController {

	private ClientModel clientModel;
	private String address;
	private ArrayList<Email> emails;



	@FXML //fx:id = "emailComboBox"
	private ComboBox<String> emailComboBox;

	@FXML //fx:id = "infoLabel"
	private Label infoLabel;

	ObservableList<String> comboBoxEmailList = FXCollections.observableArrayList("user@gmail.com",
			"user2@gmail.com", "user3@gmail.com");

	public ClientController() {
		clientModel = new ClientModel();
		emails = new ArrayList<>();
	}


	@FXML
	public void initialize() {
		emailComboBox.setItems(comboBoxEmailList);
		address = emailComboBox.getValue();


	}


	public void onChangedComboBox(ActionEvent event) {
		infoLabel.setText(emailComboBox.getValue() + " mailbox selected");

	}

	public void onRunButtonPressed(ActionEvent event) throws IOException {
		address = emailComboBox.getValue();

		new Thread(()->{
			emails = clientModel.runClient(address);
		}).start();


		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			ClientMailboxController mailboxController = new ClientMailboxController();
			fxmlLoader.setLocation(getClass().getResource("/resources/mailbox_view.fxml"));
			fxmlLoader.setController(mailboxController);

			emails = clientModel.retrieveObject();
			mailboxController.setEmails(emails);
			mailboxController.setAddress(address);

			Scene scene = new Scene(fxmlLoader.load());

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}



		/*
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/resources/client_view.fxml"));

			System.out.println("### FXML Loader ### : " + address);// This did the "trick"
			//cc.getUserName(client); // Passing the client-object to the ClientViewController


			Scene scene = new Scene(fxmlLoader.load());


			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	public String sendClientModel() {
		System.out.println("### sendClientModel ### : " + address);
		return address;
	}
}
