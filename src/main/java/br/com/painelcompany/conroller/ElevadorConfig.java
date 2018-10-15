package br.com.painelcompany.conroller;

public class ElevadorConfig {
	private final int andarMin;
	private final int andarMax;
	private final String descricao;

	public ElevadorConfig(int andarMin, int andarMax, String descricao) {
		this.andarMin = andarMin;
		this.andarMax = andarMax;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getAndarMin() {
		return andarMin;
	}

	public int getAndarMax() {
		return andarMax;
	}
}