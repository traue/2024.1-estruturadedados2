package ed.hanoi;

import java.util.Random;
import java.util.Scanner;

import ed.Pilha;

public class Jogo {

	static Pilha torre1, torre2, torre3;
	static int tamamnho, qtd;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("JOGO BASEADO NA TORRE DE HANOI");

		while (true) {
			System.out.println("");
			System.out.print("Tamanho das pilhas [min 5; max 100]: ");
			tamamnho = sc.nextInt();
			if (tamamnho < 5 || tamamnho > 100) {
				System.err.println("ERRO: tamanho invalido.");
				continue;
			}
			System.out.print(
					"Informe a quantidade de valores iniciais das pilhas 1 e 2 (min 3 ; max " + tamamnho + "): ");
			qtd = sc.nextInt();
			if (qtd < 3 || qtd > tamamnho) {
				System.err.println("A quantidade de numeros não pode ser menor que 3 ou maior que o tamanho da pilha!");
				continue;
			}
			break;
		}

		torre1 = new Pilha(tamamnho);
		torre2 = new Pilha(tamamnho);
		torre3 = new Pilha(tamamnho);

		Random seed = new Random();
		for (int i = 0; i < qtd; i++) {
			torre1.push(seed.nextInt(5 * qtd));
			torre2.push(seed.nextInt(5 * qtd));
		}

		while (true) {

			System.out.println("\n\n*****************");

			mostrarPilhas();

			System.out.println("\n\n*****************");
			System.out.println("");
			System.out.println(" [0] - Encerrar");
			System.out.println(" [1] - Mover");
			System.out.println("");
			System.out.print(">> Opcao: ");
			int opc = sc.nextInt();

			if (opc == 0) {
				System.out.print("Encerrar? [1]Sim  |  [2]Não: ");
				if (sc.nextInt() == 1) {
					break;
				}
			} else if (opc == 1) {

				System.out.print("Pop na pilha... ");
				int pop = sc.nextInt();
				System.out.print("Push na pilha... ");
				int push = sc.nextInt();

				if (pop < 1 || pop > 3 || push < 1 || push > 3) {
					System.err.println("Pilha inválida!");
					continue;
				}
				if (pop == push) {
					System.err.println("Não pode ser a mesma pilha!");
					continue;
				}

				Pilha pilhaPop = null;
				Pilha pilhaPush = null;

				switch (pop) {
				case 1:
					pilhaPop = torre1;
					break;
				case 2:
					pilhaPop = torre2;
					break;
				case 3:
					pilhaPop = torre3;
					break;
				}

				switch (push) {
				case 1:
					pilhaPush = torre1;
					break;
				case 2:
					pilhaPush = torre2;
					break;
				case 3:
					pilhaPush = torre3;
					break;
				}

				if (pilhaPop == null || pilhaPush == null) {
					System.err.println("Pelo menos uma das pilhas nao foi inicializada.");
					break;
				}

				if (pilhaPop.isEmpty()) {
					System.err.printf("Não pode fazer o pop nesta pilha (%d) Pilha vazia\n", pop);
					continue;
				}
				if (pilhaPush.isFull()) {
					System.err.printf("Não pode fazer o pop nesta pilha (%d) Pilha cheia\n", push);
					continue;
				}

				pilhaPop.pop();
				pilhaPush.push(pilhaPop.getRetorno());

				if (condicaoVitoria()) {
					System.out.println("\nJogo finalizado!\n");
					break;
				}
			}
		}
		sc.close();
	}

	private static String formataNumero(int valor) {
		String nrS = String.valueOf(valor);
		while (nrS.length() < 4) {
			nrS = " " + nrS;
		}
		return nrS;
	}

	private static void mostrarPilhas() {

		System.out.print("\nPILHA 1: ");
		for (int i = 0; i < torre1.getQtdEleentos(); i++) {
			System.out.print(formataNumero(torre1.read(i)) + " ");
		}

		System.out.print(" | (" + (tamamnho - torre1.getQtdEleentos()) + " livres)");
		System.out.print("\n\nPILHA 2: ");
		for (int i = 0; i < torre2.getQtdEleentos(); i++) {
			System.out.print(formataNumero(torre2.read(i)) + " ");
		}

		System.out.print(" | (" + (tamamnho - torre2.getQtdEleentos()) + " lives)");
		System.out.print("\n\nPILHA 3: ");
		for (int i = 0; i < torre3.getQtdEleentos(); i++) {
			System.out.print(formataNumero(torre3.read(i)) + " ");
		}
		System.out.print(" | (" + (tamamnho - torre3.getQtdEleentos()) + " livres)");

	}

	private static boolean condicaoVitoria() {

		if (torre1.getQtdEleentos() != qtd || torre2.getQtdEleentos() != qtd || torre3.getQtdEleentos() > 0) {
			return false;
		}
		for (int i = 0; i < torre1.getQtdEleentos() - 1; i++) {
			if (torre1.read(i) > torre1.read(i + 1)) {
				return false;
			}
		}
		for (int i = torre2.getQtdEleentos() - 1; i > 0; i--) {
			if (torre2.read(i) > torre2.read(i - 1)) {
				return false;
			}
		}
		return true;
	}
}
