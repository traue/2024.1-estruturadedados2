//implementação baseada em: https://github.com/KnIfER/RBTree-java

package ed.tad.redblacktree.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ed.tad.redblacktree.COLOR;
import ed.tad.redblacktree.RedBlackTree;
import ed.tad.redblacktree.RedBlackTreeNode;
import ed.tad.redblacktree.RedBlackTree.inOrderDo;

public class RedBlackTreeTest {
	static RedBlackTree<Integer> tree;
	private static final int a[] = { 3, 7, 8, 10, 11, 18, 22, 26 };
	private static final boolean mDebugInsert = false;
	private static final boolean mDebugDelete = false;

	public static void main(String[] args) {
		int i, ilen = a.length;
		tree = new RedBlackTree<Integer>();

		System.out.printf("== Raw Data: ");
		for (i = 0; i < ilen; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		for (i = 0; i < ilen; i++) {
			tree.insert(a[i]);
			if (mDebugInsert) {
				System.out.printf("== Add Node: %d\n", a[i]);
				System.out.printf("== Tree Detail: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		System.out.printf("== Preorder: ");
		tree.preOrder();

		System.out.printf("\n== Inorder: ");
		tree.inOrder();

		System.out.printf("\n== Postorder: ");
		tree.postOrder();
		System.out.printf("\n");

		System.out.printf("== min: %s\n", tree.minimum());
		System.out.printf("== max: %s\n", tree.maximum());
		System.out.printf("== details: \n");
		tree.print();
		System.out.printf("\n");

		if (mDebugDelete) {
			for (i = 0; i < ilen; i++) {
				tree.remove(a[i]);

				System.out.printf("== Delete Node: %d\n", a[i]);
				System.out.printf("== Tree Details: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		// System.out.println(tree.search(100).key + "");
		// System.out.println(tree.xxing(99).key + "");
		// System.out.println(tree.xxing(90).key + "");
		// System.out.println(tree.xxing(89).key + "");

		drawTree();
	}

	private static void drawTree() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Frame frame = new Frame();
				frame.setVisible(true);

			}
		});
	}

	public static class Frame extends JFrame {
		private static final long serialVersionUID = 1L;
		public static final String TITLE = "RedBlack Tree Test";
		public static final int HEIGHT = 700;
		public static final int WIDTH = (int) (HEIGHT / 0.618);

		public Frame() {
			super();
			initFrame();
		}

		private void initFrame() {
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			Panel panel = new Panel(this);
			setContentPane(panel);
		}

	}

	public static class Panel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Frame frame;

		public Panel(Frame frame) {
			super();
			this.frame = frame;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
//			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			((Graphics2D) g).setColor(Color.RED);
//			((Graphics2D) g).drawLine(0, 25, 1366, 25);
//			((Graphics2D) g).drawLine(25, 768, 25, 0);
			final Graphics2D g2 = (Graphics2D) g.create();
			final float ratio = frame.getWidth() * .8f / (tree.maximum() - tree.minimum());
			final float offset = tree.minimum() * ratio - 25;

			g2.setFont(new Font(null, Font.ITALIC, 25));
			tree.SetInOrderDo(new inOrderDo() {

				public void dothis(RedBlackTreeNode<?> n) {
					@SuppressWarnings("unchecked")
					RedBlackTreeNode<Integer> n2 = (RedBlackTreeNode<Integer>) n;
					if (n2.color == COLOR.BLACK)
						g2.setColor(Color.black);
					else
						g2.setColor(Color.red);
					g2.drawString(n2.key + "", n2.key * ratio - offset, tree.inorderCoounter2 * 100);
					g2.setColor(Color.BLUE);
					// draw relationship line
					if (n2.parent != null)
						g2.drawLine((int) (n2.key * ratio - offset), tree.inorderCoounter2 * 100,
								(int) (n2.parent.key * ratio - offset), (tree.inorderCoounter2 - 1) * 100);

				}
			});
			tree.inOrderDo();

		}

	}

}
