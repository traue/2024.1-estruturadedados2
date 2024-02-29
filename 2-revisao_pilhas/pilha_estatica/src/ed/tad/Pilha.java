package ed.tad;

public class Pilha {
	private int topo;
	private int dados[];

	public Pilha(int c) {
		topo = -1;
		dados = new int[c];
	}

	public boolean estaVazia() {
		return topo == -1;
	}

	public boolean estaCheia() {
		return topo == dados.length - 1;
	}

	public void push(int elemento) { // adiciona ao topo se não está cheia
		if (estaCheia()) {
			System.err.println("Erro! A pilha está cheia!");
		} else {
			dados[++topo] = elemento;
		}
	}

	public int pop() { //remove do topo e retorno o elemento removido
		int r = -1;

		if (estaVazia()) {
			System.err.println("Erro! A pilha está vazia!");
		} else {
			r = dados[topo--];
		}

		return r;
	}

	public void imprimirPilha() {
		if (estaVazia()) {
			System.err.println("Erro! A pilha está vazia!");
		} else {
			System.out.println("Pilha:");
			for (int i = topo; i > -1; i--) {
				System.out.printf("|%d|\n", dados[i]);
			}
			System.out.print("\n");
		}
	}
}
