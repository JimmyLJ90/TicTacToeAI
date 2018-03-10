package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {


    public MainWindow()
    {
        Dimension d = new Dimension();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.height = 480;
        d.width = 640;
        setSize(d);
        setVisible(true);
    }
}
