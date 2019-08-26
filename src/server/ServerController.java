package server;

import common.Log;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

	ServerModel serverModel;
	private ObservableList<Log> logs;

	@FXML
	private Button clearLogBtn;

	@FXML
	private Button serverStartBtn;

	@FXML
	private  TextArea logTxtArea;

	public ServerController() throws IOException {
		serverModel = new ServerModel();
		logs = serverModel.getLogs();
		logs.addListener(new ListChangeListener<Log>() {
			@Override
			public void onChanged(Change<? extends Log> c) {
				logTxtArea.setText(logs.toString());
			}
		});
	}



	@FXML
	public void handleClearLogBtn(ActionEvent event) throws RemoteException {
		clearLogBtn.setText("Cleared");
		logTxtArea.setText("Cleared");
	}


	@FXML
	public void handleServerStartBtn(ActionEvent event) throws IOException{
		new Thread(()->{
			serverModel.initServer();
		}).start();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logTxtArea.setText("Press Start to initialize server.");
	}
}
