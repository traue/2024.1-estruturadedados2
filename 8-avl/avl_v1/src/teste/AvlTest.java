package teste;

import java.util.Scanner;
import ed.AvlNode;
import ed.AvlTree;
import ed.NodeData;

public class AvlTest {

	private static AvlTree avl;

	public static void main(String[] args) {

		avl = new AvlTree();

		Scanner sc = new Scanner(System.in);

		while (true) {
			//em pt mesmo
			System.out.printf("Número de nós na arvore: %d\n", avl.getQty());
			System.out.println("OPÇÕES");
			System.out.println(" [0] - Finalizar");
			System.out.println(" [1] - Inserir nó na árvore");
			System.out.println(" [2] - Procurar nó por ID");
			System.out.println(" [3] - Excluir nó por ID");
			System.out.println("MÉTODOS ITERATIVOS DE IMPRESSÃO");
			System.out.println(" [4] - Em ordem");
			System.out.println(" [5] - Pré-ordem");
			System.out.println(" [6] - Pós-ordem");
			System.out.println("MÉTODOS RECURSIVOS DE IMPRESSÃO");
			System.out.println(" [7] - Em ordem");
			System.out.println(" [8] - Pré-ordem");
			System.out.println(" [9] - Pós-ordem");
			System.out.println("EXTRAS");
			System.out.println(" [10] - Obter nó raiz");
			System.out.println(" [11] - Inserir nó (método recursivo)");
			System.out.println(" [12] - Pré-carregar árvore 1: (100, 50, 200, 25, 75, 150, 300)");
			System.out.println(
					" [13] - Pré-carregar árvore 2: (100, 50, 200, 25, 75, 150, 300, 12, 30, 60, 120, 5, 15, 27, 35)");
			System.out.println(" [14] - Remover todos os nós");

			System.out.print("\nEscolha: ");

			int opc = sc.nextInt();

			if (opc == 0) {
				System.out.println("Done! :D");
				break;
			} else if (opc == 1) {
				System.out.print("ID: ");
				sc.nextLine();
				int id = sc.nextInt();
				System.out.print("Name: ");
				sc.nextLine();
				String nm = sc.next();

				NodeData elem = new NodeData(id, nm);
				AvlNode<NodeData> noa = new AvlNode<>(id, elem);

				if (avl.insert(noa)) {
					System.out.println("\nSuccessful.");
				} else {
					System.err.println("\nERROR: Inclusion denied.");
				}

			} else if (opc == 2) {
				System.out.print("Enter the ID of the NodeData to be located: ");
				sc.nextLine();
				int id = sc.nextInt();
				AvlNode<?>[] no = avl.findNodeById(id);
				if (no[1] == null) {
					System.out.println("ERROR: not in the tree");
				} else {
					System.out.println("\nLocalized element:");
					printElement(no[1]);
				}

			} else if (opc == 3) {

				System.out.print("Enter the ID of the NodeData to be extracted: ");
				sc.nextLine();
				int id = sc.nextInt();
				AvlNode<?> no = avl.deleteNodeById(id);
				if (no == null) {
					System.out.println("ERROR: not in the tree");
				} else {
					System.out.println("\nElement:");
					printElement(no);
				}

			} else if (opc == 4) {
				avl.inOrderIterative();
			} else if (opc == 5) {
				avl.preOrderIterative();
			} else if (opc == 6) {
				avl.postOrderIterative();
			} else if (opc == 7) {
				avl.inOrderRecursive(avl.getRoot());
			} else if (opc == 8) {
				avl.preOrderRecursive(avl.getRoot());
			} else if (opc == 9) {
				avl.postOrderRecursive(avl.getRoot());
			} else if (opc == 10) {
				if (avl.getQty() == 0) {
					System.err.println("Tree is empty!");
				} else {
					System.out.print("\nRoot element: ");
					printElement(avl.getRoot());
				}
			} else if (opc == 11) {
				System.out.print("Node ID: ");
				sc.nextLine();
				int id = sc.nextInt();
				System.out.print("Node Name: ");
				sc.nextLine();
				String nm = sc.next();

				NodeData elem = new NodeData(id, nm);
				AvlNode<NodeData> auxNode = new AvlNode<>(id, elem);

				if (avl.insert(avl.getRoot(), auxNode)) {
					System.out.println("\nSuccess!");
				} else {
					System.err.println("\n>>ERROR: insertion failed (check if this ID is not already present in the tree).");
				}

			} else if (opc == 12) {
				loadTree1();
			} else if (opc == 13) {
				loadTree2();
			} else if (opc == 14) {
				avl.emptyTree();
			}

		}

		sc.close();

	}

	private static void printElement(AvlNode<?> node) {
		NodeData elem = (NodeData) node.getData();
		System.out.println("Num -> " + elem.getValue() + " | Name -> " + elem.getName());
	}

	private static void loadTree1() {
		avl.insert(new AvlNode<>(100, new NodeData(100, "Cem")));
		avl.insert(new AvlNode<>(50, new NodeData(50, "Cinquenta")));
		avl.insert(new AvlNode<>(200, new NodeData(200, "Duzentos")));
		avl.insert(new AvlNode<>(25, new NodeData(25, "VinteECinco")));
		avl.insert(new AvlNode<>(75, new NodeData(75, "SetentaECinco")));
		avl.insert(new AvlNode<>(150, new NodeData(150, "Cento e Cinquenta")));
		avl.insert(new AvlNode<>(300, new NodeData(300, "Trezentos")));
	}

	private static void loadTree2() {

		avl.insert(new AvlNode<>(100, new NodeData(100, "Cem")));
		avl.insert(new AvlNode<>(50, new NodeData(50, "Cinquenta")));
		avl.insert(new AvlNode<>(200, new NodeData(200, "Duzentos")));
		avl.insert(new AvlNode<>(25, new NodeData(25, "VinteECinco")));
		avl.insert(new AvlNode<>(75, new NodeData(75, "SetentaECinco")));
		avl.insert(new AvlNode<>(150, new NodeData(150, "Cento e Cinquenta")));
		avl.insert(new AvlNode<>(300, new NodeData(300, "Trezentos")));

		avl.insert(new AvlNode<>(12, new NodeData(12, "12")));
		avl.insert(new AvlNode<>(30, new NodeData(30, "30")));
		avl.insert(new AvlNode<>(60, new NodeData(60, "60")));
		avl.insert(new AvlNode<>(120, new NodeData(120, "120")));
		avl.insert(new AvlNode<>(5, new NodeData(5, "5")));
		avl.insert(new AvlNode<>(15, new NodeData(15, "15")));
		avl.insert(new AvlNode<>(27, new NodeData(27, "27")));
		avl.insert(new AvlNode<>(35, new NodeData(35, "35")));
	}

}
