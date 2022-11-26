package iaroslav.eremeev.program;

import iaroslav.eremeev.model.Planet;
import iaroslav.eremeev.model.Universe;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        /*Universe universe = new Universe();
        universe.behavior();*/

        Planet planet = new Planet("Tatuin", "desert");
        planet.toXML("PlanetTatuinXML");
    }
}