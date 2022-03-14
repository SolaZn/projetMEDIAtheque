package fr.uparis.persistance;

import mediatek2022.Document;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ConnexionData {
    static String url;

    private ConnexionData() {
    }

    protected static void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb?autoReconnect=true";
    }

    protected static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, "vmvo1438_mediaweb", "mediaweb4568");
    }

    protected static List<String> getUtilisateur() throws SQLException {
        ArrayList<String> utilisateurs = new ArrayList<>();
        String req1 = "SELECT `login` FROM utilisateur";
        try(PreparedStatement st1 = connect().prepareStatement(req1)){
            ResultSet RS1 = st1.executeQuery(req1);

            while (RS1.next()) {
                String login = RS1.getString(1);
                utilisateurs.add(login);
            }
            return utilisateurs;
        }
    }

    // connexion d'un utilisateur
    protected static Utilisateur connectUsingLogin(String username, String password) throws SQLException {
        String req = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";
        try(PreparedStatement st = connect().prepareStatement(req)) {
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                String foundName = rs.getString("nom_u");
                String foundStrStatus = rs.getString("type_u");
                Object[] foundData = {}; //TODO: mettre les donn�es compl�mentaires ici

                boolean foundStatus = foundStrStatus.equals("bibliothecaire");

                return new Utilisateur(foundName, foundStatus, foundData);
            }
        }
    }

    // récupération de tous les documents
    protected static List<Document> queryEveryDocument() throws SQLException {
        String req = "SELECT * FROM document";
            try (PreparedStatement st = connect().prepareStatement(req)) {
                ResultSet rs = st.executeQuery();

                ArrayList<Document> documentList = new ArrayList<>();

                if (!rs.next()) {
                    return documentList;
                } else {
                    do {
                        int id = rs.getInt("id_d");
                        String type = rs.getString("type_d");
                        boolean etatEmprunt = rs.getInt("emprunt_d") > 0;
                        String titre = rs.getString("titre_d");
                        String auteur = rs.getString("auteur_d");
                        String options = rs.getString("options_d");

                        Document queriedDocument = new fr.uparis.persistance.Document(id, type, etatEmprunt, titre, auteur, options);
                        documentList.add(queriedDocument);
                    } while (rs.next());
                    return documentList;
                }
            }
    }

    // récupération d'un document
    protected static Document queryDocument(int idDocument) throws SQLException {
        String req = "SELECT * FROM document WHERE id_d = ?";
            try (PreparedStatement st = connect().prepareStatement(req)) {
                st.setInt(1, idDocument);
                ResultSet rs = st.executeQuery();

                if (!rs.next()) {
                    int id = rs.getInt("id_d");
                    String type = rs.getString("type_d");
                    boolean etatEmprunt = rs.getInt("emprunt_d") >= 0;
                    String titre = rs.getString("titre_d");
                    String auteur = rs.getString("auteur_d");
                    String options = rs.getString("options_d");

                    return new fr.uparis.persistance.Document(id, type, etatEmprunt, titre, auteur, options);
                }
            }
            return null;
    }

    // mise à jour du statut d'un document
    protected static synchronized void updateDocumentStatus(int id, boolean status) throws SQLException {
        String req = "UPDATE document SET emprunt_d = ? WHERE id_d = ?";
            try (PreparedStatement st = connect().prepareStatement(req)) {
                if (status) {
                    st.setInt(1, 1);
                } else {
                    st.setInt(1, -1);
                }
                st.setInt(2, id);
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    // ajout d'un document
    protected static void addDocument(String type, String titre, String auteur) throws SQLException {
        String req = "INSERT INTO document(type_d, titre_d, auteur_d) VALUES(?, ?, ?)";
            try (PreparedStatement st = connect().prepareStatement(req)) {
                st.setString(1, type);
                st.setString(2, titre);
                st.setString(3, auteur);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
