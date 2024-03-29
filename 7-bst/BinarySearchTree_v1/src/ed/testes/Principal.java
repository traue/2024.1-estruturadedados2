package ed.testes;

import java.util.Scanner;

import ed.BinarySearchTree;
import ed.Element;
import ed.Node;

public class Principal {

	private static BinarySearchTree bst;

	public static void main(String[] args) {

		bst = new BinarySearchTree();

		try (Scanner sc = new Scanner(System.in)) {
			while (true) {

				System.out.printf(">> Quantidade de nós na árvore: %d\n", bst.getQty());
				System.out.println("OPÇÕES");
				System.out.println(" [0] - Encerrar");
				System.out.println(" [1] - Inserir Nó");
				System.out.println(" [2] - Ler Mó");
				System.out.println(" [3] - Excluir Nó");
				System.out.println("IMPRESÕES ITERATIVAS");
				System.out.println(" [4] - Em ordem");
				System.out.println(" [5] - Pré-ordem");
				System.out.println(" [6] - Pós-ordem");
				System.out.println("IMPRESÃO RECURSIVAS");
				System.out.println(" [7] - Em ordem");
				System.out.println(" [8] - Pré-ordem");
				System.out.println(" [9] - Pós-ordem");
				System.out.println("EXTRAS:");
				System.out.println(" [10] - Imprimir raiz");
				System.out.println(" [11] - Inserir Nó recursivamente");
				System.out.println(" [12] - Preencher árvore (100, 60, 200, 20, 80, 160, 240, 70, 90, 230, 600)");

				System.out.print("\nEscolha: ");

				int opc = sc.nextInt();

				if (opc == 0) {
					System.out.println("\nEncerrado... :)");
					break;
				} else if (opc == 1) {
					System.out.print(" ID do Nó: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print(" Texto do nó: ");
					String nm = sc.nextLine();

					Element e = new Element(id, nm);
					Node<Element> auxNode = new Node<>(id, e);

					if (bst.insert(auxNode)) {
						System.out.println("\nInclusão bem sucedida!\n");
					} else {
						System.err.println("\nERRO: Inclusão negada");
					}

				} else if (opc == 2) {
					System.out.print(" ID do Nó buscado: ");
					int id = sc.nextInt();
					sc.nextLine();
					Node<?>[] Node = bst.findNodeById(id);
					if (Node[1] == null) {
						System.err.println("ERRO: Não encontrado\n");
					} else {
						System.out.println("\nNó localizado: ");
						printNode(Node[1]);
					}

				} else if (opc == 3) {

					System.out.print("ID do Nó a remover: ");
					int id = sc.nextInt();
					sc.nextLine();
					Node<?> Node = bst.remove(id);
					if (Node == null) {
						System.err.println("ERRO: Não encontrado");
					} else {
						System.out.println("\nNó extraido: ");
						printNode(Node);
					}

				} else if (opc == 4) {
					bst.inOrderIterative();
				} else if (opc == 5) {
					bst.preOrderIterative();
				} else if (opc == 6) {
					bst.postOrderIterative();
				} else if (opc == 7) {
					bst.inOrderRecursive(bst.getRoot());
					System.out.println();
				} else if (opc == 8) {
					bst.preOrderRecursive(bst.getRoot());
					System.out.println();
				} else if (opc == 9) {
					bst.postOrderRecursive(bst.getRoot());
					System.out.println();
				} else if (opc == 10) {
					if (bst.getQty() == 0) {
						System.err.println("Árvore vazia!");
					} else {
						System.out.print("\nNós raíz: ");
						printNode(bst.getRoot());
					}
				} else if (opc == 11) {

					System.out.print(" ID do Nó: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print(" Valor do Nó: ");
					String nm = sc.next();

					Element e = new Element(id, nm);
					Node<Element> auxNode = new Node<>(id, e);

					if (bst.insert(bst.getRoot(), auxNode)) {
						System.out.println("\nInserção realizada!\n");
					} else {
						System.err.println("\nERRO: Inserção falou!");
					}

				} else if (opc == 12) {
					carregaArvorePreenchida();
				}

			}
		}

	}

	// (100, 60, 200, 20, 80, 160, 240, 70, 90, 230, 600)
	private static void carregaArvorePreenchida() {
		bst.insert(new Node<Element>(100, new Element(100, "Cem")));
		bst.insert(new Node<Element>(60, new Element(60, "Sessenta")));
		bst.insert(new Node<Element>(200, new Element(200, "Duzentos")));
		bst.insert(new Node<Element>(20, new Element(20, "Vinte")));
		bst.insert(new Node<Element>(80, new Element(80, "Oitenta")));
		bst.insert(new Node<Element>(160, new Element(160, "Cento e sessenta")));
		bst.insert(new Node<Element>(240, new Element(240, "Duzentos e quarenta")));
		bst.insert(new Node<Element>(70, new Element(70, "Setenta")));
		bst.insert(new Node<Element>(90, new Element(90, "Noventa")));
		bst.insert(new Node<Element>(230, new Element(230, "Duzentos e trinta")));
		bst.insert(new Node<Element>(600, new Element(600, "Seiscentos")));
	}

	private static void printNode(Node<?> Node) {
		Element e = (Element) Node.getData();
		System.out.printf("ID: %d | Nome: %s\n", e.getValue(), e.getName());
	}
}
