package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static syntax.Syntax.proveMatch;

public class Menu {
    private JPanel main;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;


    public Menu(JFrame frame) {
        main = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 170, 10, 170); // Espaciado entre componentes
        // ----------- Línea 1: Título ---------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        main.add(new JLabel("Welcome to chessChecked"), gbc);

        // ----------- Línea 2: Labels ---------------
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.WEST;
        main.add(new JLabel("Enter chess game in SAN"), gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        main.add(new JLabel("Binary tree for the game"), gbc);

        // ----------- Línea 3: TextField y JButton1 ---------------
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 20;
        gbc.anchor = GridBagConstraints.WEST;
        textField1 = new JTextField(25);
        textField1.setPreferredSize(new Dimension(300, 50));
        main.add(textField1, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        button1 = new JButton("generate tree");
        button1.setBackground(new Color(173, 216, 230)); // Light blue
        main.add(button1, gbc);

        // ----------- Línea 4: JButton2 centrado abajo ---------------
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        button2 = new JButton("verify");
        button2.setBackground(new Color(173, 216, 230)); // Light blue
        main.add(button2, gbc);
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
                    proveMatch(game);
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

