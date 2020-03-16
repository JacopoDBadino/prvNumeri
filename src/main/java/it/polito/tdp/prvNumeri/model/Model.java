package it.polito.tdp.prvNumeri.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;

public class Model {

	int tentRimasti;
	int segreto;
	LinkedList<String> numeriInseriti;

	public Model() {
		this.tentRimasti = 7;
		numeriInseriti = new LinkedList<String>();
	}

	public void nuovaPartita() {
		tentRimasti = 7;
		segreto = (int) (Math.random() * 100);
		numeriInseriti.removeAll(numeriInseriti);
	}

	public int tentativo(int tentativo) {

		if (tentativoValido(tentativo) == false)
			throw new InvalidParameterException("\nInserire numero tra 1 e 100");
		if (numeriInseriti.contains(String.valueOf(tentativo)))
			throw new InvalidParameterException("\nNumero gia' inserito!");

		tentRimasti--;

		if (tentativo == segreto) {
			return 0;
		}
		if (tentRimasti == 0) {

			return 6;
		}
		if (tentativo > segreto) {
			numeriInseriti.add(String.valueOf(tentativo));
			return 1;
		}
		if (tentativo < segreto) {
			numeriInseriti.add(String.valueOf(tentativo));
			return -1;
		}
		
		return -100;
	}

	private boolean tentativoValido(int tentativo) {
		boolean esito = true;
		if (tentativo < 1 || tentativo > 100)
			esito = false;
		return esito;
	}

	public int getTentRimasti() {
		return tentRimasti;
	}

	public void setTentRimasti(int tentRimasti) {
		this.tentRimasti = tentRimasti;
	}

}
