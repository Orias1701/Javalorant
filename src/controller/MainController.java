package controller;

import model.ApiClient;
import view.MainRegion.ContentPanel;
import view.MenuRegion.MenuPanel;
import model.ApiClient.TableDataResult;
public class MainController {
    private final ContentPanel contentPanel;
    private final MenuPanel menuPanel;

    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    public MainController(ContentPanel contentPanel, MenuPanel menuPanel) {
        if (contentPanel == null || menuPanel == null) {
            throw new IllegalArgumentException("ContentPanel and MenuPanel must not be null");
        }
        this.contentPanel = contentPanel;
        this.menuPanel = menuPanel;

        menuPanel.setTableSelectionListener((tableName, tableComment) -> {
            if (tableName != null && !tableName.isEmpty()) {
                TableDataResult result = ApiClient.getTableData(tableName);
                contentPanel.updateTableData(result.data, result.columnComments, result.keyColumn, tableName);            
            } else {
                System.err.println("Invalid table name received");
            }
        });
    }
}