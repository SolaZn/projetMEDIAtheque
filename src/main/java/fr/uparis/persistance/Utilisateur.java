package fr.uparis.persistance;

public class Utilisateur implements mediatek2022.Utilisateur{
    private final String name;
    private final String status;
    protected Utilisateur(String username, String userstatus){
        name = username;
        status = userstatus;
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isBibliothecaire() {
        return status.equals("bibliothecaire");
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }
}
