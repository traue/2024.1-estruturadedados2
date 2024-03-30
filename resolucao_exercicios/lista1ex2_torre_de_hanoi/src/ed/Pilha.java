package ed;

public class Pilha {

	private final int capacidade;
	private int topo;
	private int[] pilha;

	private int retorno;

	public Pilha(int capacidade) {
		this.capacidade = capacidade;
		this.topo = -1;
		this.pilha = new int[capacidade];
	}

	public int getRetorno() {
		return retorno;
	}

	public boolean isFull() {
		return this.topo == this.capacidade - 1;
	}

	public int getQtdEleentos() {
		return this.topo + 1;
	}

	public boolean isEmpty() {
		return this.getQtdEleentos() == 0;
	}

	public boolean push(int valor) {
		if (!this.isFull()) {
			this.topo += 1;
			this.pilha[this.topo] = valor;
			return true;
		} else {
			return false;
		}
	}

	public boolean pop() {
		if (this.isEmpty()) {
			return false;
		}
		this.retorno = this.pilha[this.topo];
		this.topo--;
		return true;
	}

	public String print() {
		String ret = "";
		if (this.isEmpty()) {
			return "A pilha está vazia";
		}
		ret += "A pilha possui " + this.getQtdEleentos() + " elementos.\n";
		for (int i = 0; i <= this.topo; i++) {
			ret += this.pilha[i] + " ";
		}
		return ret;
	}

	public int read(int indice) {
		if (indice > this.topo) {
			return -1;
		}
		return pilha[indice];
	}
}
