package ed.view;

import java.util.Random;
import java.util.Scanner;

import ed.tad.Vetor;

public class Principal {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			int tamanho, minimo, maximo, vaga, repete;

			while (true) {

				System.out.println("================================================");
				System.out.println("      CONFIGURACOES INICIAIS DO VETOR");
				System.out.println("================================================");

				// TAMANHO DO VETOR
				System.out.print("\nQual o tamanho do vetor: ");
				tamanho = sc.nextInt();
				if (tamanho <= 0) {
					System.out.println("ERRO: Vetor deve ter tamanho maior que zero!!");
					continue;
				}

				// VALORES MÍNIMO E MÁXIMO
				System.out.print("\nValor mínimo no vetor: ");
				minimo = sc.nextInt();
				System.out.print("\nValor máximo no vetor: ");
				maximo = sc.nextInt();
				if (minimo > maximo) {
					System.out.println("ERRO: Valor maximo deve ser maior que o valor minimo");
					continue;
				}

				// VALOR INDICATIVO DE POSICAO VAGA
				System.out.print("\nValor que indica que uma posição está vaga: ");
				vaga = sc.nextInt();
				if (vaga >= minimo && vaga <= maximo) {
					System.out.println(
							"ERRO: Valor indicativo de posicao vaga nao pode estar no intervalo de valores valido!!");
					continue;
				}

				// PODE OU NÃO REPETIR
				System.out.print("\nPode haver repetição de valores (1) SIM | (2) NÃO? ");
				repete = sc.nextInt();
				if (repete < 1 || repete > 2) {
					System.out.println("ERRO: Valor invalido. Informe 1 ou 2.");
					continue;
				}

				break;
			}

			Vetor vetor = new Vetor(tamanho, minimo, maximo, vaga, repete);

			while (true) {

				System.out.println("===================================================");
				System.out.println("\t\t\t\tOperação no Vetor");
				System.out.println("===================================================");
				System.out.println("1 - Atribuir um valor a determinada posição.");
				System.out.println("2 - Alterar o valor de determinada posição.");
				System.out.println(
						"3 - Remover o valor de determinada posição (atribui o valor indicativo de posição vaga).");
				System.out.println("4 - Ler o conteúdo de uma posicao.");
				System.out.println(
						"5 - Localizar um valor e retornar sua posição (se permitir repetição tem que retornar todos).");
				System.out.println("6 - Inserir na primeira posição vaga (busca no sentido 0 → N).");
				System.out.println("7 - Remover da última posição ocupada (busca no sentido 0 → N).");
				System.out.println("8 - Imprimir o conteúdo do vetor.");

				System.out.println("9 - Encerrar");

				System.out.print("\nSua opcao: ");
				int opc = sc.nextInt();

				int valor;
				int posicao;

				switch (opc) {
				case 1:
					System.out.println("=== ATRIBUIÇÃO ===");
					System.out.print("Valor: ");
					valor = sc.nextInt();
					System.out.print("Posicao: ");
					posicao = sc.nextInt();
					Utils.mostaMennsagem(vetor.armazenar(valor, posicao));
					continue;
				case 2:
					System.out.println("=== ALTERAÇÃO ===");
					System.out.print("Valor: ");
					valor = sc.nextInt();
					System.out.print("Posicao: ");
					posicao = sc.nextInt();
					Utils.mostaMennsagem(vetor.alterar(valor, posicao));
					continue;
				case 3:
					System.out.println("===   REMOCÃO ===");
					System.out.print("Posicao a remover: ");
					posicao = sc.nextInt();
					Utils.mostaMennsagem(vetor.excluir(posicao));
					continue;
				case 4:
					System.out.println("=== CONTEÚDO ===");
					System.out.print("Posicao a ler: ");
					posicao = sc.nextInt();
					System.out.println("Posicao " + posicao + "contem " + vetor.ler(posicao));
					continue;
				case 5:
					System.out.println("=== LOCALIZANDO UM VALOR ===");
					System.out.print("Valor a localizar: ");
					valor = sc.nextInt();
					int[] valores = vetor.localizar(valor, 0);
					if (valores[0] == 0) {
						System.out.println("\n\nVALOR NAO LOCALIZADO.\n\n");
					} else {
						System.out.println("\n\n===  VALORES ENCONTRADOS NOS INDICES ABAIXO   ===");
						for (int i = 1; i < valores.length; i++) {
							if (valores[i] == 0) {
								break;
							}
							System.out.print(valores[i] + " ");
						}
						System.out.println("\n" + valores[0] + " valor(es) encontrado(s).");
					}
					continue;
				case 6:
					System.out.println("===   INSERINDO NA PRIMEIRA POSICAO VAGA   ===");
					System.out.println("Valor a inserir: ");
					valor = sc.nextInt();
					Utils.mostaMennsagem(vetor.armazenar1Vaga(valor));
					continue;
				case 7:
					System.out.println("===   REMOVENDO DA ULTIMA POSICAO OCUPADA   ===");
					Utils.mostaMennsagem(vetor.removerUltima());
					continue;
				case 8:
					for (int i = 0; i < tamanho; i++) {
						System.out.println(i + " = " + vetor.ler(i));
					}
					continue;
				case 99:
					vetor.limparVetor();
					Random seed = new Random();
					for (int i = 0; i < tamanho; i++) {
						vetor.armazenar(minimo + seed.nextInt(maximo + 1 - minimo), i);
					}
				case 9:
					return;
				default:
					continue;
				}

			}
		}
	}
}
