package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallComponent extends JPanel {
    public static final int DEFAULT_WIDTH=450;
    public static final int DEFAULT_HEIGHT=450;

    private java.util.List<Ball> balls=new ArrayList<>();

    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        for (Ball b:balls
             ) {
            g2.fill(b.getShape());
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
