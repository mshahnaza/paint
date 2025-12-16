package Components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaintMenuBar extends JMenuBar {

    Canvas canvas;

    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem saveAsItem;
    JMenuItem exitItem;
    JMenuItem resetItem;

    JButton undoButton;
    JButton redoButton;
    JButton pickColorButton;

    JButton eraserButton;

    JPanel slider;

    ImageIcon openIcon;
    ImageIcon saveIcon;
    ImageIcon saveAsIcon;
    ImageIcon exitIcon;

    public PaintMenuBar(ActionListener listener, Canvas canvas) {
        this.canvas = canvas;

        JMenu fileMenu = new JMenu("File");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save as");
        exitItem = new JMenuItem("Exit");

        openIcon = new ImageIcon("src/icons/openFile.png");
        saveIcon = new ImageIcon("src/icons/save.png");
        saveAsIcon = new ImageIcon("src/icons/saveAs.png");
        exitIcon = new ImageIcon("src/icons/exit.png");

        openItem.setIcon(openIcon);
        saveItem.setIcon(saveIcon);
        saveAsItem.setIcon(saveAsIcon);
        exitItem.setIcon(exitIcon);

        fileMenu.setMnemonic('F');
        openItem.setMnemonic('O');
        saveAsItem.setMnemonic('S');
        exitItem.setMnemonic('X');

        openItem.addActionListener(listener);
        saveItem.addActionListener(listener);
        saveAsItem.addActionListener(listener);
        exitItem.addActionListener(listener);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);

        this.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");

        resetItem = new JMenuItem("Reset");
        ImageIcon resetIcon = new ImageIcon("src/icons/reset.png");
        resetItem.setIcon(resetIcon);

        resetItem.addActionListener(listener);

        editMenu.add(resetItem);

        this.add(editMenu);

        pickColorButton = new JButton("Pick color");
        pickColorButton.setBorderPainted(false);
        pickColorButton.setFocusPainted(false);
        pickColorButton.setContentAreaFilled(false);
        pickColorButton.addActionListener(listener);
        this.add(pickColorButton);

        ImageIcon undoIcon = new ImageIcon("src/icons/undo.png");
        ImageIcon redoIcon = new ImageIcon("src/icons/redo.png");

        undoButton = new JButton(undoIcon);
        undoButton.setMaximumSize(new Dimension(48,48));
        undoButton.setBorderPainted(false);
        undoButton.setFocusPainted(false);
        undoButton.setContentAreaFilled(false);
        undoButton.addActionListener(listener);
        this.add(undoButton);

        redoButton = new JButton(redoIcon);
        redoButton.setBorderPainted(false);
        redoButton.setFocusPainted(false);
        redoButton.setContentAreaFilled(false);
        redoButton.addActionListener(listener);
        this.add(redoButton);

        ImageIcon eraserIcon = new ImageIcon("src/icons/eraser.png");
        eraserButton = new JButton(eraserIcon);
        eraserButton.setMaximumSize(new Dimension(32,32));
        eraserButton.setFocusable(false);
        eraserButton.setContentAreaFilled(false);
        eraserButton.setBorderPainted(true);
        eraserButton.setBorder(null);
        eraserButton.addActionListener(listener);

        this.add(eraserButton);
        this.add(Box.createHorizontalStrut(20));
        slider = new StrokeSizeSlider(canvas);
        this.add(slider);
    }

    public JMenuItem getOpenItem() {
        return openItem;
    }

    public JMenuItem getSaveItem() {
        return saveItem;
    }

    public JMenuItem getSaveAsItem() {
        return saveAsItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getResetItem() {
        return resetItem;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
        return redoButton;
    }

    public JButton getPickColorButton() {
        return pickColorButton;
    }

    public JButton getEraserButton() {
        return eraserButton;
    }
    private int eraserSize = 20;

    public void pickEraser() {
        eraserButton.setBorder(new LineBorder(Color.BLACK, 3));
        canvas.setCurrentColor(canvas.getBackground());
    }
    public void unpickEraser() {
        eraserButton.setBorder(null);
        canvas.setCurrentColor(Color.BLACK);
    }
}
