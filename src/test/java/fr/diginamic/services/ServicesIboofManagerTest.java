package fr.diginamic.services;

import fr.diginamic.services.subSystems.I_IboofManager;
import fr.diginamic.services.subSystems.Services_IboofManager;
import org.junit.Test;

import java.util.ResourceBundle;

public class ServicesIboofManagerTest {
    @Test
    public void mapCsvFileToDatabase() {
        String fileTestPath = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
        I_IboofManager iboofManager = new Services_IboofManager(fileTestPath);
        iboofManager.mapCsvFileToDatabase();
    }



}