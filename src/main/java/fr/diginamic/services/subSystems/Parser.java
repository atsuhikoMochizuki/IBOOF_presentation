package fr.diginamic.services.subSystems;

import fr.diginamic.entities.*;
import fr.diginamic.utilitaires.mochizukiTools.Logging;
import fr.diginamic.utilitaires.mochizukiTools.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import me.tongfei.progressbar.ProgressBar;
import org.slf4j.Logger;

import java.util.*;

public class Parser {
    private static final Logger LOG = Logging.LOG;

    public static final int CATEGORIE = 0;
    public static final int MARQUE = 1;
    public static final int PRODUIT = 2;
    public static final int NUTRITIONGRADEFR = 3;
    public static final int INGREDIENTS = 4;
    public static final int ENERGIE100G = 5;
    public static final int GRAISSE100G = 6;
    public static final int SUCRES100G = 7;
    public static final int FIBRES100G = 8;
    public static final int PROTEINES100G = 9;
    public static final int SEL100G = 10;
    public static final int VITA100G = 11;
    public static final int VITD100G = 12;
    public static final int VITE100G = 13;
    public static final int VITK100G = 14;
    public static final int VITC100G = 15;
    public static final int VITB1100G = 16;
    public static final int VITB2100G = 17;
    public static final int VITPP100G = 18;
    public static final int VITB6100G = 19;
    public static final int VITB9100G = 20;
    public static final int VITB12100G = 21;
    public static final int CALCIUM100G = 22;
    public static final int MAGNESIUM100G = 23;
    public static final int IRON100G = 24;
    public static final int FER100G = 25;
    public static final int BETACAROTENE100G = 26;
    public static final int PRESENCEHUILEPALME = 27;
    public static final int ALLERGENES = 28;
    public static final int ADDITIFS = 29;
    public static final int NBRE_LIGNES_IN_FILE = 13434;

    public static ArrayList parseFile(String csvFile_path) {
        Utils.msgInfo("Extraction, nettoyage et référencement en mémoire des élements du fichier" + " csv de OpenFoodFacts en vue de l'insertion en base de données");
        ArrayList<String[]> cleanedDatas = Cleaner.extractAndCleanDatas(csvFile_path);
        ArrayList refsOfDatas = referenceAllForeignAttributesOfProducts(cleanedDatas);
        return refsOfDatas;
    }

    /**
     * @param extractedAndCleanedLines
     * @return Liste globale de toutes les instances des attributs étrangers aux produits (dans d'autres tables)
     */
    public static ArrayList referenceAllForeignAttributesOfProducts(ArrayList<String[]> extractedAndCleanedLines) {
        //La fonction récupère en paramètres les lignes nettoyées et splittées du fichier CSV

        //La liste globale de référence regroupera toutes les instances triées de ces lignes fournies
        //en paramètres, triées dans des Maps.
        ArrayList globalList_ReferencedDatas = new ArrayList();


        //Pour chaque entité de notre MCD, un hashMap est associé
        Map<String, Categorie> refs_categories = new HashMap<>();
        Map<String, Marque> refs_marques = new HashMap<>();
        Map<String, Produit> refs_produits = new HashMap<>();
        Map<String, Nutriscore> refs_nutriscore = new HashMap<>();
        Map<String, Ingredient> refs_ingredients = new HashMap<>();
        Map<String, Allergene> refs_allergenes = new HashMap<>();
        Map<String, Additif> refs_additifs = new HashMap<>();

        try (ProgressBar progressBar = new ProgressBar((String.format("Analyse des %d lignes du fichier nettoyé", extractedAndCleanedLines.size())), extractedAndCleanedLines.size())) {
            int lineNbrInFile = 0;
            Iterator<String[]> iter = extractedAndCleanedLines.iterator();

            //Itération sur chaque ligne
            while (iter.hasNext()) {
                lineNbrInFile++;

                //Récupération de la ligne
                String[] rowToParse = iter.next();
                // Itération sur chaque colonne de la ligne
                for (int colIndex = 0; colIndex < rowToParse.length; colIndex++) {
                    switch (colIndex) {

                        //Chaque valeur de colonne donne lieu à une instance, et
                        //enregistrement dans le map dédié.
                        //Cette instance est référencée par la valeur de la colonne,
                        //Ce qui permettre de récupérer facilement l'instance utlréieurement.
                        //Tout ceci est effectué si et seulement si l'instance n'existe pas
                        //dans le map

                        case CATEGORIE:
                            if (!refs_categories.containsKey(rowToParse[CATEGORIE])) {
                                Categorie categorieToAdd = new Categorie(rowToParse[CATEGORIE]);
                                refs_categories.put(rowToParse[CATEGORIE], categorieToAdd);
                            }
                            break;

                        case MARQUE:
                            //Un produit peut appartenir à plusieurs marques
                            //Il faut splitter la chaine pour enregistrer chaque
                            //marque dans le HashMap dédié
                            String[] splittedMarque = rowToParse[MARQUE].trim().split(",");
                            for (int j = 0; j < splittedMarque.length; j++) {
                                if (splittedMarque[j] == null) {
                                } else if (splittedMarque[j].contentEquals("")) {
                                } else if (splittedMarque[j].contentEquals(" ")) {
                                } else {
                                    if (!refs_marques.containsKey(splittedMarque[j])) {
                                        Marque marqueToAdd = new Marque(splittedMarque[j]);
                                        refs_marques.put(splittedMarque[j], marqueToAdd);
                                    }
                                }
                            }
                            break;

                        case PRODUIT:
                            if (!refs_produits.containsKey(rowToParse[PRODUIT])) {
                                Produit produitToAdd = new Produit(rowToParse[PRODUIT]);
                                refs_produits.put(rowToParse[PRODUIT], produitToAdd);
                            }
                            break;

                        case NUTRITIONGRADEFR:
                            if (!refs_nutriscore.containsKey(rowToParse[NUTRITIONGRADEFR])) {
                                Nutriscore nutriscoreToAdd = new Nutriscore(rowToParse[NUTRITIONGRADEFR]);
                                refs_nutriscore.put(rowToParse[NUTRITIONGRADEFR], nutriscoreToAdd);
                            }
                            break;

                        case INGREDIENTS:
                            String[] splittedIngredient = rowToParse[INGREDIENTS].split(",");
                            for (int j = 0; j < splittedIngredient.length; j++) {
                                if (splittedIngredient[j] == null) {
                                } else if (splittedIngredient[j].contentEquals("")) {
                                } else if (splittedIngredient[j].contentEquals(" ")) {
                                } else {
                                    if (!refs_ingredients.containsKey(splittedIngredient[j])) {
                                        Ingredient ingredientToAdd = new Ingredient(splittedIngredient[j]);
                                        refs_ingredients.put(splittedIngredient[j], ingredientToAdd);
                                    }
                                }
                            }
                            break;

                        case ALLERGENES:
                            String[] splittedAllergenes = rowToParse[ALLERGENES].split(",");
                            for (int j = 0; j < splittedAllergenes.length; j++) {
                                if (splittedAllergenes[j] == null) {
                                } else if (splittedAllergenes[j].contentEquals("")) {
                                } else if (splittedAllergenes[j].contentEquals(" ")) {
                                } else {
                                    if (!refs_allergenes.containsKey(splittedAllergenes[j])) {
                                        Allergene allergeneToAdd = new Allergene(splittedAllergenes[j]);
                                        refs_allergenes.put(splittedAllergenes[j], allergeneToAdd);
                                    }
                                }
                            }
                            break;

                        case ADDITIFS:
                            String[] splittedAdditifs = rowToParse[ADDITIFS].split(",");
                            for (int j = 0; j < splittedAdditifs.length; j++) {
                                if (splittedAdditifs[j] == null) {
                                } else if (splittedAdditifs[j].contentEquals("")) {
                                } else if (splittedAdditifs[j].contentEquals(" ")) {
                                } else {
                                    if (!refs_additifs.containsKey(splittedAdditifs[j])) {
                                        Additif additifToAdd = new Additif(splittedAdditifs[j]);
                                        refs_additifs.put(splittedAdditifs[j], additifToAdd);
                                    }
                                }
                            }
                            break;
                    }
                }
                progressBar.step();
            }
        }

        //Toutes les instances, sauf les produits, sont à présents référencées dans des maps.
        //Intégration des maps dans la liste grobale
        try (ProgressBar progressBar2 = new ProgressBar("Mise à jour de la liste de référencement", 7)) {
            globalList_ReferencedDatas.add(refs_categories);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_marques);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_produits);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_nutriscore);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_ingredients);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_allergenes);
            progressBar2.step();

            globalList_ReferencedDatas.add(refs_additifs);
            progressBar2.step();
        }
        return globalList_ReferencedDatas;
    }


    /**
     * méthode d'instantiation de touts les produits.
     * A partir d'une liste extraite et nettoyée du fichier CSV, le produit est instancié.
     * Les instances de ces attributs sont pour cela récupérés dans la liste globale de référencement
     *
     * @param cleanedLines
     * @param globalListForeignAtributes
     */
    public static void instantiationProduits(ArrayList<String[]> cleanedLines, ArrayList globalListForeignAtributes) {
        try (ProgressBar progressBar = new ProgressBar((String.format("Insertion de %d produits en base de données", cleanedLines.size())), cleanedLines.size())) {
            //Extraction des différents maps de référencement d'instances contenus dans la liste globale
            Map<String, Categorie> refs_categories = (Map<String, Categorie>) globalListForeignAtributes.get(0);
            Map<String, Marque> refs_marques = (Map<String, Marque>) globalListForeignAtributes.get(1);
            Map<String, Produit> refs_produits = (Map<String, Produit>) globalListForeignAtributes.get(2);
            Map<String, Nutriscore> refs_nutriscore = (Map<String, Nutriscore>) globalListForeignAtributes.get(3);
            Map<String, Ingredient> refs_ingredients = (Map<String, Ingredient>) globalListForeignAtributes.get(4);
            Map<String, Allergene> refs_allergenes = (Map<String, Allergene>) globalListForeignAtributes.get(5);
            Map<String, Additif> refs_additifs = (Map<String, Additif>) globalListForeignAtributes.get(6);

            //Nouvelle itération sur la liste contenant les lignes extraites et nettoyées du fichier csv
            Iterator<String[]> iter = cleanedLines.iterator();
            try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
                 EntityManager em = emf.createEntityManager();
            ) {
                while (iter.hasNext()) {

                    //Création d'un entityManagerFactory à partir de la description
                    //notifiée dans le fichier "persistence.xml

                    //Début de la transaction dédiée à la persistence du produit traité
                    em.getTransaction().begin();

                    Produit produitToPersist = new Produit();

                    //Récupération de la ligne splitée
                    String[] rowToParse = iter.next();

                    //Itération sur chacune des valeurs de colonne
                    for (int colIndex = 0; colIndex < rowToParse.length; colIndex++) {
                        switch (colIndex) {

                            //Pour chacune des colonnes, la valeur décrite dans la ligne
                            //nous permet d'accéder à l'instance désirée stockée dans le map
                            case CATEGORIE:
                                Categorie catToAssociate = refs_categories.get(rowToParse[CATEGORIE]);
                                if (catToAssociate == null) {
                                    throw new RuntimeException();
                                } else {
                                    //L'instance est persistée en base, puis associée au produit
                                    em.persist(catToAssociate);
                                    produitToPersist.setCategorie(catToAssociate);
                                }
                                break;

                            case MARQUE:
                                //Un produit peut être associé à plusieurs marques, c'est pourquoi
                                //on re-splite la valeur.
                                //Toutes instances des marques du produit sont
                                //persistées en base puis placées dans un set
                                Set<Marque> marquesToAssociate = new HashSet<>();
                                String[] splittedMarque = rowToParse[MARQUE].split(",");
                                for (int j = 0; j < splittedMarque.length; j++) {
                                    if (splittedMarque[j] == null) {
                                    } else if (splittedMarque[j].contentEquals("")) {
                                    } else if (splittedMarque[j].contentEquals(" ")) {
                                    } else {
                                        try {
                                            Marque marqueToAssociate = refs_marques.get(splittedMarque[j]);
                                            em.persist(marqueToAssociate);
                                            marquesToAssociate.add(marqueToAssociate);
                                        } catch (Exception e) {
                                            Utils.msgError(e.getMessage());

                                        }
                                    }
                                }
                                //Affectation de l'ensemble des marques du set au produit
                                produitToPersist.setMarques(marquesToAssociate);
                                break;

                            case PRODUIT:
                                //On ne récupère ici que le nom du produit.
                                //Ce dernier est instantié en fin d'itération
                                Produit produitToAssociate = refs_produits.get(rowToParse[PRODUIT]);
                                if (produitToAssociate == null) {
                                    throw new RuntimeException();
                                } else
                                    produitToPersist.setNom_produit(rowToParse[PRODUIT]);
                                break;

                            case NUTRITIONGRADEFR:
                                Nutriscore nutriscoreToAssociate = refs_nutriscore.get(rowToParse[NUTRITIONGRADEFR]);
                                if (nutriscoreToAssociate == null) {
                                    throw new RuntimeException();
                                } else {
                                    em.persist(nutriscoreToAssociate);
                                    produitToPersist.setNutriscore(nutriscoreToAssociate);
                                }
                                break;

                            case INGREDIENTS:
                                Set<Ingredient> ingredientsToAssociate = new HashSet<>();
                                String[] splittedIngredients = rowToParse[INGREDIENTS].split(",");
                                for (int j = 0; j < splittedIngredients.length; j++) {
                                    if (splittedIngredients[j] == null) {
                                    } else if (splittedIngredients[j].contentEquals("")) {
                                    } else if (splittedIngredients[j].contentEquals(" ")) {
                                    } else {
                                        try {
                                            Ingredient ingredientToAssociate = refs_ingredients.get(splittedIngredients[j]);
                                            em.persist(ingredientToAssociate);
                                            ingredientsToAssociate.add(ingredientToAssociate);
                                        } catch (Exception e) {
                                            Utils.msgError(e.getMessage());
                                        }
                                    }
                                }
                                produitToPersist.setIngredients(ingredientsToAssociate);
                                break;

                            case ENERGIE100G:
                                //Filtrage des différentes anomalies
                                if (rowToParse[ENERGIE100G] == null) {
                                } else if (rowToParse[ENERGIE100G].contentEquals("")) {
                                } else if (rowToParse[ENERGIE100G].contentEquals(" ")) {
                                } else {
                                    double energie100g;
                                    try {
                                        energie100g = Double.parseDouble(rowToParse[ENERGIE100G]);
                                    } catch (Exception ignore) {
                                        //Si, malgré le filtrage, la valeur n'est pas convertible
                                        //en double, on la passe à zéro.
                                        energie100g = 0;
                                    }
                                    produitToPersist.setEnergiePour100g(energie100g);
                                }
                                break;

                            case GRAISSE100G:
                                if (rowToParse[GRAISSE100G] == null) {
                                } else if (rowToParse[GRAISSE100G].contentEquals("")) {
                                } else if (rowToParse[GRAISSE100G].contentEquals(" ")) {
                                } else {
                                    double graisse100g;
                                    try {
                                        graisse100g = Double.parseDouble(rowToParse[GRAISSE100G]);
                                    } catch (Exception ignore) {
                                        graisse100g = 0;
                                    }
                                    produitToPersist.setGraisse100g(graisse100g);
                                }
                                break;

                            case SUCRES100G:
                                if (rowToParse[SUCRES100G] == null) {
                                } else if (rowToParse[SUCRES100G].contentEquals("")) {
                                } else if (rowToParse[SUCRES100G].contentEquals(" ")) {
                                } else {
                                    double sucre100g;
                                    try {
                                        sucre100g = Double.parseDouble(rowToParse[SUCRES100G]);
                                    } catch (Exception ignore) {
                                        sucre100g = 0;
                                    }
                                    produitToPersist.setSucres100g(sucre100g);
                                }
                                break;

                            case FIBRES100G:
                                if (rowToParse[FIBRES100G] == null) {
                                } else if (rowToParse[FIBRES100G].contentEquals("")) {
                                } else if (rowToParse[FIBRES100G].contentEquals(" ")) {
                                } else {
                                    double fibres100g;
                                    try {
                                        fibres100g = Double.parseDouble(rowToParse[FIBRES100G]);
                                    } catch (Exception ignore) {
                                        fibres100g = 0;
                                    }
                                    produitToPersist.setFibres100g(fibres100g);
                                }
                                break;

                            case PROTEINES100G:
                                if (rowToParse[PROTEINES100G] == null) {
                                } else if (rowToParse[PROTEINES100G].contentEquals("")) {
                                } else if (rowToParse[PROTEINES100G].contentEquals(" ")) {
                                } else {
                                    double proteines100G;
                                    try {
                                        proteines100G = Double.parseDouble(rowToParse[PROTEINES100G]);
                                    } catch (Exception ignore) {
                                        proteines100G = 0;
                                    }
                                    produitToPersist.setProteines100g(proteines100G);
                                }
                                break;

                            case SEL100G:
                                if (rowToParse[SEL100G] == null) {
                                } else if (rowToParse[SEL100G].contentEquals("")) {
                                } else if (rowToParse[SEL100G].contentEquals(" ")) {
                                } else {
                                    double sel100g;
                                    try {
                                        sel100g = Double.parseDouble(rowToParse[SEL100G]);
                                    } catch (Exception ignore) {
                                        sel100g = 0;
                                    }
                                    produitToPersist.setSel100g(sel100g);
                                }
                                break;

                            case VITA100G:
                                if (rowToParse[VITA100G] == null) {
                                } else if (rowToParse[VITA100G].contentEquals("")) {
                                } else if (rowToParse[VITA100G].contentEquals(" ")) {
                                } else {
                                    double vita100g;
                                    try {
                                        vita100g = Double.parseDouble(rowToParse[VITA100G]);
                                    } catch (Exception ignore) {
                                        vita100g = 0;
                                    }
                                    produitToPersist.setVita100g(vita100g);
                                }
                                break;

                            case VITD100G:
                                if (rowToParse[VITD100G] == null) {
                                } else if (rowToParse[VITD100G].contentEquals("")) {
                                } else if (rowToParse[VITD100G].contentEquals(" ")) {
                                } else {
                                    double vitd100g;
                                    try {
                                        vitd100g = Double.parseDouble(rowToParse[VITD100G]);
                                    } catch (Exception ignore) {
                                        vitd100g = 0;
                                    }
                                    produitToPersist.setVitd100g(vitd100g);
                                }
                                break;

                            case VITE100G:
                                if (rowToParse[VITE100G] == null) {
                                } else if (rowToParse[VITE100G].contentEquals("")) {
                                } else if (rowToParse[VITE100G].contentEquals(" ")) {
                                } else {
                                    double vite100g;
                                    try {
                                        vite100g = Double.parseDouble(rowToParse[VITE100G]);
                                    } catch (Exception ignore) {
                                        vite100g = 0;
                                    }
                                    produitToPersist.setVite100g(vite100g);
                                }
                                break;

                            case VITK100G:
                                if (rowToParse[ENERGIE100G] == null) {
                                } else if (rowToParse[ENERGIE100G].contentEquals("")) {
                                } else if (rowToParse[ENERGIE100G].contentEquals(" ")) {
                                } else {
                                    double vitk100g;
                                    try {
                                        vitk100g = Double.parseDouble(rowToParse[VITK100G]);
                                    } catch (Exception ignore) {
                                        vitk100g = 0;
                                    }
                                    produitToPersist.setVitk100g(vitk100g);
                                }
                                break;

                            case VITC100G:
                                if (rowToParse[VITC100G] == null) {
                                } else if (rowToParse[VITC100G].contentEquals("")) {
                                } else if (rowToParse[VITC100G].contentEquals(" ")) {
                                } else {
                                    double vitc100g;
                                    try {
                                        vitc100g = Double.parseDouble(rowToParse[VITC100G]);
                                    } catch (Exception ignore) {
                                        vitc100g = 0;
                                    }
                                    produitToPersist.setVitc100g(vitc100g);
                                }
                                break;

                            case VITB1100G:
                                if (rowToParse[VITB1100G] == null) {
                                } else if (rowToParse[VITB1100G].contentEquals("")) {
                                } else if (rowToParse[VITB1100G].contentEquals(" ")) {
                                } else {
                                    double vitb1100g;
                                    try {
                                        vitb1100g = Double.parseDouble(rowToParse[VITB1100G]);
                                    } catch (Exception ignore) {
                                        vitb1100g = 0;
                                    }
                                    produitToPersist.setVitb1100g(vitb1100g);
                                }
                                break;

                            case VITB2100G:
                                if (rowToParse[VITB2100G] == null) {
                                } else if (rowToParse[VITB2100G].contentEquals("")) {
                                } else if (rowToParse[VITB2100G].contentEquals(" ")) {
                                } else {
                                    double vitb2100g;
                                    try {
                                        vitb2100g = Double.parseDouble(rowToParse[VITB2100G]);
                                    } catch (Exception ignore) {
                                        vitb2100g = 0;
                                    }
                                    produitToPersist.setVitb2100g(vitb2100g);
                                }
                                break;

                            case VITPP100G:
                                if (rowToParse[VITPP100G] == null) {
                                } else if (rowToParse[VITPP100G].contentEquals("")) {
                                } else if (rowToParse[VITPP100G].contentEquals(" ")) {
                                } else {
                                    double vitpp100g;
                                    try {
                                        vitpp100g = Double.parseDouble(rowToParse[VITPP100G]);
                                    } catch (Exception ignore) {
                                        vitpp100g = 0;
                                    }
                                    produitToPersist.setVitpp100g(vitpp100g);
                                }
                                break;

                            case VITB6100G:
                                if (rowToParse[VITB6100G] == null) {
                                } else if (rowToParse[VITB6100G].contentEquals("")) {
                                } else if (rowToParse[VITB6100G].contentEquals(" ")) {
                                } else {
                                    double vitb6100g;
                                    try {
                                        vitb6100g = Double.parseDouble(rowToParse[VITB6100G]);
                                    } catch (Exception ignore) {
                                        vitb6100g = 0;
                                    }
                                    produitToPersist.setVitb6100g(vitb6100g);
                                }
                                break;

                            case VITB9100G:
                                if (rowToParse[VITB9100G] == null) {
                                } else if (rowToParse[VITB9100G].contentEquals("")) {
                                } else if (rowToParse[VITB9100G].contentEquals(" ")) {
                                } else {
                                    double vitb9100g;
                                    try {
                                        vitb9100g = Double.parseDouble(rowToParse[VITB9100G]);
                                    } catch (Exception ignore) {
                                        vitb9100g = 0;
                                    }
                                    produitToPersist.setVitb9100g(vitb9100g);
                                }
                                break;

                            case VITB12100G:
                                if (rowToParse[VITB12100G] == null) {
                                } else if (rowToParse[VITB12100G].contentEquals("")) {
                                } else if (rowToParse[VITB12100G].contentEquals(" ")) {
                                } else {
                                    double vitb12100g;
                                    try {
                                        vitb12100g = Double.parseDouble(rowToParse[VITB12100G]);
                                    } catch (Exception ignore) {
                                        vitb12100g = 0;
                                    }
                                    produitToPersist.setVitb12100g(vitb12100g);
                                }
                                break;

                            case CALCIUM100G:
                                if (rowToParse[CALCIUM100G] == null) {
                                } else if (rowToParse[CALCIUM100G].contentEquals("")) {
                                } else if (rowToParse[CALCIUM100G].contentEquals(" ")) {
                                } else {
                                    double calcium100g;
                                    try {
                                        calcium100g = Double.parseDouble(rowToParse[CALCIUM100G]);
                                    } catch (Exception ignore) {
                                        calcium100g = 0;
                                    }
                                    produitToPersist.setCalcium100g(calcium100g);
                                }
                                break;

                            case MAGNESIUM100G:
                                if (rowToParse[MAGNESIUM100G] == null) {
                                } else if (rowToParse[MAGNESIUM100G].contentEquals("")) {
                                } else if (rowToParse[MAGNESIUM100G].contentEquals(" ")) {
                                } else {
                                    double magnesium100g;
                                    try {
                                        magnesium100g = Double.parseDouble(rowToParse[MAGNESIUM100G]);
                                    } catch (Exception ignore) {
                                        magnesium100g = 0;
                                    }
                                    produitToPersist.setMagnesium100g(magnesium100g);
                                }
                                break;

                            case IRON100G:
                                if (rowToParse[IRON100G] == null) {
                                } else if (rowToParse[IRON100G].contentEquals("")) {
                                } else if (rowToParse[IRON100G].contentEquals(" ")) {
                                } else {
                                    double iron100g;
                                    try {
                                        iron100g = Double.parseDouble(rowToParse[IRON100G]);
                                    } catch (Exception ignore) {
                                        iron100g = 0;
                                    }
                                    produitToPersist.setIron100g(iron100g);
                                }
                                break;

                            case FER100G:
                                if (rowToParse[FER100G] == null) {
                                } else if (rowToParse[FER100G].contentEquals("")) {
                                } else if (rowToParse[FER100G].contentEquals(" ")) {
                                } else {
                                    double fer100g;
                                    try {
                                        fer100g = Double.parseDouble(rowToParse[FER100G]);
                                    } catch (Exception ignore) {
                                        fer100g = 0;
                                    }
                                    produitToPersist.setFer100g(fer100g);
                                }
                                break;

                            case BETACAROTENE100G:
                                if (rowToParse[BETACAROTENE100G] == null) {
                                } else if (rowToParse[BETACAROTENE100G].contentEquals("")) {
                                } else if (rowToParse[BETACAROTENE100G].contentEquals(" ")) {
                                } else {
                                    double betacarotene100g;
                                    try {
                                        betacarotene100g = Double.parseDouble(rowToParse[BETACAROTENE100G]);
                                    } catch (Exception ignore) {
                                        betacarotene100g = 0;
                                    }
                                    produitToPersist.setBetacarotene100g(betacarotene100g);
                                }
                                break;

                            case PRESENCEHUILEPALME:
                                if (rowToParse[PRESENCEHUILEPALME] == null) {
                                } else if (rowToParse[PRESENCEHUILEPALME].contentEquals("")) {
                                } else if (rowToParse[PRESENCEHUILEPALME].contentEquals(" ")) {
                                } else {
                                    boolean presencehuilepalme = rowToParse[PRESENCEHUILEPALME].equals("1");
                                    produitToPersist.setPresencehuilepalme(presencehuilepalme);
                                }
                                break;

                            case ALLERGENES:
                                Set<Allergene> allergenesToAssociate = new HashSet<>();
                                String[] splittedAllergenes = rowToParse[ALLERGENES].split(",");
                                for (int j = 0; j < splittedAllergenes.length; j++) {
                                    if (splittedAllergenes[j] == null) {
                                    } else if (splittedAllergenes[j].contentEquals("")) {
                                    } else if (splittedAllergenes[j].contentEquals(" ")) {
                                    } else {
                                        try {
                                            Allergene allergeneToAssociate = refs_allergenes.get(splittedAllergenes[j]);
                                            em.persist(allergeneToAssociate);
                                            allergenesToAssociate.add(allergeneToAssociate);
                                        } catch (Exception e) {
                                            Utils.msgError(e.getMessage());
                                        }
                                    }
                                }
                                produitToPersist.setAllergenes(allergenesToAssociate);
                                break;

                            case ADDITIFS:
                                Set<Additif> additifsToAssociate = new HashSet<>();
                                String[] splittedAdditifs = rowToParse[ADDITIFS].split(",");
                                for (int j = 0; j < splittedAdditifs.length; j++) {
                                    if (splittedAdditifs[j] == null) {
                                    } else if (splittedAdditifs[j].contentEquals("")) {
                                    } else if (splittedAdditifs[j].contentEquals(" ")) {
                                    } else {
                                        try {
                                            Additif additifToAssociate = refs_additifs.get(splittedAdditifs[j]);
                                            em.persist(additifToAssociate);
                                            additifsToAssociate.add(additifToAssociate);
                                        } catch (Exception e) {
                                            Utils.msgError(e.getMessage());
                                        }
                                    }
                                }
                                produitToPersist.setAdditifs(additifsToAssociate);
                                break;
                        }
                    }
                    //L'itération de la ligne se termine par la persistance en base du produit
                    em.persist(produitToPersist);
                    //La transaction est renouvellée à chaque produit, car nous constatons
                    //des erreurs si trop des données sont intégrée à une unique transaction
                    em.getTransaction().commit();
                    progressBar.step();
                }
            }
        }
        Utils.msgResult("Insertion des données terminée avec succès");
    }
}
