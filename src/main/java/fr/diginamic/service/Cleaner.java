package fr.diginamic.service;

import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleaner {
    private static Logger LOG = null;
    public static String CSV_FILE_RELATIVE_PATH = null;
    private static String[] HEADER;

    public static final String SEP_CHAR = "\\|";
    public static final String EFFACER_CHAR = "";
    public static final String COMMA_CHAR = ",";

    // Filtres
    public static final String FILTRE_POURCENTAGES = "(?:\\s+\\d+[%])";
    public static final String FILTRE_GRAMMES = "(?:\\s+\\d+[g])";
    public static final String FILTRE_CARACTERES_PARASITES = "(?:[_*:|%])";
    public static final String FILTRE_MILLIGRAMMES_MATIERES_GRASSES = "(?:\\s+\\d+[m]g)";
    public static final String FILTRE_PARENTHESES = "(\\((.*?)\\))";
    public static final String FILTRE_CROCHETS = "(\\[(.*?)\\])";
    public static final String FILTRE_TRAITEMENTS_VIRGULES = "(?:\\s+;\\s+)|(?:;\\s+)|(?:\\s+;)|(?:;)|(?:\\s+,\\s+)|(?:\\s+-\\s+)|(?:-)|(?:,\\s+)|(?:\\s+,)";
    public static final String FILTRE_TRIM_DEBUT_FIN = "^\\s+|\\s+$";
    public static String FILTRE_EXTERMINATOR = FILTRE_POURCENTAGES + "|" + FILTRE_GRAMMES + "|" + FILTRE_CARACTERES_PARASITES + "|" + FILTRE_MILLIGRAMMES_MATIERES_GRASSES + "|" + FILTRE_PARENTHESES + "|" + FILTRE_CROCHETS;

    //Patterns
    public static Pattern pattern_EXTERMINATOR;
    public static Pattern pattern_NETTOYAGE_ESPACES_SUPERFLUS;
    public static Pattern pattern_FILTRE_TRAITEMENTS_VIRGULES;

    static {
        LOG = Logging.LOG;
        CSV_FILE_RELATIVE_PATH = ResourceBundle.getBundle("project").getString("project.csvfile");

        pattern_EXTERMINATOR = Pattern.compile(FILTRE_EXTERMINATOR);
        pattern_NETTOYAGE_ESPACES_SUPERFLUS = Pattern.compile(FILTRE_TRIM_DEBUT_FIN);
        pattern_FILTRE_TRAITEMENTS_VIRGULES = Pattern.compile(FILTRE_TRAITEMENTS_VIRGULES);
    }

    public static String filtrer(String chaineAfiltrer) {
        String chaineNettoyee;
        Matcher matcher;

        //Suppression des caractères parasites
        matcher = pattern_EXTERMINATOR.matcher(chaineAfiltrer);
        chaineAfiltrer = matcher.replaceAll(Cleaner.EFFACER_CHAR);

        //Suppression des espaces en début et fin de chaine
        matcher = pattern_NETTOYAGE_ESPACES_SUPERFLUS.matcher(chaineAfiltrer);
        chaineAfiltrer = matcher.replaceAll(Cleaner.EFFACER_CHAR);

        //Suppression des espaces entre les virgules
        matcher = pattern_FILTRE_TRAITEMENTS_VIRGULES.matcher(chaineAfiltrer);
        chaineNettoyee = matcher.replaceAll(COMMA_CHAR);

        return chaineNettoyee;
    }

    public static ArrayList<String[]> extractAndCleanDatas(String csvFile_path) {
        List<String> rows;

        Utils.msgInfo(String.format("Nettoyage du fichier %s", csvFile_path));

        try {
            Utils.msgInfo("Lecture du fichier...");
            Path path = Paths.get(csvFile_path);
            rows = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (InvalidPathException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Utils.msgInfo("Nettoyage des lignes du fichier");
        Iterator<String> rowIterator = rows.iterator();
        HEADER = rowIterator.next().split(SEP_CHAR);
        ArrayList<String[]> cleanedRows = new ArrayList<>();
        while (rowIterator.hasNext()) {
            String[] parsedRow = rowIterator.next().split(SEP_CHAR, HEADER.length + 1);
            for (int i = 0; i < parsedRow.length; i++) {
                parsedRow[i] = filtrer(parsedRow[i].toLowerCase());
            }
            cleanedRows.add(parsedRow);
        }
        Utils.msgResult(String.format("Nettoyage du fichier %s OK", csvFile_path));
        return cleanedRows;
    }
}
