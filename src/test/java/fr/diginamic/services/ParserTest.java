package fr.diginamic.services;

import fr.diginamic.entities.*;
import fr.diginamic.utilitaires.mochizukiTools.Utils;
import fr.diginamic.services.subSystems.Cleaner;
import fr.diginamic.services.subSystems.Parser;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class ParserTest extends TestCase {

    @Test
    public void test_parseFile() {
        Utils.msgInfo("Test de la fonction parseFile");
        ArrayList listeGenerale = (ArrayList) Parser.parseFile(Cleaner.CSV_FILE_RELATIVE_PATH);
        Utils.msgResult("Affichage des éléments référencés en mémoire en vue du mapping");

        Utils.msgResult("Liste des categories référencées");
        Map<String, Categorie> categories = (Map) listeGenerale.get(0);
        categories.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des marques référencées");
        Map<String, Marque> marques = (Map) listeGenerale.get(1);
        marques.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des produits référencés");
        Map<String, Produit> produits = (Map) listeGenerale.get(2);
        produits.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des nutriscores référencés");
        Map<String, Nutriscore> nutriscores = (Map) listeGenerale.get(3);
        nutriscores.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des ingrédients référencées");
        Map<String, Ingredient> ingredients = (Map) listeGenerale.get(4);
        ingredients.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des allergènes référencés");
        Map<String, Allergene> allergenes = (Map) listeGenerale.get(5);
        allergenes.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });

        Utils.msgResult("Liste des additifs référencés");
        Map<String, Additif> additifs = (Map) listeGenerale.get(6);
        additifs.forEach((key, value) -> {
            System.out.println("Key=" + key + ", Value=" + value);
        });
        Utils.msgResult("Test de la fonction parseFile OK");
    }

    @Test
    public void test_instantiateProduit() {

        Utils.msgTitle("Test de la fonction instantiationProduit");
        ArrayList<String[]> rowsToParse = Cleaner.extractAndCleanDatas(Cleaner.CSV_FILE_RELATIVE_PATH);
        ArrayList references_attributs_Produits = Parser.referenceAllForeignAttributesOfProducts(rowsToParse);
        Parser.instantiationProduits(rowsToParse, references_attributs_Produits);


    }

    @Test
    public void creationInstanceAdditif() {
    }

    @Test
    public void creationInstanceAllergene() {
    }

    @Test
    public void creationInstanceIngredient() {
    }

    @Test
    public void creationInstanceNutriscore() {
    }

    @Test
    public void creationInstanceMarque() {
    }

    @Test
    public void creationInstanceProduit() {
    }

    @Test
    public void creationInstanceCategorie() {
    }
}