package fr.diginamic.service;

import fr.diginamic.mochizukiTools.Utils;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static fr.diginamic.service.Cleaner.filtrer;

public class CleanerTest extends TestCase {
    public String CSV_TEST_FILE_PATH = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
    public String COMPLETE_CSV_FILE_PATH = ResourceBundle.getBundle("project").getString("project.TEST_csvfileComplet");

    public void test_filtrage() {
        Utils.msgInfo("Lancement du test des filtres\n");

        test_filtrer("beurre     45%    , farine    25%    ", "beurre,farine");
        test_filtrer("beurre     45g, fromage  456g   ", "beurre,fromage");
        test_filtrer("beurre     45mg, fromage  22mg   ", "beurre,fromage");
        test_filtrer("Sucre, banane, Pâte (Farine 50%, Sucre 20%, Œufs 30%)", "Sucre,banane,Pâte");
        test_filtrer("   [lentilles],   [pois chiches]", ",");
        test_filtrer("tomates-courgettes - oeufs", "tomates,courgettes,oeufs");
        test_filtrer("__||** ** ::..tomates___||||", "tomates");
        test_filtrer("oeufs    -   patates-oignons - carottes", "oeufs,patates,oignons,carottes");
        test_filtrer("lardons;pizza  ;   mozzarella; linguines, pesto ,carbonnara", "lardons,pizza,mozzarella,linguines,pesto,carbonnara");
        Utils.msgInfo("Test des filtres déroulé avec succès\n");
    }

    public void test_filtrer(String chaineAfiltrer, String valeurAttendue) {
        String chaineAFiltrer, chaineNettoyee;
        Utils.msgTest("Test filtrage");
        Utils.msgInfo(String.format("chaine à nettoyer: %s", chaineAfiltrer));
        Utils.msgInfo(String.format("valeur attendue après traitement: %s", valeurAttendue));
        Utils.msgInfo("Lancement du nettoyage");
        chaineNettoyee = filtrer(chaineAfiltrer);
        Utils.msgInfo(String.format("résultat: %s", chaineNettoyee));
        assertEquals(valeurAttendue, chaineNettoyee);
        Utils.msgResult("TEST OK\n");
    }

    public void testCleanFile() {
        Utils.msgInfo("Lancement du test de la fonction cleanFile");
        ArrayList<String[]> rows = Cleaner.extractAndCleanDatas(CSV_TEST_FILE_PATH);
        assertEquals("[toto, titi, tutu]", Arrays.toString(rows.get(0)));
        assertEquals("[toto, titi, tutu]", Arrays.toString(rows.get(1)));
        assertEquals("[toto, titi, tutu]", Arrays.toString(rows.get(2)));
        assertEquals("[toto, titi, tutu]", Arrays.toString(rows.get(3)));
        assertEquals("[toto,popo,roro, titi,pipi,riri, tutu]", Arrays.toString(rows.get(4)));
        assertEquals("[Sucre,farine,Maïs]", Arrays.toString(rows.get(5)));
        assertEquals("[Sucre,farine,Maïs]", Arrays.toString(rows.get(6)));
        assertEquals("[Sucre,banane,Pâte]", Arrays.toString(rows.get(7)));

        Utils.msgInfo("Nettoyage de la base de données openFoodFacts entière");
        ArrayList<String[]> rows_CompleteCsvFile = Cleaner.extractAndCleanDatas(COMPLETE_CSV_FILE_PATH);
        for(String[] row : rows_CompleteCsvFile)
            Utils.msgResult(Arrays.toString(row));

        Utils.msgResult("Test de la fonction cleanFile déroulé avec succès");
    }
}
