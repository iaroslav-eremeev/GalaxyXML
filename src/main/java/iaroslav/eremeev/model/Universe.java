package iaroslav.eremeev.model;

import iaroslav.eremeev.util.Generator;
import iaroslav.eremeev.util.XmlMethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        Document doc = XmlMethods.parseXML(fileName);
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
        Document doc = XmlMethods.newDoc();
        toXmlElement(doc);
        XmlMethods.writeToFile(doc, fileName);
    }

    public void toXmlElement(Document doc){
        Element universe = doc.createElement("universe");
        doc.appendChild(universe);
        Element galaxies = doc.createElement("galaxies");
        universe.appendChild(galaxies);
        for (Galaxy galaxy : this.galaxies) galaxy.toXmlElement(doc, galaxies);
    }

    public void groupPlanetsInXmlFile(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XmlMethods.parseXML(fileName);
        NodeList planetsAllGalaxies = doc.getElementsByTagName("planets");
        for (int i = 0; i < planetsAllGalaxies.getLength(); i++) {
            Node planets = planetsAllGalaxies.item(i);
            NodeList planetsList = planets.getChildNodes();
            HashMap<String, ArrayList<Element>> planetsHashMap = new HashMap<>();
            String planetName = "";
            for (int j = 0; j < planetsList.getLength(); j++) {
                Element planet = (Element) planetsList.item(j);
                planetName = planet.getAttribute("name");
                planetsHashMap.computeIfAbsent(planetName, p -> new ArrayList<>()).add(planet);
            }
            int groupNumber = 1;
            for (var entry : planetsHashMap.entrySet()) {
                Element equalGroup = doc.createElement("EqualGroup");
                String groupName = entry.getKey();
                equalGroup.setAttribute("number", String.valueOf(groupNumber));
                groupNumber++;
                equalGroup.setAttribute("name", groupName + "_group");
                planets.appendChild(equalGroup);
                for (int j = 0; j < entry.getValue().size(); j++) {
                    equalGroup.appendChild(entry.getValue().get(j));
                }
            }
        }
        XmlMethods.writeToFile(doc, fileName);
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
