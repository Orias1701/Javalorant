// package view.MenuRegion;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.GradientPaint;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.util.*;
// import javax.swing.*;

// import model.ApiClient;
// import view.Style;

// public class MenuPanel extends JPanel {
//     private final List<MenuButton> menuButtons = new ArrayList<>();
//     private MenuButton activeButton = null;
//     private String currentTableName;

//     public String getCurrentTableName() {
//         return currentTableName;
//     }

//     public void updateTableData(String tableName) {
//         if (tableName.equals(currentTableName)) {
//             return;
//         }
//         firePropertyChange("currentTableName", null, tableName);
    
//         Component center = ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER);
//         if (center != null) {
//             remove(center);
//         }

//         JLabel contentLabel = new JLabel("Dữ liệu của bảng: " + tableName);
//         contentLabel.setFont(contentLabel.getFont().deriveFont(16f));
//         contentLabel.setHorizontalAlignment(JLabel.CENTER);
//         add(contentLabel, BorderLayout.CENTER);

//         if (activeButton != null) {
//             activeButton.setActive(false);
//             activeButton.setBackground(new Color(0, 0, 0, 0));
//             activeButton.repaint();
//         }
//         for (MenuButton button : menuButtons) {
//             if (tableName.equals(button.getClientProperty("tableName"))) {
//                 button.setActive(true);
//                 button.setBackground(Style.MAIN_CL);
//                 activeButton = button;
//                 button.repaint();
//                 break;
//             }
//         }

//         System.out.println("Update data for table: " + tableName);
//         revalidate();
//         repaint();
//     }

//     public MenuPanel() {
//         System.out.println("MenuPanel initialized");
//         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//         setOpaque(false);
//         setPreferredSize(new Dimension(240, getHeight()));
//         setMaximumSize(new Dimension(240, getHeight()));
//         setMinimumSize(new Dimension(240, getHeight()));

//         JLabel tableLabel = new JLabel("HOME");
//         tableLabel.setAlignmentX(CENTER_ALIGNMENT);
//         tableLabel.setFont(tableLabel.getFont().deriveFont(20f));
//         tableLabel.setForeground(Style.BUTTON_CL);
//         add(tableLabel);
//         add(Box.createRigidArea(new Dimension(0, 50)));

//         Map<String, String> tableInfo = ApiClient.getTableInfo();
//         for (Map.Entry<String, String> entry : tableInfo.entrySet()) {
//             String tableName = entry.getKey();
//             String tableComment = entry.getValue();

//             MenuButton button = new MenuButton(tableComment);
//             button.putClientProperty("tableName", tableName);

//             button.setAlignmentX(CENTER_ALIGNMENT);
//             button.setPreferredSize(new Dimension(240, 50));
//             button.setMaximumSize(new Dimension(240, 50));
//             button.setMinimumSize(new Dimension(240, 50));
//             button.setFont(button.getFont().deriveFont(14f));

//             // Hover effect
//             button.addMouseListener(new java.awt.event.MouseAdapter() {
//                 @Override
//                 public void mouseEntered(java.awt.event.MouseEvent e) {
//                     if (!button.isActive()) {
//                         button.setBackground(Style.SEC_CL);
//                         button.repaint();
//                     }
//                 }

//                 @Override
//                 public void mouseExited(java.awt.event.MouseEvent e) {
//                     if (!button.isActive()) {
//                         button.setBackground(new Color(0, 0, 0, 0));
//                         button.repaint();
//                     }
//                 }
//             });

//             button.addActionListener(e -> {
//                 // String selectedTable = (String) button.getClientProperty("tableName");
//                 // updateTableData(selectedTable);

//                 for (MenuButton b : menuButtons) {
//                     b.setActive(false);
//                     b.setBackground(new Color(0, 0, 0, 0));
//                 }

//                 button.setActive(true);
//                 button.setBackground(Style.MAIN_CL);
//                 activeButton = button;
//                 button.repaint();
//             });

//             button.setBackground(new Color(0, 0, 0, 0));
//             add(button);
//             menuButtons.add(button);
//         }
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         Graphics2D g2d = (Graphics2D) g.create();

//         GradientPaint gradient = new GradientPaint(
//             0, 0, new Color(0xFB, 0xFA, 0xFC),
//             0, getHeight(), new Color(0x8E, 0x84, 0xB9)
//         );

//         g2d.setPaint(gradient);
//         g2d.fillRect(0, 0, getWidth(), getHeight());
//         g2d.dispose();
//     }
// }
package view.MenuRegion;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

import model.ApiClient;
import view.Style;

public class MenuPanel extends JPanel {
    private final List<MenuButton> menuButtons = new ArrayList<>();
    private MenuButton activeButton = null;
    private String currentTableName;
    private Rectangle highlightRect = new Rectangle(0, 0, 240, 60);
    private final Timer animationTimer;

    public String getCurrentTableName() {
        return currentTableName;
    }

    public MenuPanel() {
        System.out.println("MenuPanel initialized");
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(240, 720));

        animationTimer = new Timer(10, e -> animateHighlight());

        int y = 20;

        // HOME BUTTON
        MenuButton homeButton = createMenuButton("HOME", y);
        homeButton.setFont(homeButton.getFont().deriveFont(14f));
        homeButton.setForeground(Style.BUTTON_CL);
        homeButton.putClientProperty("tableName", "HOME");
        homeButton.setBounds(0, y, 240, 60);
        activeButton = homeButton;
        highlightRect.setLocation(0, homeButton.getY());
        add(homeButton);
        menuButtons.add(homeButton);
        y += 60;

        // Tạo các button bảng từ API
        Map<String, String> tableInfo = ApiClient.getTableInfo();
        for (Map.Entry<String, String> entry : tableInfo.entrySet()) {
            String tableName = entry.getKey();
            String tableComment = entry.getValue();
            MenuButton button = createMenuButton(tableComment, y);
            button.putClientProperty("tableName", tableName);
            add(button);
            menuButtons.add(button);
            y += 60;
        }
    }

    private MenuButton createMenuButton(String text, int y) {
        MenuButton button = new MenuButton(text);
        button.setBounds(0, y, 240, 60);
        button.setBackground(new Color(0, 0, 0, 0));
        button.setBorder(BorderFactory.createEmptyBorder());

        button.addActionListener(e -> {
            moveHighlightTo(button);
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (!button.isActive()) {
                    button.setBackground(Style.SEC_CL);
                    button.repaint();
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!button.isActive()) {
                    button.setBackground(new Color(0, 0, 0, 0));
                    button.repaint();
                }
            }
        });

        return button;
    }

    private void moveHighlightTo(MenuButton targetButton) {
        if (activeButton != null) {
            activeButton.setActive(false);
            activeButton.setBackground(new Color(0, 0, 0, 0));
            activeButton.repaint();
        }
        activeButton = targetButton;
        activeButton.setActive(true);
        activeButton.setBackground(new Color(0, 0, 0, 0));
        animationTimer.start();
    }

    private void animateHighlight() {
        if (activeButton == null) return;
    
        int targetY = activeButton.getY();
        int dy = targetY - highlightRect.y;
    
        if (Math.abs(dy) <= 1) {
            highlightRect.y = targetY;
            animationTimer.stop();
        } else {
            int step = (int)(dy * 0.16);
            if (step == 0) step = (dy > 0) ? 1 : -1;
            highlightRect.y += step;
        }
    
        repaint();
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(0xFB, 0xFA, 0xFC),
            0, getHeight(), new Color(0x8E, 0x84, 0xB9)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Style.MAIN_CL);
        g2d.fillRoundRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height, 20, 20);

        g2d.dispose();
    }
}
