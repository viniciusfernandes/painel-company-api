package br.com.painelcompany.conroller;

import br.com.elevadorcompany.Elevador;
import br.com.elevadorcompany.ItemEmbarcavel;
import br.com.elevadorcompany.Mensagem;

public class ElevadorTeste implements Elevador {
	private int andarAtual;
	private final int andarMin;
	private final int andarMax;
	private final int capacidade;
	private final int lotacao;
	private String mensagem;
	private final ItemEmbarcavel[] itens;

	public ElevadorTeste(int andarMin, int andarMax, int capacidade, int lotacao) {
		this.andarMin = andarMin;
		this.andarMax = andarMax;
		this.capacidade = capacidade;
		this.lotacao = lotacao;
		this.itens = new ItemEmbarcavel[lotacao];
	}

	@Override
	public boolean movimentar(int andar) {
		if (andar >= andarMin && andar <= andarMax && andar != andarAtual) {
			mensagem = andar > andarAtual ? "Subindo " : "Descendo ";
			mensagem += "do andar " + andarAtual + " para o andar " + andar;
			andarAtual = andar;
			return true;
		}
		mensagem = "Nao eh possivel sair do andar " + andarAtual + " para o andar " + andar;
		return false;
	}

	@Override
	public int capacidade() {
		return capacidade;
	}

	@Override
	public int lotacao() {
		return lotacao;
	}

	@Override
	public boolean embarcar(ItemEmbarcavel item) {
		if (isLotado()) {
			mensagem = "Nao foi possivel o embarque por excesso de lotacao.";
			return false;
		}

		if (item.getPeso() + carga() <= capacidade) {
			for (int i = 0; i < itens.length; i++) {
				if (itens[i] == null) {
					itens[i] = item;
					return true;
				}
			}
		}
		mensagem = "Nao foi possivel o embarque por excesso de carga.";
		return false;
	}

	@Override
	public boolean desembarcar(int indiceItem) {
		if (indiceItem < 0 || indiceItem >= lotacao || itens[indiceItem] == null) {
			mensagem = "O item escolhido (" + indiceItem + ") nao existe para o desembarque.";
			return false;
		}
		itens[indiceItem] = null;
		mensagem = "Desembarcou o item (" + indiceItem + ")";
		return true;
	}

	@Override
	public Mensagem mensagem() {
		return new Mensagem(mensagem).append(" Carga: ").append(String.valueOf(carga())).append(" kg.");
	}

	@Override
	public int carga() {
		int tot = 0;
		for (ItemEmbarcavel item : itens) {
			if (item == null) {
				continue;
			}
			tot += item.getPeso();
		}
		return tot;
	}

	@Override
	public String descricaoItens() {
		StringBuilder indices = new StringBuilder();
		for (int i = 0; i < itens.length; i++) {
			if (itens[i] == null) {
				continue;
			}
			indices.append("(").append(i).append(") peso ").append(itens[i].getPeso()).append(" kg\n");
		}
		return indices.toString();
	}

	@Override
	public String descricaoTecnica() {
		return "Capacidade " + capacidade + " lotacao " + lotacao + ". Atende de " + andarMin + " ate " + andarMax;
	}

	private boolean isLotado() {
		for (ItemEmbarcavel item : itens) {
			if (item == null) {
				return false;
			}
		}
		return true;
	}
}