import javax.swing.SwingUtilities;

import controller.LogHandler;
import view.MainUI;

public class Run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LogHandler.logInfo("Ứng dụng khởi động");
            new MainUI();
        });
    }
}