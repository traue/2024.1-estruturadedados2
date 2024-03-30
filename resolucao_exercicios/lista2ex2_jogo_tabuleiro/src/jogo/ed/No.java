package jogo.ed;

public class No {

	private int id;
	private int jogador; // marcador ou dono da casa
	private int posicao; // posição da casa no tabuleiro
	private STATUSCASA status; // livre, marcada ou proprietária
	private int penalidade; // número de vezes que fica sem jogar
	private No proximo;
	private No anterior;

	public No() {
		this.id = 0;
		this.jogador = 0;
		this.posicao = 0;
		this.status = STATUSCASA.LIVRE;
		penalidade = 0;
		this.proximo = this; // casa inicial é instanciada apontando para ela mesma
		this.anterior = this;
	}

	public int getId() {
		return id;
	}

	public int getJogador() {
		return jogador;
	}

	public void setJogador(int jogador) {
		this.jogador = jogador;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	// === game
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public STATUSCASA getStatus() {
		return status;
	}

	public void setStatus(STATUSCASA status) {
		this.status = status;
	}

	public int getPenalidade() {
		return penalidade;
	}

	public No getProximo() {
		return proximo;
	}

	public void setProximo(No proximo) {
		this.proximo = proximo;
	}

	public No getAnterior() {
		return anterior;
	}

	public void setAnterior(No anterior) {
		this.anterior = anterior;
	}

}
