package bounceThread;

import bounce.Ball;
import bounce.BallComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BounceThread {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS=1000;
    public static final int DELAY=5;

    public BounceFrame(){
        comp=new BallComponent();
        add(comp,BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        addButton(buttonPanel,"start baby",event->addBall());
        addButton(buttonPanel,"stop baby",event->System.exit(0));
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall(){
        Ball ball=new Ball();
        comp.add(ball);
        Runnable r=()-> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.repaint();
                    Thread.sleep(DELAY);
                }
            }catch (InterruptedException e)
            {
            }
        };
        Thread t=new Thread(r);
        t.start();
    }
}
