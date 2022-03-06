package fr.uparis.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnexionData {
    static Connection connection;

    public static void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
        connection = DriverManager.getConnection(url, "vmvo1438_mediaweb", "mediaweb4568");
    }

    public static List<String> getUtilisateur() throws SQLException {
        ArrayList<String> utilisateurs = new ArrayList<>();
        String req1 = "SELECT `login` FROM utilisateur";
        PreparedStatement st1 = connection.prepareStatement(req1);
        ResultSet RS1 = st1.executeQuery(req1);

        while(RS1.next()) {
            String login = RS1.getString(1);
            utilisateurs.add(login);
        }

        RS1.close();
        st1.close();

        return utilisateurs;
    }

    public static Utilisateur connectUsingLogin(String username, String password) throws SQLException {
        String req = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, username);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();

        if(!rs.next()){
            return null;
        }
        else {
            String foundName = rs.getString("nom_u");
            String foundStatus = rs.getString("type_u");

            return new Utilisateur(foundName, foundStatus);
        }

    }
}
