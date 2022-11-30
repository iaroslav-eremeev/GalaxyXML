package iaroslav.eremeev.model;

import iaroslav.eremeev.util.Generator;
import iaroslav.eremeev.util.XmlMethods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    /**
     * Method for adding galaxies in the universe
     * @param galaxy - Galaxy object
     */
    public void addGalaxy(Galaxy galaxy) {
        this.galaxies.add(galaxy);
    }

    /**
     * Method for finding planets in the universe
     * @param planet - Planet object to find
     * @return Array of integer indexes of respective galaxies and planets
     */
    public int[] findPlanet(Planet planet) {
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.findPlanet(planet) > -1)
                return new int[]{findGalaxy(galaxy), galaxy.findPlanet(planet)};
        }
        return null;
    }

    /**
     * Method for finding planets by their name
     * @param planetName - name of Planet object
     * @return Planet object if found
     */
    public Planet findPlanet(String planetName) {
        for (Galaxy galaxy : this.galaxies) {
            return galaxy.findPlanet(planetName);
        }
        return null;
    }

    /**
     * Method for finding galaxies in the universe
     * @param galaxy - Galaxy object
     * @return - integer index of galaxy if found
     */
    public int findGalaxy(Galaxy galaxy) {
        return this.galaxies.indexOf(galaxy);
    }

    /**
     * Method for finding galaxy by its name
     * @param galaxyName - name of Galaxy object
     * @return - Galaxy object if found by name
     */
    public Galaxy findGalaxy(String galaxyName) {
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.getName().equals(galaxyName)) return galaxy;
        }
        return null;
    }

    /**
     * Behavior method - every 30 seconds generates from 1 to 10 (number may be changed)
     * new galaxies with random number of planets as specified in Generator.generateGalaxies()
     * When updated, prints the Array of galaxies generated
     */
    public void behavior() {
        while (true) {
            ArrayList<Galaxy> galaxies = Generator
                    .generateGalaxies(Generator.generateNumber(1, 10));
            this.galaxies.addAll(galaxies);
            System.out.println(this);
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Parse Universe object from an XML document
     * @param fileName - name of the XML document
     * @return - Universe object
     */
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

    /**
     * Write a Universe object to XML document
     * @param fileName - name of the XML document to write to
     */
    public void toXML(String fileName) throws ParserConfigurationException {
        Document doc = XmlMethods.newDoc();
        toXmlElement(doc);
        XmlMethods.writeToXml(doc, fileName);
    }

    /**
     * Transform a Universe object into XML Element
     * @param doc - XML document
     */
    public void toXmlElement(Document doc){
        Element universe = doc.createElement("universe");
        doc.appendChild(universe);
        Element galaxies = doc.createElement("galaxies");
        universe.appendChild(galaxies);
        for (Galaxy galaxy : this.galaxies) galaxy.toXmlElement(doc, galaxies);
    }

    /**
     * Group planets with the same names inside tags <EqualGroup></> inside the tag <planets></>
     * with tag attributes: number - number of each EqualGroup in the <planets></> tag,
     * name - name of the group formed with the name of the planet and String "_group"
     * @param fileName - name of the XML file, that must be transformed
     */
    public void groupPlanetsInXmlFile(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XmlMethods.parseXML(fileName);
        NodeList planetsAllGalaxies = doc.getElementsByTagName("planets");
        for (int i = 0; i < planetsAllGalaxies.getLength(); i++) {
            Element planets = (Element) planetsAllGalaxies.item(i);
            NodeList planetsList = planets.getElementsByTagName("planet");
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
        XmlMethods.writeToXml(doc, fileName);
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
