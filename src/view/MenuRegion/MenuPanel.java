package view.MenuRegion;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

import controller.LogHandler;
import model.ApiClient;
import view.Style;

public class MenuPanel extends JPanel {
    private final List<MenuButton> menuButtons = new ArrayList<>();
    private MenuButton activeButton = null;
    private String currentTableName;
    private Rectangle highlightRect = new Rectangle(0, 0, 240, 60);
    private final Timer animationTimer;
    private TableSelectionListener tableSelectionListener;

    // Interface cho listener
    public interface TableSelectionListener {
        void onTableSelected(String tableName, String tableComment);
    }
    
    public void setTableSelectionListener(TableSelectionListener listener) {
        this.tableSelectionListener = listener;
    }

    public String getCurrentTableName() {
        return currentTableName;
    }

    public MenuPanel() {
        // Sử dụng BoxLayout thay vì null layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setPreferredSize(new Dimension(240, 720));
        animationTimer = new Timer(0, e -> animateHighlight());

        // Home button
        MenuButton homeButton = createMenuButton("TRANG CHỦ");
        homeButton.setFont(Style.MONS_16);    
        homeButton.setForeground(Style.LIGHT_CL);
        homeButton.putClientProperty("tableName", "HOME");
        homeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        activeButton = homeButton;
        currentTableName = "HOME";
        highlightRect.setLocation(0, 20); // Vị trí ban đầu của highlight
        add(homeButton);
        menuButtons.add(homeButton);
        add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách

        LogHandler.logInfo("MenuPanel initialized");
    }

    public void refreshTableList() {
        // Xóa các nút bảng cũ (nếu có), giữ lại nút TRANG CHỦ
        for (int i = menuButtons.size() - 1; i >= 0; i--) {
            MenuButton button = menuButtons.get(i);
            if (!"HOME".equals(button.getClientProperty("tableName"))) {
                remove(button);
                menuButtons.remove(i);
            }
        }

        // Tải danh sách bảng từ API
        Map<String, String> tableInfo = ApiClient.getTableInfo();
        if (tableInfo.containsKey("error")) {
            LogHandler.logError("Không thể tải danh sách bảng: " + tableInfo.get("error"));
            return;
        }

        for (Map.Entry<String, String> entry : tableInfo.entrySet()) {
            String tableName = entry.getKey();
            String tableComment = entry.getValue();
            MenuButton button = createMenuButton(tableComment);
            button.putClientProperty("tableName", tableName);
            add(button);
            menuButtons.add(button);
            add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
        }

        revalidate();
        repaint();
    }

    // Tạo một menu button
    private MenuButton createMenuButton(String text) {
        MenuButton button = new MenuButton(text);
        button.setMaximumSize(new Dimension(240, 60));
        button.setFont(Style.MONS_16);    
        button.setForeground(Style.GRAY_CL);
        button.setBackground(Style.NO_CL);
        button.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        button.setHorizontalAlignment(SwingConstants.LEFT);
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
                    button.setBackground(Style.NO_CL);
                    button.setForeground(Style.GRAY_CL);
                    button.repaint();
                }
            }
        });

        return button;
    }

    // Highlight nút được chọn
    private void moveHighlightTo(MenuButton targetButton) {
        if (activeButton != null) {
            activeButton.setActive(false);
            activeButton.setBackground(Style.NO_CL);
            activeButton.setForeground(Style.GRAY_CL);
            activeButton.repaint();
        }
        activeButton = targetButton;
        activeButton.setActive(true);
        activeButton.setBackground(Style.NO_CL);
        activeButton.setForeground(Style.LIGHT_CL);
        currentTableName = (String) activeButton.getClientProperty("tableName");
        String currentTableComment = activeButton.getText();
        LogHandler.logInfo("Tên bảng: " + currentTableName + ", Chú thích: " + currentTableComment);
        if (tableSelectionListener != null && !"HOME".equals(currentTableName)) {
            tableSelectionListener.onTableSelected(currentTableName, currentTableComment);
        }
        animationTimer.start();
    }

    // Hiệu ứng di chuyển highlight
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

    // Vẽ nền và highlight
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
        g2d.fillRoundRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height, 7, 10);
        g2d.dispose();
    }
}