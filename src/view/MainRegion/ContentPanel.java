package view.MainRegion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class ContentPanel extends JPanel{

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ContentPanel() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setOpaque(false);
    }

    public void updateTableData(List<Map<String, String>> data) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        if (data == null || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data available for the selected table.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Map<String, String> firstRow = data.get(0);
        List<String> columnNames = new ArrayList<>(firstRow.keySet());
        tableModel.setColumnIdentifiers(columnNames.toArray());

        for (Map<String, String> row : data) {
            Object[] rowData = new Object[columnNames.size()];
            for (int i = 0; i < columnNames.size(); i++) {
                rowData[i] = row.get(columnNames.get(i));
            }
            tableModel.addRow(rowData);
        }

        table.revalidate();
        table.repaint();
    }
}