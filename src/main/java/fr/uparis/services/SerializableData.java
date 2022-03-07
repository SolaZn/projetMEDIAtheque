package fr.uparis.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableData implements Serializable {
    private final Utilisateur objet;
    public SerializableData(Utilisateur data){
        objet = data;
    }

    public Object getObjet() {
        return objet;
    }

    //TODO: passer à un format de sérialization (JSON/XML) plutôt que "sérialiser" l'objet (possibles bugs)
    /*private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeBytes(objet.name());
        oos.writeBoolean(objet.isBibliothecaire());
        oos.writeObject(objet.data());
    }

    private  void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

    }*/
}
