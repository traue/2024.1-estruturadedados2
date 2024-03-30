package jogo.ed;

public class Jogador {

	private int numero;
	private String nome;
	private No casa;
	private int perdeVez;

	public Jogador(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
		this.casa = null;
		this.perdeVez = 0;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public No getCasa() {
		return casa;
	}

	public void setCasa(No casa) {
		this.casa = casa;
	}

	public int getPerdeAVez() {
		return perdeVez;
	}

	public void setPerdeAVez(int perdeAVez) {
		this.perdeVez = perdeAVez;
	}

}
