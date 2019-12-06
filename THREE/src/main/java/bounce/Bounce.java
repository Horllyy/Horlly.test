package bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Bounce{
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
    public static final int DELAY=3;

    public BounceFrame(){
        setTitle("Bounce");
        comp=new BallComponent();
        add(comp,BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        addButton(buttonPanel,"Start",event->addBall());
        addButton(buttonPanel,"Close",event->System.exit(0));
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall(){
        try {
            Ball ball = new Ball();
            comp.add(ball);

            for (int i = 0; i < STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        }catch (InterruptedException e)
        {
        }
    }
}
/*这个程序基本思路我总结了一下 大概是这样：
一个小球在窗口界面上活动 分为这样几个部分：
  1.ball 单纯的小球 主要就是静态小球 具有坐标属性
  2.ballComponent 装载，负责，或者说是将小球的每个状态连贯起来，负责让感觉让小球动起来的小球组件
  （注：这是单线程 只能有一个小球 所以是小球的多个状态。多线程里可以有多个小球分别行动 所以分别是是多个小球的多个状态）
  3.JPanel 普通小框架，普通窗口，不能输出呈现，负责将一些按钮等组件装进来 再连同它自己贴到JFrame上去
  4.JFrame 底层框架，顶级窗口，最大最基本的那个结构，负责输出，呈现，集成各部分组件和小框架
  （JFrame可以直接贴组件component 但是按钮，标签，文本框等要经过第三方JPanel）。
*/

//这个程序和多线程小球程序只有主程序最后一段有些微不同 多线程小球多了一个lambda表达式和增加新线程并运行的语句