package frontInterface;

import javax.swing.*;
import java.awt.*;

public class MessageStyles {

    // Método para aplicar estilos globales
    public static void applyGlobalStyle() {
        UIManager.put("OptionPane.background", Color.decode("#2f2f2f"));
        UIManager.put("Panel.background", Color.decode("#2f2f2f"));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", new Font("Cooper Black", Font.PLAIN, 14));

        UIManager.put("Button.background", Color.decode("#444444"));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Cooper Black", Font.PLAIN, 12));
    }
}

