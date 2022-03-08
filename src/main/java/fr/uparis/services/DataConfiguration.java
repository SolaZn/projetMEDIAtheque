package fr.uparis.services;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.List;

@Root(name = "root")
public class DataConfiguration {
    @ElementList(name = "data")
    private List<String> data;

    @Element(name = "package")
    private String packageName;

    private String getPackageName() {
        return packageName;
    }

    public static String getConfigPackage() throws Exception {
        Serializer serializer = new Persister();

        DataConfiguration config = serializer.read(DataConfiguration.class,
                DataConfiguration.class.getClassLoader().getResourceAsStream("config.xml"));
        return config.getPackageName();
    }
}
