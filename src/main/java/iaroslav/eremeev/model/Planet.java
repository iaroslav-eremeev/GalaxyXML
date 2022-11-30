package iaroslav.eremeev.model;

import iaroslav.eremeev.util.XmlMethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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

    /**
     * Planet behavior method - return a line with info from its String variables
     * @return String
     */
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

    /**
     * Parse a Planet object from an XML file
     * @param fileName - name of XML file
     * @return Planet object
     */
    public Planet fromXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XmlMethods.parseXML(fileName);
        String name = doc.getDocumentElement().getAttribute("name");
        String type = doc.getDocumentElement().getAttribute("type");
        return new Planet(name, type);
    }

    /**
     * Parse a Planet object from a piece of XML code
     * @param parent - parent Element in XML document
     * @return Planet object
     */
    public Planet fromXmlParent(Element parent){
        String name = parent.getAttribute("name");
        String type = parent.getAttribute("type");
        return new Planet(name, type);
    }

    /**
     * Write Planet object to XML file
     * @param fileName - name of the file to write a Planet object
     */
    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XmlMethods.newDoc();
        toXmlElement(doc);
        XmlMethods.writeToXml(doc, fileName);
    }

    /**
     * Transform a Planet object into XML element in a Document if there is no certain parent Element
     * @param doc - XML Document where we add Element with tag <planet>
     */
    public void toXmlElement(Document doc){
        Element planet = doc.createElement("planet");
        doc.appendChild(planet);
        planet.setAttribute("name", this.name);
        planet.setAttribute("type", this.type);
    }

    /**
     * Transform a Planet object into XML element inside certain parent Element tag in a Document
     * @param doc - XML document
     * @param parent - parent Element in XML document
     */
    public void toXmlElement(Document doc, Element parent){
        Element planet = doc.createElement("planet");
        parent.appendChild(planet);
        planet.setAttribute("name", this.name);
        planet.setAttribute("type", this.type);
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
