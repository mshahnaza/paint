
import Components.ColorPicker;
import Components.PaintMenuBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PaintGUI extends JFrame implements ActionListener {

    Components.Canvas canvas;

    JLabel backgroundLabel;

    PaintMenuBar menuBar;

    boolean isCanvasSaved = false;

    PaintGUI() {
        this.setTitle("Paint");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowExitListener();
        this.setLayout(new BorderLayout());
        this.setSize(1080, 720);

        canvas = new Components.Canvas(420, 720);
        this.add(canvas, BorderLayout.CENTER);

        menuBar = new PaintMenuBar(this, canvas);
        this.setJMenuBar(menuBar);

        ImageIcon icon = new ImageIcon("src/icons/brush.jpg");
        this.setIconImage(icon.getImage());

        this.setVisible(true);
    }

    private void openFile(File selectedFile) {
        //deleting everything from canvas
        canvas.reset();

        ImageIcon icon = new ImageIcon(selectedFile.getPath());
        backgroundLabel = new JLabel(icon);
        backgroundLabel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));

        canvas.revalidate();
        canvas.repaint();
        canvas.add(backgroundLabel);
    }

    private void saveCanvas(Components.Canvas canvas) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (!selectedFile.getName().contains(".")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + "." + "png");
            }

            if (selectedFile.exists()) {
                int result = JOptionPane.showConfirmDialog(null,
                        "The file exists, do you want to replace it?",
                        "Confirm Save As", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (result != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            RepaintManager.currentManager(canvas).paintDirtyRegions();
            BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            canvas.paint(g2d);
            try {
                ImageIO.write(image, "png", new File(selectedFile.getAbsolutePath()));
                System.out.println("Panel saved as Image.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addWindowExitListener() {
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if(!isCanvasSaved) {
                    showExitDialogue();
                }  else {
                    System.exit(0);
                }
            }
        };
        addWindowListener(exitListener);
    }

    private void showExitDialogue() {
            int response = JOptionPane.showOptionDialog(
                    null, "Хотите ли вы сохранить ваши изменения?",
                    "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if(response == JOptionPane.YES_OPTION) {
                saveCanvas(canvas);
            } else if(response == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuBar.getOpenItem()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));

            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                    "Image Files (*.png, *.jpg, *.gif)", "png", "jpg", "gif");
            fileChooser.addChoosableFileFilter(imageFilter);
            fileChooser.setFileFilter(imageFilter);

            fileChooser.setAcceptAllFileFilterUsed(false);

            int result = fileChooser.showOpenDialog(canvas);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                openFile(selectedFile);
            }
        }

        if (e.getSource() == menuBar.getSaveAsItem()) {
            saveCanvas(canvas);
        }
        if (e.getSource() == menuBar.getExitItem()) {
            if(!isCanvasSaved) {
                showExitDialogue();
            }  else {
                System.exit(0);
            }
        }
        if (e.getSource() == menuBar.getResetItem()) {
            canvas.reset();
        }
        if (e.getSource() == menuBar.getUndoButton()) {
            canvas.undo();
        }
        if (e.getSource() == menuBar.getRedoButton()) {
            canvas.redo();
        }
        if (e.getSource() == menuBar.getPickColorButton()) {
            ColorPicker cp = new  ColorPicker(canvas);
            menuBar.unpickEraser();
        }
        if (e.getSource() == menuBar.getEraserButton()) {
            if(canvas.getCurrentColor() != Color.WHITE) {
                menuBar.pickEraser();
            } else {
                menuBar.unpickEraser();
            }
        }
    }
}

