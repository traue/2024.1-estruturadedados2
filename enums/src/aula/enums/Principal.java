package aula.enums;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Você encontrou uma porta... ");
		System.out.print("O que você faz...? ");
		String acao = sc.nextLine();
		Porta p = new Porta();
		p.cor = "Azul";
		p.estado = EstadoPorta.TRANCADA;
		
		if (acao.equals("tentar abrir") ) {
			System.out.println("Ops.. você precisa de uma chave!");
		}
		
		if (acao.equals("ignorar") ) {
			System.out.println("Melhor não... há um professor correndo até você...");
		}
		
		if (acao.equals("destrancar") ) {
			System.out.println("A porta está tranca... destrancando...");
			p.estado = EstadoPorta.ABERTA;
			System.out.println("Show! Você passou pela porta.. o que faz agora? ");
			acao = sc.nextLine();
			if (acao == "trancar porta") {
				p.estado = EstadoPorta.FECHADA;
				System.out.println("A porta foi fechada!");
				p.estado = EstadoPorta.TRANCADA;
				System.out.println("Agora o professor está trancado do lado de fora.");
			}
		}
	}
}
