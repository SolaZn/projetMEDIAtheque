package fr.uparis.persistance;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import fr.uparis.persistance.exceptions.ConnectionException;
import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
    // Jean-François Brette 01/01/2018
    static boolean connectionDone = false;

    static {
        Mediatheque.getInstance().setData(new MediathequeData());
        try {
            if(!connectionDone) {
                ConnexionData.initialize();
                connectionDone = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MediathequeData() {
    }

    // renvoie la liste de tous les documents disponibles de la médiathèque
    @Override
    public List<mediatek2022.Document> tousLesDocumentsDisponibles() {
        try{
            return ConnexionData.queryEveryDocument();
        }catch(SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // va récupérer le User dans la BD et le renvoie
    // si pas trouvé, renvoie null
    @Override
    public Utilisateur getUser(String login, String password) {
        try {
            return ConnexionData.connectUsingLogin(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // va récupérer le document de numéro numDocument dans la BD
    // et le renvoie
    // si pas trouvé, renvoie null
    @Override
    public Document getDocument(int numDocument) {
        return null;
    }

    @Override
    public void ajoutDocument(int type, Object... args) {
        // args[0] -> le titre
        // args [1] --> l'auteur
        // etc... variable suivant le type de document
    }

}
