package it.polito.tdp.prvNumeri;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	int tentRimasti;
	int segreto;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button tastoInizio;

	@FXML
	private TextField spazioTentativo;

	@FXML
	private Button tastoProva;

	@FXML
	private TextArea spazioFinale;

	@FXML
	void voglioIniziare(ActionEvent event) {
		tentRimasti = 7;
		spazioFinale.appendText(
				"Benvenuto! Lo scopo del gioco e' indovinare un numero casuale compreso da 1 e 100, buona fortuna!\nTentativi rimasti: "
						+ tentRimasti);
		tastoProva.setDisable(false);
		spazioTentativo.setEditable(true);
		segreto = (int) (Math.random() * 100);
	}

	@FXML
	void voglioProvare(ActionEvent event) {
		int tentativo;
		try {
			tentativo = Integer.parseInt(spazioTentativo.getText());
		} catch (NumberFormatException nfe) {
			spazioFinale.appendText("\nInserire un numero!");
			return;
		}
		tentRimasti--;
		if (tentativo == segreto) {
			spazioFinale.appendText("\nHAI VINTO! Hai impegato: " + (7 - tentRimasti) + " tentativi\n");
			tastoProva.setDisable(true);
			spazioTentativo.setEditable(false);
			return;
		}
		if (tentRimasti == 0) {
			spazioFinale.appendText("\nHAI PERSO!\n");
			tastoProva.setDisable(true);
			spazioTentativo.setEditable(false);
			return;
		}
		if (tentativo > segreto) {
			spazioFinale.appendText("\nTroppo alto!");
			return;
		}
		if (tentativo < segreto) {
			spazioFinale.appendText("\nTroppo basso!");
			return;
		}

	}
	
	// modifica prova

	@FXML
	void initialize() {
		assert tastoInizio != null : "fx:id=\"tastoInizio\" was not injected: check your FXML file 'Scene.fxml'.";
		assert spazioTentativo != null : "fx:id=\"spazioTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert tastoProva != null : "fx:id=\"tastoProva\" was not injected: check your FXML file 'Scene.fxml'.";
		assert spazioFinale != null : "fx:id=\"spazioFinale\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
