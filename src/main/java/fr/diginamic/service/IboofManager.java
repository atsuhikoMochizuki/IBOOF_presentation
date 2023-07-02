package fr.diginamic.service;

import fr.diginamic.I_IboofManager;
import fr.diginamic.mochizukiTools.Utils;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class IboofManager implements I_IboofManager {
    private String pathToCsvFile;

    @Override
    public void mapCsvFileToDatabase() {
        Utils.msgTitle(String.format("Parsing et insertion en base de données du fichier %s", this.pathToCsvFile));
        ArrayList<String[]> extractedAndCleanedDatas = Cleaner.extractAndCleanDatas(this.pathToCsvFile);
        ArrayList references_attributs_Produits = Parser.referenceAllForeignAttributesOfProducts(extractedAndCleanedDatas);
        Parser.instantiationProduits(extractedAndCleanedDatas, references_attributs_Produits);
    }

    public IboofManager() {
        this.pathToCsvFile = ResourceBundle.getBundle("project").getString("project.csvfile");
    }

    public IboofManager(String pathToCsvFile) {
        this.pathToCsvFile = pathToCsvFile;
    }

    public String getPathToCsvFile() {
        return pathToCsvFile;
    }

    public void setPathToCsvFile(String pathToCsvFile) {
        this.pathToCsvFile = pathToCsvFile;
    }
}
