package frontInterface;

import javax.swing.*;

public class Tree extends JFrame {
    public Tree() {
        setTitle("frontInterface.Tree Frame");

        // Crear panel y label
        JPanel panel = new JPanel();
        JLabel label = new JLabel("frontInterface.Tree");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        panel.add(label);

        // Configurar frame
        setContentPane(panel);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // o EXIT_ON_CLOSE si es el final de la app
    }
}
