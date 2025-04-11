package view;

import controller.SimpleServer;
import java.awt.BorderLayout;
import java.awt.Color;
// import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
// import javax.swing.JLabel;

import view.FooterRegion.FooterPanel;
import view.HeaderRegion.HeaderPanel;
// import view.MainRegion.ContentPanel;
import view.MenuRegion.MenuPanel;
public class MainUI extends JFrame {

    
    public MainUI() {
        setTitle("Order Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Style.WIN_WIDTH, 810);
        setMinimumSize(new Dimension(900, 700));
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.decode("#FFFFFF"));

        SimpleServer.start();

        add(new LoginPanel(this));

        setVisible(true);
    }

    public void showMainInterface() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new FooterPanel(), BorderLayout.SOUTH);
        add(new MenuPanel(), BorderLayout.WEST);
        revalidate();
        repaint();
    }
}
