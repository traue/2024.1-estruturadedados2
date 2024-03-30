package jogo.view;

import java.util.Random;
import java.util.Scanner;

import jogo.ed.Jogador;
import jogo.ed.ListaLigadaJogo;
import jogo.ed.No;
import jogo.ed.STATUSCASA;

public class JogoTabuleiro {

	static int qtdCasas;
	static int numJogadores;

	static Jogador jogadores[];

	static int jogadorDaVez = 0;
	static int rodada = 0;
	static boolean encerrar = false;

	static ListaLigadaJogo game;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("CONFIGURAÇÕES INICIAIS DO JOGO");
		System.out.print(" Quantidade de casas [10-99]: ");
		qtdCasas = sc.nextInt();

		// verifica as quantidades
		if (qtdCasas < 10 || qtdCasas > 99) {
			System.err.printf("ERRO: quantidade de casas do tabuleiro invalida (%d)\n", qtdCasas);
			sc.close();
			return;
		}

		System.out.print(" Número de jogares [2-4]: ");
		numJogadores = sc.nextInt();

		if (numJogadores > 4 || numJogadores < 2) {
			System.err.printf("ERRO: quantidade de jogadores invalida (%d)\n", numJogadores);
			sc.close();
			return;
		}

		// inicia a criação do jogo
		game = new ListaLigadaJogo();

		// cria o tabuleiro
		for (int i = qtdCasas; i >= 1; i--) {
			No e = new No();
			e.setPosicao(i);
			game.insereInicio(e);
		}

		// cria os jogadores
		sc.nextLine();
		jogadores = new Jogador[numJogadores];
		for (int i = 0; i < numJogadores; i++) {
			System.out.printf("Nome do jogador %d:", i + 1);
			String nomeJogador = sc.nextLine();
			jogadores[i] = new Jogador(i, nomeJogador);
		}

		System.out.println("...PRONTO: JOGO CRIADO. INICIANCO...");

		LOOPJOGO: while (true) {

			System.out.println();
			System.out.printf("Casas inicias: %d\n", qtdCasas);
			System.out.printf("Quantidade de casas atuais: %d\n", game.getQtd());

			for (int i = 0; i < numJogadores; i++) {
				System.out.printf(">> Jogador %d: ", i + 1);
				Jogador jn = jogadores[i];
				System.out.println(jn.getNome());
			}

			System.out.println("****************************");
			game.imprimeHorario(jogadores);
			Jogador jogadorAtual = proxAJogar();

			// numero da rodada
			if (jogadorAtual.getNumero() == 1) {
				rodada++;
			}
			System.out.printf("Rodada: %d\n", rodada);

			// próximo jogador
			System.out.printf("Proximo a jogar: %s\n", jogadorAtual.getNome());
			System.out.println("****************************");

			System.out.println("OPCOES DO JOGO:");
			System.out.println(" [1] - Jogar");
			System.out.println(" [0] - Encerrar");
			sc.nextLine();

			while (true) {

				System.out.print(">> Opcao: ");
				int opc = sc.nextInt();
				if (opc < 0 || opc > 1) {
					System.err.println("Ops.. opção inválida!");
					continue;
				}

				if (opc == 0) {
					sc.nextLine();
					System.out.print("Encerrar o jogo?");
					System.out.println(" [0] - Não");
					System.out.println(" [1] - Sim");
					if (sc.nextInt() == 1) {
						encerrar = true;
						break;
					} else {
						continue;
					}
				} else if (opc == 1) {
					// verifica se o jogador perdeu a vez
					if (jogadorAtual.getPerdeAVez() > 0) {
						System.out.println("Voce perdeu a vez...");
						jogadorAtual.setPerdeAVez(jogadorAtual.getPerdeAVez() - 1);
						continue LOOPJOGO;
					}

					int dado = dado();

					System.out.printf("Saiu o número: %d\n ", dado);
					while (true) {

						// escolher o sentido a percorrer
						System.out.print("Escolha a direcao: [1]Horario  |  [2]Antihorário");
						int direcao = sc.nextInt();
						if (direcao < 1 || direcao > 2) {
							System.err.println("Direcao inválida!");
							continue;
						}
						No casaAtu = jogadorAtual.getCasa(); // casa atual em que o está
						if (casaAtu == null) {
							casaAtu = game.getInicio();
						} else {
							dado++;
						}
						No casaNova;
						if (direcao == 1) {
							casaNova = game.getPosHorario(casaAtu, dado);
						} else {
							casaNova = game.getPosAntiHorario(casaAtu, dado);
						}

						jogadorAtual.setCasa(casaNova);
						jogadorAtual.setPerdeAVez(casaNova.getPenalidade());

						// se a casa estiver livre, marca
						if (casaNova.getStatus() == STATUSCASA.LIVRE) {
							casaNova.setStatus(STATUSCASA.MARCADA);
							casaNova.setJogador(jogadorAtual.getNumero());
						}
						// casa marcada por outro jogador, então volta a ser livre
						else if (casaNova.getStatus() == STATUSCASA.MARCADA
								&& casaNova.getJogador() != jogadorAtual.getNumero()) {
							casaNova.setStatus(STATUSCASA.LIVRE);
							casaNova.setJogador(0);
						}
						// casa marcada pelo mesmo jogador, então passa a ser proprietario
						else if (casaNova.getStatus() == STATUSCASA.MARCADA
								&& casaNova.getJogador() == jogadorAtual.getNumero()) {
							casaNova.setStatus(STATUSCASA.PROPRIETARIA);
							casaNova.setJogador(jogadorAtual.getNumero());
							// se a casa anterior é do mesmo jogador, cria uma nova no meio
							No casaAnterior = casaNova.getAnterior();
							if (casaAnterior.getStatus() == STATUSCASA.PROPRIETARIA
									&& casaAnterior.getJogador() == jogadorAtual.getNumero()) {
								No casaBonus = new No();
								casaBonus.setJogador(jogadorAtual.getNumero());
								casaBonus.setStatus(STATUSCASA.PROPRIETARIA);
								game.insereHorario(casaBonus, casaNova);
							}
							// se a casa posterior é do mesmo jogador, cria uma nova no meio
							No casaPosterior = casaNova.getProximo();
							if (casaPosterior.getStatus() == STATUSCASA.PROPRIETARIA
									&& casaPosterior.getJogador() == jogadorAtual.getNumero()) {
								No casaBonus = new No();
								casaBonus.setJogador(jogadorAtual.getNumero());
								casaBonus.setStatus(STATUSCASA.PROPRIETARIA);
								game.insereHorario(casaBonus, casaNova.getProximo());
							}
						} else if (casaNova.getStatus() == STATUSCASA.PROPRIETARIA
								&& casaNova.getJogador() != jogadorAtual.getNumero()) {
							jogadorAtual.setPerdeAVez(jogadorAtual.getPerdeAVez() + 1);
						}
						break;
					}
				}
				break;
			}
			if (encerrar) {
				break;
			}

		}
		System.out.println("Jogo encerrado.");
		sc.close();
	}

	private static Jogador proxAJogar() {

		if (jogadorDaVez == 0) {
			jogadorDaVez = 1;
		} else {
			jogadorDaVez++;
			if (jogadorDaVez > numJogadores) {
				jogadorDaVez = 1;
			}
		}

		return jogadores[jogadorDaVez - 1];
	}

	private static int dado() {
		Random rnd = new Random();
		return rnd.nextInt(6) + 1;
	}
}
