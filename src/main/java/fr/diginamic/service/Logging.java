package fr.diginamic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    public static final Logger LOG =
            LoggerFactory.getLogger(Logging.class);

    public static void effectuerLog(String param) {
        LOG.debug("Traitement 1 : param = {}", param);
    }
}
