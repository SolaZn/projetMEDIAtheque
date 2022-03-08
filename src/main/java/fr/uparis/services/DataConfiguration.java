package fr.uparis.services;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DataConfiguration {
    private DataConfiguration(){}

    public static String getConfigPackage() {
        try {
            InputStream inputFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            return doc.getFirstChild().getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
