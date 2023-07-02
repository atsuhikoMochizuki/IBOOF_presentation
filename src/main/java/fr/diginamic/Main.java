package fr.diginamic;


import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.IboofManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        Utils.clearConsole();
        Params.welcomePrompt();

        String fileTestPath = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
        I_IboofManager iboofManager = new IboofManager(fileTestPath);
        iboofManager.mapCsvFileToDatabase();

        //Ihm.display();
    }
}