package Components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class ColorPicker extends JFrame implements ActionListener {

    Canvas canvas;

    Color currentCanvasColor;

    JButton red;
    JButton orange;
    JButton yellow;
    JButton green;
    JButton cyan;
    JButton blue;
    JButton black;

    Border colorPickedBorder = new LineBorder(Color.BLACK, 3);
    Border defaultBorder = new LineBorder(Color.BLACK);
    Border blackPickedBorder = new CompoundBorder(new LineBorder(Color.BLACK, 3), new LineBorder(Color.WHITE, 1));

    public ColorPicker(Canvas canvas) {
        currentCanvasColor = canvas.getCurrentColor();

        this.canvas = canvas;

        ImageIcon colorPaletteIcon = new ImageIcon("src/icons/colorPalette.png");
        this.setIconImage(colorPaletteIcon.getImage());

        setTitle("Pick Brush Color");
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel colorPalette = new JPanel();
        colorPalette.setPreferredSize(new Dimension(500, 100));
        colorPalette.setLayout(new FlowLayout());

        red = new JButton();
        red.setPreferredSize(new Dimension(50, 50));
        red.setBorder(defaultBorder);
        red.setBackground(Color.RED);

        orange = new JButton();
        orange.setPreferredSize(new Dimension(50, 50));
        orange.setBorder(defaultBorder);
        orange.setBackground(Color.ORANGE);

        yellow = new JButton();
        yellow.setPreferredSize(new Dimension(50, 50));
        yellow.setBorder(defaultBorder);
        yellow.setBackground(Color.YELLOW);

        green = new JButton();
        green.setPreferredSize(new Dimension(50, 50));
        green.setBorder(defaultBorder);
        green.setBackground(Color.GREEN);

        cyan = new JButton();
        cyan.setPreferredSize(new Dimension(50, 50));
        cyan.setBorder(defaultBorder);
        cyan.setBackground(Color.CYAN);

        blue = new JButton();
        blue.setPreferredSize(new Dimension(50, 50));
        blue.setBorder(defaultBorder);
        blue.setBackground(Color.BLUE);

        black = new JButton();
        black.setPreferredSize(new Dimension(50, 50));
        black.setBorder(defaultBorder);
        black.setBackground(Color.BLACK);

        red.addActionListener(this);
        orange.addActionListener(this);
        yellow.addActionListener(this);
        green.addActionListener(this);
        cyan.addActionListener(this);
        blue.addActionListener(this);
        black.addActionListener(this);

        colorPalette.add(red);
        colorPalette.add(orange);
        colorPalette.add(yellow);
        colorPalette.add(green);
        colorPalette.add(cyan);
        colorPalette.add(blue);
        colorPalette.add(black);

        if (currentCanvasColor == Color.RED) {
            red.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.ORANGE) {
            orange.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.YELLOW) {
            yellow.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.GREEN) {
            green.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.CYAN) {
            cyan.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.BLUE) {
            blue.setBorder(colorPickedBorder);
        }
        if (currentCanvasColor == Color.BLACK) {
            black.setBorder(blackPickedBorder);
        }
        add(colorPalette);
    }

    private void resetBorders() {
        red.setBorder(defaultBorder);
        orange.setBorder(defaultBorder);
        yellow.setBorder(defaultBorder);
        green.setBorder(defaultBorder);
        cyan.setBorder(defaultBorder);
        blue.setBorder(defaultBorder);
        black.setBorder(defaultBorder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == red) {
            resetBorders();
            red.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.RED);
            dispose();
        }
        if (e.getSource() == orange) {
            resetBorders();
            orange.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.ORANGE);
            dispose();
        }
        if (e.getSource() == yellow) {
            resetBorders();
            yellow.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.YELLOW);
            dispose();
        }
        if (e.getSource() == green) {
            resetBorders();
            green.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.GREEN);
            dispose();
        }
        if (e.getSource() == cyan) {
            resetBorders();
            cyan.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.CYAN);
            dispose();
        }
        if (e.getSource() == blue) {
            resetBorders();
            blue.setBorder(colorPickedBorder);
            canvas.setCurrentColor(Color.BLUE);
            dispose();
        }
        if (e.getSource() == black) {
            resetBorders();
            black.setBorder(blackPickedBorder);
            canvas.setCurrentColor(Color.BLACK);
            dispose();
        }
    }
}
