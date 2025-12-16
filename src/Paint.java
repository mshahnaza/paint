import javax.swing.*;

public class Paint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaintGUI();
            }
        });
    }
}

// TODO make menu item icons better quality
// TODO clear out file management mess }
