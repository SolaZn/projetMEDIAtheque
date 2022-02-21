package fr.uparis.persistance;

public class Utilisateur implements mediatek2022.Utilisateur{
    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isBibliothecaire() {
        return false;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }
}
