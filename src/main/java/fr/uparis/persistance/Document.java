package fr.uparis.persistance;

import fr.uparis.persistance.exceptions.DocumentDejaEmprunteException;
import mediatek2022.Utilisateur;

public class Document implements mediatek2022.Document{
    private String id;
    private String type;
    private boolean etatEmprunt;
    private String titre;
    private String auteur;  

    @Override
    public boolean disponible() {
        return etatEmprunt;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        if(!etatEmprunt){
            etatEmprunt = true;
        }else
            throw new DocumentDejaEmprunteException("Le document " + this.titre + " a déjà été emprunté");
    }

    @Override
    public void retour() {
        etatEmprunt = false;
    }
}
