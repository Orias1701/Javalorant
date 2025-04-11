package controller;

import model.ApiClient;
import view.MainRegion.ContentPanel;
import view.MenuRegion.MenuPanel;

import java.util.List;
import java.util.Map;

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
        this.contentPanel = contentPanel;
        this.menuPanel = menuPanel;

        menuPanel.setTableSelectionListener(tableName -> {
            List<Map<String, String>> data = ApiClient.getTableData(tableName);
            contentPanel.updateTableData(data);
        });
    }
}