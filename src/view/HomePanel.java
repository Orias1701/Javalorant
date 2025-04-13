package view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private Image backgroundImage;

    public HomePanel() {
        setLayout(null);
        loadBackgroundImage();
    }

    private void loadBackgroundImage() {
        try {
            java.net.URL imgURL = getClass().getResource("Manager.jpg");
            if (imgURL == null) {
                throw new IllegalArgumentException("Manager.jpg not found");
            }
            backgroundImage = new ImageIcon(imgURL).getImage();
        } catch (Exception e) {
            System.err.println("Lỗi tải ảnh: " + e.getMessage());
            backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imgWidth = backgroundImage.getWidth(this);
            int imgHeight = backgroundImage.getHeight(this);

            if (imgWidth <= 0 || imgHeight <= 0) {
                return;
            }

            double imgAspectRatio = (double) imgWidth / imgHeight;
            double panelAspectRatio = (double) panelWidth / panelHeight;

            int scaledWidth, scaledHeight;
            int x, y;

            if (imgAspectRatio > panelAspectRatio) {

                scaledHeight = panelHeight;
                scaledWidth = (int) (panelHeight * imgAspectRatio);
                x = (panelWidth - scaledWidth) / 2;
                y = 0;
            } else {

                scaledWidth = panelWidth;
                scaledHeight = (int) (panelWidth / imgAspectRatio);
                x = 0;
                y = (panelHeight - scaledHeight) / 2;
            }

            g.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, this);
        }
    }
}