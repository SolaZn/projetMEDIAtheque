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

    public List<String> getUtilisateur() throws SQLException {
        ArrayList<String> utilisateurs = new ArrayList<>();
        String req1 = "SELECT `login` FROM utilisateur";
        Statement st1 = connection.createStatement();
        ResultSet RS1 = st1.executeQuery(req1);

        while(RS1.next()) {
            String login = RS1.getString(1);
            utilisateurs.add(login);
        }

        RS1.close();
        st1.close();

        return utilisateurs;
    }
}
