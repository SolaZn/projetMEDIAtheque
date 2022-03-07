package fr.uparis.persistance;

import fr.uparis.persistance.exceptions.DocumentDejaEmprunteException;
import mediatek2022.Utilisateur;

public class Document implements mediatek2022.Document{
    private final int id;
    private final String type;
    private boolean etatEmprunt;
    private final String titre;
    private final String auteur;
    private final String options;

    public Document(int id, String type, boolean etatEmprunt, String titre, String auteur, String options){
        this.id = id;
        this.type = type;
        this.etatEmprunt = etatEmprunt;
        this.titre = titre;
        this.auteur = auteur;
        this.options = options;
    }

    @Override
    public boolean disponible() {
        return etatEmprunt;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        if(!etatEmprunt){
            ConnexionData.updateDocumentStatus(this.id, true);
            etatEmprunt = true;
        }else
            throw new DocumentDejaEmprunteException("Le document " + this.titre + " a déjà été emprunté");
    }

    @Override
    public void retour() {
        etatEmprunt = false;
        ConnexionData.updateDocumentStatus(this.id, false);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", etatEmprunt=" + etatEmprunt +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", options='" + options + '\'' +
                '}';
    }

    protected int getId(){
        return id;
    }
}
