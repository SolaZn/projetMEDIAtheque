package fr.uparis.persistance;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import fr.uparis.persistance.exceptions.TypeNonSpecifieException;
import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
    static boolean connectionDone = false;

    static {
        Mediatheque.getInstance().setData(new MediathequeData());
        try {
            if(!connectionDone) {
                ConnexionData.initialize();
                connectionDone = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private MediathequeData() {
    }

    // renvoie la liste de tous les documents disponibles de la m�diath�que
    @Override
    public List<mediatek2022.Document> tousLesDocumentsDisponibles() {
        try{
            return ConnexionData.queryEveryDocument();
        }catch(SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // va r�cup�rer le User dans la BD et le renvoie
    // si pas trouv�, renvoie null
    @Override
    public Utilisateur getUser(String login, String password) {
        try {
            return ConnexionData.connectUsingLogin(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // va r�cup�rer le document de num�ro numDocument dans la BD
    // et le renvoie
    // si pas trouv�, renvoie null
    @Override
    public mediatek2022.Document getDocument(int numDocument) {
        try {
            return ConnexionData.queryDocument(numDocument);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void ajoutDocument(int type, Object[] args) {
        String titre = (String) args[0];
        String auteur = (String) args [1];

        try {
            ConnexionData.addDocument(getType(type), titre, auteur);
        } catch (SQLException | TypeNonSpecifieException e) {
            e.printStackTrace();
        }
    }

    private String getType(int type) throws TypeNonSpecifieException {
        switch (type){
            case 1:{
                return "Livre";
            }
            case 2:{
                return "DVD";
            }
            case 3:{
                return "CD";
            }
            default:{
                throw new TypeNonSpecifieException("Le type n'a pas été spécifié");
            }
        }
    }

}
