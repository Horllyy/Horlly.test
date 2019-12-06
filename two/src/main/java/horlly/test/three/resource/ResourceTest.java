package horlly.test.three.resource;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ResourceTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new ResourceTestFrame();
            frame.setTitle("ResourceTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
class ResourceTestFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_LENGTH=300;

    public ResourceTestFrame()
    {

//        setSize(DEFAULT_WIDTH,DEFAULT_LENGTH);
////        URL aboutURL= getClass().getResource("good.jpg");
//
//        URL aboutURL = null;
//        try {
//            aboutURL = new File("C:\\good.jpg").toURI().toURL();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(getClass());
//        System.out.println(aboutURL);
//        Image img=new ImageIcon(aboutURL).getImage();
//        setIconImage(img);
//上面这个方法可以完成但是不完美
        setSize(DEFAULT_WIDTH,DEFAULT_LENGTH);
        URL aboutURL= getClass().getResource("good.jpg");
        System.out.println(getClass());
        System.out.println(aboutURL);
        Image img=new ImageIcon(aboutURL).getImage();
        setIconImage(img);

        JTextArea textArea=new JTextArea();
        InputStream stream=getClass().getResourceAsStream("a.txt");
        System.out.println(getClass());
        System.out.println(stream);
        try(Scanner in=new Scanner(stream,"UTF-8"))
        {
            while (in.hasNext())
                textArea.append(in.nextLine()+"\n");
        }
        add(textArea);
    }
}