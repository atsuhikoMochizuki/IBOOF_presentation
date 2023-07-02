package fr.diginamic;


import fr.diginamic.ihm.Ihm;
import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.Logging;
import fr.diginamic.service.Cleaner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        Utils.clearConsole();
        Params.welcomePrompt();

        Utils.msgDebug("Parsing du fichier csv");
        Utils.msgResult(String.format("Parsing du fichier CSV <%s> OK", Cleaner.CSV_FILE_RELATIVE_PATH));

        Utils.msgInfo("Début du mapping JPA de la base de données");
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
             EntityManager em = emf.createEntityManager();
        ) {
            Utils.msgInfo("Mapping de la base de données OK");
            Utils.msgInfo("Connection à la base de données OK");
        } catch (IllegalStateException e) {
            Logging.LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Utils.msgInfo("Lancement de la boucle évenementielle");
        Ihm.display();
    }
}