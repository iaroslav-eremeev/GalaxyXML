package iaroslav.eremeev.model;

import iaroslav.eremeev.util.XmlMethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Galaxy {

    private String name;
    private ArrayList<Planet> planets = new ArrayList<>();

    public Galaxy(String name) {
        this.name = name;
    }

    public Galaxy() {
    }

    /**
     * Galaxy behavior - return a String with a list of planets forming the galaxy
     * @return String
     */
    public String behavior(){
        System.out.println("This is galaxy " + name + ". Check the list of its planets");
        StringBuilder stringBuilder = new StringBuilder();
        for (Planet planet : this.planets) {
            stringBuilder.append(planet.behaviour());
        }
        return stringBuilder.toString();
    }

    /**
     * Add a Planet in the ArrayList of planets in Galaxy
     * @param planet - Planet object to add
     */
    public void addPlanet(Planet planet){
        this.planets.add(planet);
    }

    /**
     * Find planet in the galaxy by object
     * @param planet - Planet object
     * @return index of planet
     */
    public int findPlanet(Planet planet){
        return this.planets.indexOf(planet);
    }

    /**
     * Find planet in the galaxy by its name
     * @param planetName - name of the planet we look for
     * @return Planet object with this name
     */
    public Planet findPlanet(String planetName){
        for (Planet planet : this.planets) {
            if (planet.getName().equals(planetName)) return planet;
        }
        return null;
    }

    /**
     * Remove a planet from the list of planets in the galaxy
     * @param planet - Planet object to remove
     * @return true if successful
     */
    public boolean removePlanet(Planet planet){
        return this.planets.remove(planet);
    }

    /**
     * Remove a planet from the list of planets in the galaxy by its name
     * @param planetName - name of the Planet object to remove
     * @return removed Planet object
     */
    public Planet removePlanet(String planetName){
        for (Planet planet : this.planets){
            if (planet.getName().equals(planetName)){
                this.planets.remove(planet);
                return planet;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Parse a Galaxy object from an XML file
     * @param fileName - name of the file
     * @return - Galaxy object
     */
    public Galaxy fromXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XmlMethods.parseXML(fileName);
        Element galaxyXML = (Element) doc.getElementsByTagName("galaxy").item(0);
        String name = galaxyXML.getAttribute("name");
        Galaxy galaxy = new Galaxy(name);
        Element planets = (Element) galaxyXML.getElementsByTagName("planets").item(0);
        NodeList planetsList = planets.getElementsByTagName("planet");
        for (int i = 0; i < planetsList.getLength(); i++) {
            Planet planet = new Planet();
            planet = planet.fromXmlParent((Element) planetsList.item(i));
            galaxy.addPlanet(planet);
        }
        return galaxy;
    }

    /**
     * Parse a Galaxy object from a piece of XML code
     * @param parent - parent Element in XML file
     * @return Galaxy object
     */
    public Galaxy fromXmlParent(Element parent){
        String name = parent.getAttribute("name");
        Galaxy galaxy = new Galaxy(name);
        Element planets = (Element) parent.getElementsByTagName("planets").item(0);
        NodeList planetsList = planets.getElementsByTagName("planet");
        for (int i = 0; i < planetsList.getLength(); i++) {
            Planet planet = new Planet();
            planet = planet.fromXmlParent((Element) planetsList.item(i));
            galaxy.addPlanet(planet);
        }
        return galaxy;
    }

    /**
     * Write Galaxy to XML file
     * @param fileName - name of the file to write to
     */
    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XmlMethods.newDoc();
        toXmlElement(doc);
        XmlMethods.writeToXml(doc, fileName);
    }

    /**
     * Transform a Galaxy object into XML element in a Document if there is no certain parent Element
     * @param doc - XML Document where we add Element with tag <galaxy>
     */
    public void toXmlElement(Document doc){
        Element galaxy = doc.createElement("galaxy");
        doc.appendChild(galaxy);
        galaxy.setAttribute("name", this.name);
        Element planets = doc.createElement("planets");
        galaxy.appendChild(planets);
        for (Planet planet : this.planets) planet.toXmlElement(doc, planets);
    }

    /**
     * Transform a Galaxy object into XML Element inside certain parent Element tag in a Document
     * @param doc - XML Document
     * @param parent - parent Element in XML document
     */
    public void toXmlElement(Document doc, Element parent){
        Element galaxy = doc.createElement("galaxy");
        parent.appendChild(galaxy);
        galaxy.setAttribute("name", this.name);
        Element planets = doc.createElement("planets");
        galaxy.appendChild(planets);
        for (Planet planet : this.planets) planet.toXmlElement(doc, planets);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(name, galaxy.name) && Objects.equals(planets, galaxy.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planets);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }
}
