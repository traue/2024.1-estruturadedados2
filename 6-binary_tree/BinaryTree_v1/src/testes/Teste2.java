/*
 * Implementação do Prof. André Kishimoto
 * 
 * */

package testes;

import ed.BinaryTree;
import ed.Node;

/**
 * Testando a seguinte árvore
 * 
 * A / \ B C / / \ D E F
 */
public class Teste2 {
	public static void main(String[] args) {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");

		a.setLeft(b);
		a.setRight(c);
		b.setLeft(d);
		c.setLeft(e);
		c.setRight(f);

		BinaryTree tree = new BinaryTree(a);

		System.out.println(tree);
		System.out.println("Pré-ordem: " + tree.preOrderTranversal());
		System.out.println("Em-ordem: " + tree.inOrderTransversal());
		System.out.println("Pós-ordem: " + tree.preOrderTranversal());
		System.out.println("Por nível: " + tree.leverOrderTransversal());
	}
}
