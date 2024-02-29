package ed.tad;

public class Pilha {

	private No inicio;

	public Pilha() {
		inicio = null;
	}

	public boolean estaVazia() {
		return inicio == null;
	}

	public void push(int elemento) {
		No novo = new No(elemento);
		novo.prox = inicio;
		inicio = novo;
	}

	public int pop() {
		int r = -1;

		if (estaVazia()) {
			System.err.println("Erro! A pilha está vazia.");
		} else {
			r = inicio.dado;
			inicio = inicio.prox;
		}

		return r;
	}

	public void imprimirPilha() {
		if (estaVazia()) {
			System.err.println("Erro! A lista está vazia.");
		} else {
			No aux = inicio;
			System.out.println("Pilha: ");
			while (aux != null) {
				System.out.printf("|%d|\n", aux.dado);
				aux = aux.prox;
			}
			System.out.println();
		}
	}
}
