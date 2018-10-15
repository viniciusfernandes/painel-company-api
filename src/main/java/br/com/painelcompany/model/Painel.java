package br.com.painelcompany.model;

public class Painel {
	private final int minAndar;
	private final int maxAndar;

	public Painel(int minAndar, int maxAndar) {
		this.minAndar = minAndar;
		this.maxAndar = maxAndar;
	}

	public int getMinAndar() {
		return minAndar;
	}

	public int getMaxAndar() {
		return maxAndar;
	}

}
