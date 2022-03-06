package fr.uparis.services;

import java.io.Serializable;

public class SerializableData implements Serializable {
    private Object objet;
    public SerializableData(Object data){
        objet = data;
    }

    public Object getObjet() {
        return objet;
    }
}
