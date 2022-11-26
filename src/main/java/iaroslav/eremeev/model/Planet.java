package iaroslav.eremeev.model;

import iaroslav.eremeev.util.XMLmethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Planet {
    private String name;
    private String type;

    public Planet() {
    }

    public Planet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String behaviour(){
        return "Planet " + name + " is here. Its type is " + type + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XMLmethods.newDoc();
        toXMLElement(doc);
        XMLmethods.writeToFile(doc, fileName);
    }
    public void toXMLElement(Document doc){
        Element planet = doc.createElement("planet");
        doc.appendChild(planet);
        Element name = doc.createElement("name");
        planet.appendChild(name);
        name.setTextContent(this.name);
        Element type = doc.createElement("type");
        planet.appendChild(type);
        type.setTextContent(this.type);
    }

    public void toXMLElement(Document doc, Element parent){
        Element planet = doc.createElement("planet");
        parent.appendChild(planet);
        Element name = doc.createElement("name");
        planet.appendChild(name);
        name.setTextContent(this.name);
        Element type = doc.createElement("type");
        planet.appendChild(type);
        type.setTextContent(this.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name) && Objects.equals(type, planet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
