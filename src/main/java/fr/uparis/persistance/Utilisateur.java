package fr.uparis.persistance;

import java.util.Locale;

public class Utilisateur implements mediatek2022.Utilisateur{
    private final String name;
    private final boolean status;
    private final Object[] data;
    public Utilisateur(String username, boolean userstatus, Object[] userdata){
        name = username;
        data = userdata;
        status = userstatus;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isBibliothecaire() {
        return status;
    }

    @Override
    public Object[] data() {
        return data;
    }
}
