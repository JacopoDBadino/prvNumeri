package it.polito.tdp.prvNumeri;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.prvNumeri.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

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
		spazioFinale.appendText(
				"Benvenuto! Lo scopo del gioco e' indovinare un numero casuale compreso da 1 e 100, buona fortuna!\nTentativi rimasti: "
						+ model.getTentRimasti());
		model.nuovaPartita();
		tastoProva.setDisable(false);
		spazioTentativo.setEditable(true);
	}

	@FXML
	void voglioProvare(ActionEvent event) {

		int tentativo;
		int esito = -1;

		try {
			tentativo = Integer.parseInt(spazioTentativo.getText());
		} catch (NumberFormatException nfe) {
			spazioFinale.appendText("\nInserire un numero!");
			return;
		}

		try {
			esito = this.model.tentativo(tentativo);
		} catch (InvalidParameterException pe) {
			spazioFinale.appendText(pe.getMessage());
			return;
		}

		if (esito == 0) {
			spazioFinale.appendText("\nHAI VINTO! Hai impegato: " + (7 - this.model.getTentRimasti()) + " tentativi\n");
			tastoProva.setDisable(true);
			spazioTentativo.setEditable(false);
			return;
		}
		if (esito == 6) {
			spazioFinale.appendText("\nHAI PERSO!\n");
			tastoProva.setDisable(true);
			spazioTentativo.setEditable(false);
			model.setTentRimasti(7);
			return;
		}
		if (esito == 1) {
			spazioFinale.appendText("\nTroppo alto!");
			return;
		}
		if (esito == -1) {
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

	public void setModel(Model model) {
		this.model = model;
	}
}
