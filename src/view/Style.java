package view;

import java.awt.*;

public class Style {
    // Color
    public static final Color LIGHT_CL = Color.WHITE;
    public static final Color DARK_CL = Color.BLACK;
    public static final Color MAIN_CL = Color.decode("#9E88FF");
    public static final Color SEC_CL = Color.decode("#D7CEFF");
    public static final Color TRD_CL = Color.decode("#7C68D4");
    public static final Color ACT_CL = Color.decode("#6430AD");
    public static final Color BUTTON_CL = Color.decode("#43327C");
    public static final Color NO_CL = new Color(0, 0, 0, 0);

    // Font
    public static final Font TITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font BUTTON = new Font("Segoe UI", Font.PLAIN, 14);

    // Size
    public static final Dimension MENU_WIDTH = new Dimension(240, 0);
    public static final Dimension BUTTON_SIZE = new Dimension(240, 50);

    // Padding & spacing
    public static final int WIN_WIDTH = 1440;
    public static final int WIN_HEIGHT = 900;
    public static final int MENU_TOP_PADDING = 20;
    public static final int MENU_BOTTOM_PADDING = 10;
    public static final int BUTTON_SPACING = 7;
}
