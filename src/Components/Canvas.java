package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel{
    private final int height;
    private final int width;

    private int x, y;

    private List<DrawnPath> allPaths;
    private List<DrawnPath> redoPaths;

    private List<Components.Point> currentPath;

    private Color currentColor = Color.BLACK;
    private int currentBrushSize = 8;

    public Canvas(int height, int width){
        super();
        this.height = height;
        this.width = width;

        this.setPreferredSize(new Dimension(width, height));
        allPaths = new ArrayList<>(25);
        redoPaths = new ArrayList<>(25);

        MouseAdapter ma = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                currentPath = new ArrayList<>(25);
                Components.Point firstPoint = new Components.Point(x, y);
                currentPath.add(firstPoint);
                allPaths.add(new DrawnPath(currentPath,currentColor,currentBrushSize));
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                if(!currentPath.isEmpty()){
                    Components.Point prevPoint = currentPath.get(currentPath.size()-1);
                    Components.Point newPoint = new Components.Point(x, y);
                    currentPath.add(newPoint);
                    repaint();
                }

                Components.Point nextPoint = new Components.Point(e.getX(), e.getY());

                currentPath.add(nextPoint);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentPath = null;
            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }

    public int getStrokeSize() {
        return currentBrushSize;
    }

    public void setBrushSize(int brushSize) {
        currentBrushSize = brushSize;
    }

    public void reset(){
        for (Component comp : this.getComponents()) {
            if (comp instanceof JLabel) {
                this.remove(comp);
            }
        }
        // clears all rectangles
        Graphics g = getGraphics();
        g.clearRect(0, 0, width, height);
        g.dispose();

        // reset the path
        allPaths = new ArrayList<>(25);
        currentPath = null;

        repaint();
        revalidate();
    }

    public void undo() {
        if (!allPaths.isEmpty()) {
            DrawnPath allPathsLast = allPaths.remove(allPaths.size() - 1);
            redoPaths.add(allPathsLast);
            this.revalidate();
            this.repaint();
        }
    }

    public void redo() {
        if(!redoPaths.isEmpty()) {
            DrawnPath redoPathsLast = redoPaths.remove(redoPaths.size() - 1);
            allPaths.add(redoPathsLast);
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        for(DrawnPath drawnPath : allPaths){
            g2d.setStroke(new BasicStroke(drawnPath.brushSize));
            g2d.setColor(drawnPath.color);
            List<Point> path = drawnPath.path;

            Components.Point from = null;
            for(Components.Point point : path){
                if(path.size() == 1) {
                    g2d.fillOval(point.x-4, point.y-4, drawnPath.brushSize, drawnPath.brushSize);
                }
                if(from != null){
                    g2d.drawLine(from.x, from.y, point.x, point.y);
                }
                from = point;
            }
        }
    }
}
