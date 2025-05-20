package frontInterface;

import syntax.MoveNode;
import syntax.Syntax;

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
        // Panel principal (contenedor de los tres JPanels)
        main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(Color.WHITE);

// ---------- JPanel1: Encabezado con título ----------
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.decode("#212121"));
        panel1.setMaximumSize(new Dimension(50,40));
        JLabel titleLabel = new JLabel("Welcome to ChessChecked");
        titleLabel.setFont(new Font("Cooper Black", Font.ITALIC, 16));
        titleLabel.setForeground(Color.WHITE);
        panel1.add(titleLabel);

// ---------- JPanel2: Columna izquierda ----------
JPanel panel2 = new JPanel();
panel2.setBackground(Color.decode("#2f2f2f"));
panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

Box box2 = Box.createVerticalBox();
box2.add(Box.createVerticalGlue());

JLabel labelSan = new JLabel("Enter chess game in SAN:");
labelSan.setForeground(Color.WHITE);
labelSan.setFont(new Font("Cooper Black", Font.PLAIN, 16));
labelSan.setAlignmentX(Component.CENTER_ALIGNMENT);

JTextField textField1 = new JTextField(25);
textField1.setMaximumSize(new Dimension(300, 50));
textField1.setAlignmentX(Component.CENTER_ALIGNMENT);

ImageIcon generateTreePNG = new ImageIcon("chessGame/src/frontInterface/resources/buttons/verify-button.png");
JButton button2 = new JButton(new ImageIcon(generateTreePNG.getImage().getScaledInstance(168, 60, Image.SCALE_SMOOTH)));
button2.setBorderPainted(false);
button2.setContentAreaFilled(false);
button2.setAlignmentX(Component.CENTER_ALIGNMENT);

box2.add(labelSan);
box2.add(Box.createVerticalStrut(20));
box2.add(textField1);
box2.add(Box.createVerticalStrut(20));
box2.add(button2);
box2.add(Box.createVerticalGlue());

panel2.add(box2);

// ---------- JPanel3: Columna derecha ----------
JPanel panel3 = new JPanel();
panel3.setBackground(Color.decode("#2f2f2f"));
panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

Box box3 = Box.createVerticalBox();
box3.add(Box.createVerticalGlue());

JLabel labelTree = new JLabel("Binary tree for the game");
labelTree.setForeground(Color.WHITE);
labelTree.setFont(new Font("Cooper Black", Font.PLAIN, 16));
labelTree.setAlignmentX(Component.CENTER_ALIGNMENT);

ImageIcon verifyPNG = new ImageIcon("chessGame/src/frontInterface/resources/buttons/GenerateTree-button.png");
JButton button1 = new JButton(new ImageIcon(verifyPNG.getImage().getScaledInstance(168, 60, Image.SCALE_SMOOTH)));
button1.setBorderPainted(false);
button1.setContentAreaFilled(false);
button1.setAlignmentX(Component.CENTER_ALIGNMENT);

box3.add(labelTree);
box3.add(Box.createVerticalStrut(60));
box3.add(button1);
box3.add(Box.createVerticalGlue());

panel3.add(box3);


// ---------- Panel central que contiene panel2 y panel3 ----------
        JPanel centerPanel = new JPanel();
	centerPanel.setBackground(Color.decode("#3a3d3d"));
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(panel2);
        centerPanel.add(panel3);

// ---------- Agregamos todo al panel principal ----------
        main.add(panel1, BorderLayout.NORTH);
        main.add(Box.createHorizontalStrut(5));
        main.add(centerPanel, BorderLayout.CENTER);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String game =  textField1.getText();
                //proveMatch(game);
                if(proveMatch(game)){
                    MoveNode root = Syntax.buildTreeFromMatch(game);
                    // Mostrar árbol
                    SwingUtilities.invokeLater(() -> ChessTreeViewer.show(root));
                }
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
                            "Please fill the text field.",
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
        //frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.setSize(1000,500);
        frame.setVisible(true);
        MessageStyles.applyGlobalStyle();

    }
}

