package fr.diginamic.service;

import fr.diginamic.I_IboofManager;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class IboofManagerTest {
    @Test
    public void mapCsvFileToDatabase() {
        String fileTestPath = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
        I_IboofManager iboofManager = new IboofManager(fileTestPath);
        iboofManager.mapCsvFileToDatabase();
    }
}