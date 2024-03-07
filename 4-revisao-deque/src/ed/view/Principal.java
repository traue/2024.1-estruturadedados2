package ed.view;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Principal {

	static Deque<Integer> deque;
	static Scanner sc;
	static int tamanhoMax;

	public static void main(String[] args) {

		// deque = new ArrayDeque<>(10); //ASSIM OU...
		deque = new LinkedBlockingDeque<>(10);

		sc = new Scanner(System.in);

		System.out.print("Tamanho maximo do Deque: ");
		tamanhoMax = sc.nextInt();

		if (tamanhoMax <= 0) {
			System.out.println("Não pode ser negativo!");
			return;
		}

		while (true) {
			System.out.println("[0] - sair");
			System.out.println("[1] - Inserir elemento no início");
			System.out.println("[2] - Inserir elemento ao final");
			System.out.println("[3] - Remover elemento do início");
			System.out.println("[4] - Remover elemento do final");
			System.out.println("[5] - Ler elemento de uma posição");
			System.out.println("[6] - Imprimir Deque");

			System.out.print("\n\nOpcao: ");
			int opc = sc.nextInt();

			if (opc == 0) {
				break;
			} else if (opc == 1) {
				if (estaCheio()) {
					continue;
				}
				int v = getValor();
				deque.addFirst(v);
			} else if (opc == 2) {
				if (estaCheio()) {
					continue;
				}
				int v = getValor();
				deque.addLast(v);
			} else if (opc == 3) {
				if (estaVazio()) {
					continue;
				}
				int v = deque.removeFirst();
				System.out.println("Elemento removido do inicio: " + v);
			} else if (opc == 4) {
				if (estaVazio()) {
					continue;
				}
				int v = deque.removeLast();
				System.out.println("Elemento removido do final: " + v);
			} else if (opc == 5) {

				int posDesejada = getValor();
				if (posDesejada > deque.size() || posDesejada <= 0) {
					System.out.println("Nao há nenhum elemento nessa posição (" + posDesejada + ")");
					continue;
				}
				Iterator<Integer> it = deque.iterator();
				int posAtual = 0;
				boolean loc = false;
				while (it.hasNext()) {
					posAtual++;
					if (posAtual == posDesejada) {
						System.out.println("O elemento da posicao " + posDesejada + " é: " + it.next());
						loc = true;
						break;
					}
					it.next();
				}
				if (!loc) {
					System.out.println("Elemento nao localizado.");
				}

			} else if (opc == 6) {
				Iterator<Integer> it = deque.iterator();
				while (it.hasNext()) {
					System.out.print(it.next() + ", ");
				}

			} else if (opc == 99) {
				int v = getValor();
				deque.add(v);

			}

		}

	}

	private static boolean estaVazio() {
		if (deque.isEmpty()) {
			System.out.println("O Deque está vazio!");
			return true;
		}
		return false;
	}

	private static boolean estaCheio() {
		if (deque.size() == tamanhoMax) {
			System.out.println("O Deque já está cheio!");
			return true;
		}
		return false;
	}

	private static int getValor() {
		System.out.print("Informe o valor do elemento: ");
		int v = sc.nextInt();
		return v;
	}

}
