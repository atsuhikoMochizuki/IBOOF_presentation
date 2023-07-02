package fr.diginamic.mochizukiTools;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.ResourceBundle;

public class Params {
    public static ResourceBundle project = ResourceBundle.getBundle ("project");
    public static final String LOGO = project.getString ("project.title");
    public static final String DESCRIPTION = project.getString ("project.description");
    public static final String MAJOR_VERSION = project.getString ("project.majorVersion");
    public static final String MINOR_VERSION = project.getString ("project.minorVersion");
    public static final String REVISION_VERSION = project.getString ("project.revisionVersion");
    public static final String AUTHOR = project.getString ("project.author");
    public static final String DATE = project.getString ("project.date");
    public static final String URL = project.getString ("project.url");

    public static void welcomePrompt() {
        String logo;
        Utils.clearConsole ();
        try {
            logo = FigletFont.convertOneLine (LOGO);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        System.out.println (Utils.Colors.ANSI_BLUE + logo + Utils.Colors.ANSI_RESET);
        System.out.println (Utils.Colors.ANSI_GREEN + LOGO.toUpperCase () + " v" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + Utils.Colors.ANSI_RESET);
        System.out.println (Utils.Colors.ANSI_PURPLE + "By " + AUTHOR + " - " + DATE + Utils.Colors.ANSI_RESET);
        System.out.println (Utils.Colors.ANSI_BLUE + "Repository : " + URL + Utils.Colors.ANSI_RESET);
        System.out.println (DESCRIPTION);
        System.out.println ("=====================================================================================================");
    }
}

