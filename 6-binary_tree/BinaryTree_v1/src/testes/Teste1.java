/*
 * Implementação do Prof. André Kishimoto
 * 
 * */

package testes;

import ed.BinaryTree;
import ed.Node;

/*
 * Teste da seguinte árvore:
 * 
 * 		A
 * 	   / \
 *    B   C
 *     \  /
 *      D E
 *         \
 *          F
 *         / \
 *        G   H
 * 
 * */

public class Teste1 {
	public static void main(String[] args) {
		Node a = new Node("A");
		// System.out.println(a);

		// System.out.println("---------");

		Node b = new Node("B");
		// System.out.println(b);

		a.setLeft(b);
		b.setParent(a);

		// System.out.println(a);
		// System.out.println("---------");
		// System.out.println(b);

		Node c = new Node("C", a);
		a.setRight(c);

		Node d = new Node("D", b);
		b.setRight(d);

		Node e = new Node("E", c);
		c.setLeft(e);

		Node f = new Node("F", e);
		e.setRight(f);

		Node g = new Node("G", f);
		f.setLeft(g);

		Node h = new Node("H", f);
		f.setRight(h);

		BinaryTree tree = new BinaryTree(a);
		System.out.println(tree);
		System.out.println("Pré-ordem: " + tree.preOrderTranversal());
		System.out.println("Em-ordem: " + tree.inOrderTransversal());
		System.out.println("Pós-ordem: " + tree.preOrderTranversal());
		System.out.println("Por nível: " + tree.leverOrderTransversal());
	}
}
