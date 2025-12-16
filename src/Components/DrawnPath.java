package Components;

import java.awt.*;
import java.util.List;

public class DrawnPath {
    public List<Point> path;
    public Color color;
    public int brushSize;

    public DrawnPath(List<Point> path, Color color,int brushSize) {
        this.path = path;
        this.color = color;
        this.brushSize = brushSize;
    }
}
