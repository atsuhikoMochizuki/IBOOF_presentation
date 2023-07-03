package fr.diginamic.dataAccessLayer;

import jakarta.transaction.Transactional;

public interface I_Crud {
    @Transactional
    void ajouter();

    @Transactional
    void modifier();

    @Transactional
    void supprimer();
}
