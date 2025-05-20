package frontInterface;

import syntax.MoveNode;

import javax.swing.*;
import java.awt.*;
public class ChessTreeViewer extends JPanel {
    private final MoveNode root;

    public ChessTreeViewer(MoveNode root) {
        this.root = root;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, getWidth() / 2, 40, getWidth() / 4);
    }

    private void drawTree(Graphics g, MoveNode node, int x, int y, int offset) {
        if (node == null) return;

        g.setFont(new Font("Cooper Black", Font.BOLD, 14));
        g.setColor(Color.white);
        g.drawString(node.label, x - 20, y);

        int childY = y + 60;

        if (node.left != null) {
            int leftX = x - offset;
            g.drawLine(x, y + 5, leftX, childY - 15);
            drawTree(g, node.left, leftX, childY, offset / 2);
        }

        if (node.right != null) {
            int rightX = x + offset;
            g.drawLine(x, y + 5, rightX, childY - 15);
            drawTree(g, node.right, rightX, childY, offset / 2);
        }
    }

    public static void show(MoveNode root) {
        JFrame frame = new JFrame("Árbol de Partida");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.add(new ChessTreeViewer(root));
        frame.setVisible(true);
    }
}
