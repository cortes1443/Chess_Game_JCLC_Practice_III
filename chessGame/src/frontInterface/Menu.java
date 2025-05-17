package frontInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static syntax.Syntax.ProveMatch;

public class Menu {
    private JPanel main;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;
    public Menu(JFrame frame) {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tree treeFrame = new Tree();
                treeFrame.setVisible(true);



            }
        });
        button2.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String game =  textField1.getText();
                if (game.trim().isEmpty()) {
                    // Mostrar una ventana emergente si el campo está vacío
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, ingrese un valor en el campo de texto.",
                            "Campo vacío",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else  {
                    ProveMatch(game);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return main;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Menu");
        Menu menuInstance = new Menu(frame);



        frame.setContentPane(menuInstance.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
        frame.setVisible(true);

    }
}

