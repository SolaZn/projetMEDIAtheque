package fr.uparis.persistance;

import mediatek2022.Utilisateur;

public class Document implements mediatek2022.Document{
    @Override
    public boolean disponible() {
        return false;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {

    }

    @Override
    public void retour() {

    }
}
