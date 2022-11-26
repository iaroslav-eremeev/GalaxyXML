package iaroslav.eremeev.program;

import iaroslav.eremeev.model.Galaxy;
import iaroslav.eremeev.model.Planet;
import iaroslav.eremeev.model.Universe;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        /*Universe universe = new Universe();
        universe.behavior();*/

        Planet planet1 = new Planet("Tatuin", "desert");
        Planet planet2 = new Planet("Naboo", "forest");
        Planet planet3 = new Planet("Alderaan", "oceanic");
        Galaxy galaxy = new Galaxy("Far");
        galaxy.addPlanet(planet1);
        galaxy.addPlanet(planet2);
        galaxy.addPlanet(planet3);
        galaxy.toXML("FarGalaxyXML");
    }
}