package iaroslav.eremeev.model;

import iaroslav.eremeev.util.Generator;
import iaroslav.eremeev.util.XMLmethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Universe {

    private ArrayList<Galaxy> galaxies = new ArrayList<>();

    public Universe() {
    }

    public void addGalaxy(Galaxy galaxy) {
        this.galaxies.add(galaxy);
    }

    public int[] findPlanet(Planet planet) {
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.findPlanet(planet) > -1)
                return new int[]{findGalaxy(galaxy), galaxy.findPlanet(planet)};
        }
        return null;
    }

    public Planet findPlanet(String planetName) {
        for (Galaxy galaxy : this.galaxies) {
            return galaxy.findPlanet(planetName);
        }
        return null;
    }

    public int findGalaxy(Galaxy galaxy) {
        return this.galaxies.indexOf(galaxy);
    }

    public Galaxy findGalaxy(String galaxyName) {
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.getName().equals(galaxyName)) return galaxy;
        }
        return null;
    }

    public void behavior() {
        while (true) {
            ArrayList<Galaxy> galaxies = Generator.generateGalaxies();
            this.galaxies.addAll(galaxies);
            System.out.println(this);
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
            }
        }
    }

    public Universe fromXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XMLmethods.parseXML(fileName);
        Universe universe = new Universe();
        Element universeXML = (Element) doc.getElementsByTagName("universe").item(0);
        Element galaxies = (Element) universeXML.getElementsByTagName("galaxies").item(0);
        NodeList galaxiesList = galaxies.getElementsByTagName("galaxy");
        for (int i = 0; i < galaxiesList.getLength(); i++) {
            Galaxy galaxy = new Galaxy();
            galaxy = galaxy.fromXmlParent((Element) galaxiesList.item(i));
            universe.addGalaxy(galaxy);
        }
        return universe;
    }

    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XMLmethods.newDoc();
        toXmlElement(doc);
        XMLmethods.writeToFile(doc, fileName);
    }

    public void toXmlElement(Document doc){
        Element universe = doc.createElement("universe");
        doc.appendChild(universe);
        Element galaxies = doc.createElement("galaxies");
        universe.appendChild(galaxies);
        for (Galaxy galaxy : this.galaxies) galaxy.toXmlElement(doc, galaxies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universe universe = (Universe) o;
        return Objects.equals(galaxies, universe.galaxies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(galaxies);
    }

    @Override
    public String toString() {
        return "Universe{" +
                "galaxies=" + galaxies +
                '}';
    }
}
