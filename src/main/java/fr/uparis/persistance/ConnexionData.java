package fr.uparis.persistance;

import mediatek2022.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ConnexionData {
    static Connection connection;

    private ConnexionData() {
    }

    protected static void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
        connection = DriverManager.getConnection(url, "vmvo1438_mediaweb", "mediaweb4568");
    }

    public static List<String> getUtilisateur() throws SQLException {
        ArrayList<String> utilisateurs = new ArrayList<>();
        String req1 = "SELECT `login` FROM utilisateur";
        try(PreparedStatement st1 = connection.prepareStatement(req1)){
            ResultSet RS1 = st1.executeQuery(req1);

            while (RS1.next()) {
                String login = RS1.getString(1);
                utilisateurs.add(login);
            }
            return utilisateurs;
        }
    }

    protected static Utilisateur connectUsingLogin(String username, String password) throws SQLException {
        String req = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";
        try(PreparedStatement st = connection.prepareStatement(req)){
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                String foundName = rs.getString("nom_u");
                String foundStrStatus = rs.getString("type_u");
                Object[] foundData = {}; //TODO: mettre les données complémentaires ici

                boolean foundStatus = false;

                if (foundStrStatus.equals("bibliothecaire")) {
                    foundStatus = true;
                }

                return new Utilisateur(foundName, foundStatus, foundData);
            }
        }
    }

    protected static List<Document> queryEveryDocument() throws SQLException {
        String req = "SELECT * FROM document";
        try(PreparedStatement st = connection.prepareStatement(req)) {
            ResultSet rs = st.executeQuery();

            ArrayList<Document> documentList = new ArrayList<>();

            if (!rs.next()) {
                return documentList;
            } else {
                do {
                    int id = rs.getInt("id_d");
                    String type = rs.getString("type_d");
                    boolean etatEmprunt = rs.getInt("emprunt_d") == 1;
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

    protected static void updateDocumentStatus(int id, boolean status) {
        String req = "UPDATE document SET emprunt_d = ? WHERE id_d = ?";
        try(PreparedStatement st = connection.prepareStatement(req)) {
            if(status) {
                st.setInt(1, 1);
            }else{
                st.setInt(1,-1);
            }
            st.setInt(2, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
