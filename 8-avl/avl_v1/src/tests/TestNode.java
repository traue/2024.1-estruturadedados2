package tests;

import ed.AvlNode;
import ed.NodeData;

public class TestNode {
	
	public static void main(String[] args) {

		AvlNode n = new AvlNode<NodeData>(30, new NodeData(30, "trinta"));
		System.out.println(n);
	}
	
}
