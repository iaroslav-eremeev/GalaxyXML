package iaroslav.eremeev.model;

import iaroslav.eremeev.util.XMLwriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
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

    public void toXML(String fileName) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("galaxy");
        doc.appendChild(rootElement);
        Element name = doc.createElement("name");
        rootElement.appendChild(name);
        name.setTextContent(this.name);
        Element planets = doc.createElement("planets");
        rootElement.appendChild(planets);
        for (Planet planet : this.planets) {
            Element planetElement = doc.createElement("planet");
            planets.appendChild(planetElement);
            planet.toXMLElement(doc, planetElement);
        }
        try (FileOutputStream output =
                     new FileOutputStream(fileName)) {
            XMLwriter.writeXML(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
