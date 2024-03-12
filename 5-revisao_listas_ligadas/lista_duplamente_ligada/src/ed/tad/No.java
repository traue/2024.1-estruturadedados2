package ed.tad;

public class No {

	private String nome;
	private int valor;
	private No proximo;
	private No anterior;

	public No(String nome, int numero) {
		this.nome = nome;
		this.valor = numero;
		this.proximo = null;
		this.anterior = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setNumero(int numero) {
		this.valor = numero;
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
