package fr.diginamic.service;

import org.junit.Test;

import java.util.ResourceBundle;

public class IboofManagerTest {
    @Test
    public void mapCsvFileToDatabase() {
        String fileTestPath = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
        I_IboofManager iboofManager = new IboofManager(fileTestPath);
        iboofManager.mapCsvFileToDatabase();
    }



}