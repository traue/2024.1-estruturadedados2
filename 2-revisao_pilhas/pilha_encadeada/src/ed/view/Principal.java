package ed.view;

import java.util.Scanner;
import ed.tad.Pilha;

public class Principal {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			Pilha pilha = new Pilha();

			String msg = "[1] - Adicionar\n[2] - Remover\n[3] - Exibir\n[0] - Sair";
			int opc, e;

			do {
				System.out.println(msg);
				System.out.print("Opção: ");
				opc = sc.nextInt();

				switch (opc) {
				case 1:
					System.out.print("Elemento: ");
					e = sc.nextInt();
					pilha.push(e);
					break;
				case 2:
					System.out.println("Removido: " + pilha.pop());
					break;
				case 3:
					pilha.imprimirPilha();
					break;
				}
			} while (opc != 0);
		}
	}
}
