package iaroslav.eremeev.model;

import iaroslav.eremeev.util.XMLmethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Galaxy {

    private String name;
    private ArrayList<Planet> planets = new ArrayList<>();

    public Galaxy(String name) {
        this.name = name;
    }

    public Galaxy() {
    }

    public String behavior(){
        System.out.println("This is galaxy " + name + ". Check the list of its planets");
        StringBuilder stringBuilder = new StringBuilder();
        for (Planet planet : this.planets) {
            stringBuilder.append(planet.behaviour());
        }
        return stringBuilder.toString();
    }

    public void addPlanet(Planet planet){
        this.planets.add(planet);
    }

    public int findPlanet(Planet planet){
        return this.planets.indexOf(planet);
    }

    public Planet findPlanet(String planetName){
        for (Planet planet : this.planets) {
            if (planet.getName().equals(planetName)) return planet;
        }
        return null;
    }

    public boolean removePlanet(Planet planet){
        return this.planets.remove(planet);
    }

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

    public Galaxy fromXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XMLmethods.parseXML(fileName);
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

    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XMLmethods.newDoc();
        toXmlElement(doc);
        XMLmethods.writeToFile(doc, fileName);
    }

    public void toXmlElement(Document doc){
        Element galaxy = doc.createElement("galaxy");
        doc.appendChild(galaxy);
        galaxy.setAttribute("name", this.name);
        Element planets = doc.createElement("planets");
        galaxy.appendChild(planets);
        for (Planet planet : this.planets) planet.toXmlElement(doc, planets);
    }
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
