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

        Planet tatooine = new Planet("Tatooine", "desert");
        Planet naboo = new Planet("Naboo", "forest");
        Planet alderaan = new Planet("Alderaan", "forest");
        Planet utapau = new Planet("Utapau", "desert");
        Planet coruscant = new Planet("Coruscant", "city");
        Planet yavin = new Planet("Yavin", "gas");
        Planet yavin4 = new Planet("Yavin", "forest");
        Galaxy galaxy1 = new Galaxy("Far far away");
        galaxy1.addPlanet(tatooine);
        galaxy1.addPlanet(naboo);
        galaxy1.addPlanet(alderaan);
        galaxy1.addPlanet(utapau);
        galaxy1.addPlanet(coruscant);
        galaxy1.addPlanet(yavin);
        galaxy1.addPlanet(yavin4);

        Planet uranus = new Planet("Uranus", "icy");
        Planet neptune = new Planet("Neptune", "oceanic");
        Planet jupiter = new Planet("Jupiter", "gas");
        Planet saturn = new Planet("Saturn", "gas");
        Planet mars = new Planet("Mars", "desert");
        Planet earth = new Planet("Earth", "forest");
        Galaxy galaxy2 = new Galaxy("Milky Way");
        galaxy2.addPlanet(uranus);
        galaxy2.addPlanet(neptune);
        galaxy2.addPlanet(jupiter);
        galaxy2.addPlanet(saturn);
        galaxy2.addPlanet(mars);
        galaxy2.addPlanet(earth);

        Universe universe = new Universe();
        universe.addGalaxy(galaxy1);
        universe.addGalaxy(galaxy2);

        universe.toXML("universe");
        System.out.println(universe.fromXML("universe"));
    }
}