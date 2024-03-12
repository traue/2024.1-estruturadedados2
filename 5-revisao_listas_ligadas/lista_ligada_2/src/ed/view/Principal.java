package ed.view;

import java.util.Scanner;

import ed.tad.ListaLigada;
import ed.tad.No;

public class Principal {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			ListaLigada ll = new ListaLigada();

			System.out.println("LISTA LIGADA - 2ª implementação");

			while (true) {
				System.out.println("\n[1] - Inserir elemento (no final)");
				System.out.println("[2] - Extrair elemento (do inicio)");
				System.out.println("[3] - Imprimir elementos da lista (sentido inicio -> fim)");
				System.out.println("[9] - Encerrar\n");
				System.out.print("Opcao: ");
				int opc = sc.nextInt();

				if (opc == 9) {
					break;
				} else if (opc == 1) {
					System.out.print("Nome: ");
					String nm = sc.next();
					System.out.print("Número: ");
					int nr = sc.nextInt();
					No e = new No(nm, nr);
					if (ll.enqueue(e)) {
						System.out.println("Elemento inserido!");
					} else {
						System.err.println("Falha na insercao!");
					}
				} else if (opc == 2) {
					No e = ll.dequeue();
					if (e == null) {
						System.err.println("A Lista está vazia!");
					} else {
						System.out.println("Elemento removido");
						System.out.println(" > Nome:" + e.getNome());
						System.out.println(" > Número: " + e.getNumero());
					}
				} else if (opc == 3) {
					System.out.println("Elementos da fila:");
					if (ll.isEmpty()) {
						System.err.println("A fila está vazia!");
					} else {
						No e = ll.getInicio();
						while (e != null) {
							System.out.println(e.getNumero() + " | " + e.getNome());
							e = e.getProximo();
						}
					}

				}
			}
		}

	}

}
