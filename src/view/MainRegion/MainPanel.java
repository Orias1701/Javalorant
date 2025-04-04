package view.MainRegion;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainPanel extends JPanel {
    private final TablePanel tablePanel;
    private String currentTableName;

    public MainPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel tableNameLabel = new JLabel("No table selected");
        tableNameLabel.setFont(tableNameLabel.getFont().deriveFont(20f));
        tableNameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 0));
        headerPanel.add(tableNameLabel, BorderLayout.NORTH);

        ToolPanel toolPanel = new ToolPanel(this);
        headerPanel.add(toolPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        tablePanel = new TablePanel();
        add(tablePanel, BorderLayout.CENTER);

        this.addPropertyChangeListener("currentTableName", evt -> {
            tableNameLabel.setText(currentTableName);
            toolPanel.updateInputFields(currentTableName);
        });
    }

    public void updateTableData(String tableName) {
        this.currentTableName = tableName;
        firePropertyChange("currentTableName", null, tableName);
        tablePanel.updateTableData(tableName);
    }

    public JTable getTable() {
        return tablePanel.getTable();
    }
}