package ed.view;

import java.util.Scanner;

import ed.tad.ListaDuplamenteLigada;
import ed.tad.No;

public class Principal {

	static ListaDuplamenteLigada ll;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ll = new ListaDuplamenteLigada();

		System.out.println("LISTA DUPLAMENTE LIGADA");

		while (true) {

			System.out.println("Quantidade de elementos na lista: " + ll.getQtd()); // Ex1
			System.out.println("[0] - Encerrar");
			System.out.println("[1] - Inserir elemento no inicio");
			System.out.println("[2] - Inserir elemento no final");
			System.out.println("[3] - Extrair elemento do inicio");
			System.out.println("[4] - Extrair elemento do final");
			System.out.println("[5] - Imprimir elementos da lista (sentido inicio -> fim)");
			System.out.println("[6] - Imprimir elementos da lista (sentido fim -> inicio");
			System.out.println("[7] - Retornar elemento de uma determinada posicao (sentido inicio -> fim)");
			System.out.println("[8] - Retornar elemento de uma determinada posicao (sentido fim -> inicio)");
			System.out.println(
					"[9] - Localizar a primeira ocorrencia um elemento dado seu numero (sentido inicio -> fim)");
			System.out.println(
					"[10] - Localizar a primeira ocorrencia um elemento dado seu numero (sentido fim -> inicio)");
			System.out.println("[11] - Inserir um elemento em determinada posicao (sentido inicio -> fim)");
			System.out.println("[12] - Inserir um elemento em determinada posicao (sentido fim -> inicio)");
			System.out.println("[13] - Remover um elemento de determinada posicao (sentido inicio -> fim)");
			System.out.println("[14] - Remover um elemento de determinada posicao (sentido fim -> inicio)");

			System.out.println("[98] - Inicializa a lista com algum elementos");
			System.out.println("[99] - Apagar a lista");

			System.out.println("");

			System.out.print("Opção: ");

			int opc;
			try {
				opc = sc.nextInt();
			} catch (Exception ex) {
				System.err.println("ERRO: Opção invalida.");
				sc.next();
				continue;
			}

			if (opc == 0) {
				break;
			} else if (opc == 1) {

				No e = criaElem();
				ll.insereInicio(e);
				System.out.println("Elemento inserido no inicio!");

			} else if (opc == 2) {

				No e = criaElem();
				ll.insereUltimo(e);
				System.out.println("Elemento inserido ao final!");

			} else if (opc == 3) {
				if (vazia()) {
					continue;
				}
				No e = ll.removeInicio();
				System.out.println("Elemento removido do inicio!");
				printElem(e);
			} else if (opc == 4) {
				if (vazia()) {
					continue;
				}
				No e = ll.removeUltimo();
				System.out.println("Elemento removido do final!");
				printElem(e);
			} else if (opc == 5) {
				ll.imprimeParaFim();
			} else if (opc == 6) {
				ll.imprimeParaInicio();
			} else if (opc == 7 || opc == 8) {
				Scanner scn7 = new Scanner(System.in);
				System.err.println("A posicao informada não pode ser maior que a quantidade de elementos!");
				System.out.print("Informe a posicao: ");
				int pos = scn7.nextInt();
				No e = null;
				if (opc == 7) {
					e = ll.getPosDoInicio(pos);
				} else if (opc == 8) {
					e = ll.getPosDoFim(pos);
				}
				printElem(e);
			} else if (opc == 9 || opc == 10) {
				Scanner scn9 = new Scanner(System.in);
				System.err.println("A posicao informada não pode ser maior que a quantidade de elementos!");
				System.out.print("Informe a posicao: ");
				int pos = scn9.nextInt();
				No e = null;
				if (opc == 9) {
					e = ll.getElemDoInicio(pos);
				} else if (opc == 8) {
					e = ll.getElemDoFim(pos);
				}
				printElem(e);
			} else if (opc == 11 || opc == 12) {

				if (opc == 12) {
					// TODO
					continue;
				}
				Scanner sc11 = new Scanner(System.in);
				System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos.");
				System.out.print("Informe a posicao: ");
				int pos = sc11.nextInt();
				No e = criaElem();
				String s = "";
				if (opc == 11) {
					s = ll.inserePosParaFim(pos, e);
				} else if (opc == 12) {

				}
				if (s.equals("")) {
					System.out.println("Inclusão bem sucedida.");
				} else {
					System.out.println(s);
				}
			} else if (opc == 13 || opc == 14) {
				if (opc == 13) {
					// TODO
					continue;
				}
				Scanner sc13 = new Scanner(System.in);
				System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos");
				System.out.print("Informe a posicao: ");
				int pos = sc13.nextInt();
				No e = ll.removePosParaInicio(pos);
				if (e != null) {
					System.out.println("Elemento removido");
					printElem(e);
				} else {
					System.err.println("ERRO: lista vazia ou parâmetros incorretos");
				}

			} else if (opc == 98) {
				inicializa();
				System.out.println("Lista inicializada com valores na ordem do inicio para o final");
			} else if (opc == 99) {
				ll.destroi();
				System.out.println("A lista foi apagada!");
			}

		}

	}

	private static void printElem(No e) {
		if (e == null) {
			System.err.println("Número nulo!");
		} else {
			System.out.println(e.getNome() + " | " + e.getValor());
		}
	}

	private static No criaElem() {

		Scanner sc = new Scanner(System.in);
		String nm;
		int nr;
		while (true) {
			try {

				System.out.print("\n\nNome do elemento: ");
				nm = sc.nextLine();
				System.out.print("Valor do elemento: ");
				nr = sc.nextInt();
				break;
			} catch (Exception ex) {
				sc.next();
				System.err.println("Valor invalido!");
			}
		}
		return new No(nm, nr);
	}

	private static boolean vazia() {
		if (ll.isEmpty()) {
			System.out.println("A lista esta' vazia.");
			return true;
		}
		return false;
	}

	private static void inicializa() {

		for (int i = 100; i <= 300; i = i + 10) {
			No e = new No("Elemento_" + String.valueOf(i), i);
			ll.insereUltimo(e);
		}

	}

}
