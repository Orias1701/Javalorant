package view.MenuRegion;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.ApiClient;
import view.MainRegion.MainPanel;

public class MenuPanel extends JPanel {

    public MenuPanel(final MainPanel MainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        setBackground(Color.decode("#FFFFFF"));

        JLabel tableLabel = new JLabel("Table");
        tableLabel.setAlignmentX(CENTER_ALIGNMENT);
        tableLabel.setFont(tableLabel.getFont().deriveFont(20f));
        tableLabel.setForeground(Color.decode("#FF9500"));
        add(tableLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));

        Map<String, String> tableInfo = ApiClient.getTableInfo();
        for (Map.Entry<String, String> entry : tableInfo.entrySet()) {
            String tableName = entry.getKey();
            String tableComment = entry.getValue();

            MenuButton button = new MenuButton(tableComment);
            button.setMaximumSize(new Dimension(130, 50));
            button.setPreferredSize(new Dimension(130, 50));
            button.setMinimumSize(new Dimension(130, 50));
            button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFont(button.getFont().deriveFont(15f));
            button.setOpaque(true);
            button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.decode("#FF9500"), 1, true),
                new javax.swing.border.EmptyBorder(8, 12, 8, 12)
            ));

            button.addActionListener(e -> MainPanel.updateTableData(tableName));

            add(button);
            add(Box.createRigidArea(new Dimension(0, 7)));
        }
    }
}

