package horlly.test.two;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TimerTest{
    public static void main(String[] args) {
        ActionListener listener=new TimerPrinter();
        Timer t=new Timer(10000,listener);
        t.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);

         int a=0;

    }
}

class TimerPrinter implements ActionListener{
    public void actionPerformed(ActionEvent event){
        System.out.println("At the tone,the time is"+new Date());
//        Toolkit.getDefaultToolkit().beep();
    }
}
