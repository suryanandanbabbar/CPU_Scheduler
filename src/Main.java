import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Panel().setVisible(true));
    }
}