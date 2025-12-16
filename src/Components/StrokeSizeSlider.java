package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class StrokeSizeSlider extends JPanel {
    Canvas canvas;

    private JSlider slider;
    private JLabel label;

    public StrokeSizeSlider(Canvas canvas) {
        this.canvas = canvas;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
        slider.setPreferredSize(new Dimension(200, 40));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(3);
        slider.setValue(8);


        label = new JLabel("Stroke size:");

        // Add a label to display the current value
        JLabel valueLabel = new JLabel(String.valueOf(slider.getValue()));
        add(label);
        add(valueLabel);
        add(slider);
        // Add a listener to update the value label when the slider is adjusted
        slider.addChangeListener(e -> {
            int brushSize = slider.getValue();
            valueLabel.setText(String.valueOf(brushSize));
            canvas.setBrushSize(brushSize);
        });


        setOpaque(true);
        setBackground(null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Paint the panel background with a gradient
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(255, 255, 255),
                0, height, new Color(220, 220, 220)
        );
        g2d.setPaint(gradient);
        g2d.fill(new Rectangle2D.Double(0, 0, width, height));

        // Paint the slider background with a gradient
        Rectangle sliderBounds = getSliderBounds(this);
        gradient = new GradientPaint(
                (float) sliderBounds.x, (float) sliderBounds.y,
                new Color(255, 255, 255),
                (float) sliderBounds.x, (float) (sliderBounds.y + sliderBounds.height),
                new Color(220, 220, 220)
        );
        g2d.setPaint(gradient);
        g2d.fill(sliderBounds);

        g2d.dispose();
    }

    private Rectangle getSliderBounds(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component comp = panel.getComponent(i);
            if (comp instanceof JSlider) {
                return comp.getBounds();
            }
        }
        return new Rectangle(0, 0, 0, 0);
    }
}