package view;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Style {
    // Color
    public static final Color LIGHT_CL = Color.WHITE;
    public static final Color DARK_CL = Color.BLACK;
    public static final Color GREEN = Color.decode("#00A251");
    public static final Color RED = Color.decode("#C90000");
    public static final Color GRAY_CL = Color.decode("#444444");
    public static final Color MAIN_CL = Color.decode("#9E88FF");
    public static final Color SEC_CL = Color.decode("#D7CEFF");
    public static final Color TRD_CL = Color.decode("#7C68D4");
    public static final Color ACT_CL = Color.decode("#6430AD");
    public static final Color BUTTON_CL = Color.decode("#43327C");
    public static final Color NO_CL = new Color(0, 0, 0, 0);

    // Font
    public static final Font MONS_12 = new Font("Montserrat", Font.BOLD, 12);
    public static final Font MONS_14 = new Font("Montserrat", Font.BOLD, 14);
    public static final Font MONS_16 = new Font("Montserrat", Font.BOLD, 16);
    public static final Font MONS_20 = new Font("Montserrat", Font.BOLD, 20);
    public static final Font MONS_24 = new Font("Montserrat", Font.BOLD, 24);
    public static final Font MONS_28 = new Font("Montserrat", Font.BOLD, 28);
    public static final Font ROB_B14 = new Font("Roboto", Font.BOLD, 14);
    public static final Font ROB_14 = new Font("Roboto", Font.PLAIN, 14);
    public static final Font ROB_16 = new Font("Roboto", Font.BOLD, 16);
    public static final Font ROB_20 = new Font("Roboto", Font.BOLD, 20);
    public static final Font ROB_24 = new Font("Roboto", Font.BOLD, 24);
    public static final Font ROB_28 = new Font("Roboto", Font.BOLD, 28);
    public static final Font NOR_12 = new Font("Arial", Font.BOLD, 12);
    public static final Font NOR_14 = new Font("Arial", Font.BOLD, 14);
    public static final Font NOR_16 = new Font("Arial", Font.BOLD, 16);
    public static final Font NOR_20 = new Font("Arial", Font.BOLD, 20);
    public static final Font NOR_24 = new Font("Arial", Font.BOLD, 24);
    public static final Font NOR_28 = new Font("Arial", Font.BOLD, 28);

    // Size
    public static final Dimension MENU_WIDTH = new Dimension(240, 0);
    public static final Dimension BUTTON_SIZE = new Dimension(240, 50);

    // Padding & spacing
    public static final int WIN_WIDTH = 1440;
    public static final int WIN_HEIGHT = 900;
    public static final int MENU_TOP_PADDING = 20;
    public static final int MENU_BOTTOM_PADDING = 10;
    public static final int BUTTON_SPACING = 7;

    // Border
    public static final Border BORDER_L20 = BorderFactory.createEmptyBorder(0, 20, 0, 0);
}
