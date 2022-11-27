package iaroslav.eremeev.program;

import iaroslav.eremeev.model.Galaxy;
import iaroslav.eremeev.model.Planet;
import iaroslav.eremeev.model.Universe;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        /*Universe universe = new Universe();
        universe.behavior();*/

        Planet planet1 = new Planet("Tatuin", "desert");
        Planet planet2 = new Planet("Naboo", "forest");
        Planet planet3 = new Planet("Alderaan", "oceanic");
        Galaxy galaxy1 = new Galaxy("Far");
        galaxy1.addPlanet(planet1);
        galaxy1.addPlanet(planet2);
        galaxy1.addPlanet(planet3);

        Planet planet4 = new Planet("Uranus", "icy");
        Planet planet5 = new Planet("Neptun", "oceanic");
        Galaxy galaxy2 = new Galaxy("Milky Way");
        galaxy2.addPlanet(planet4);
        galaxy2.addPlanet(planet5);

        Universe universe = new Universe();
        universe.addGalaxy(galaxy1);
        universe.addGalaxy(galaxy2);

        /*universe.toXML("TheOnlyUniverse");*/

        planet1.toXML("planet1");
        System.out.println(planet1.fromXML("planet1"));
    }
}