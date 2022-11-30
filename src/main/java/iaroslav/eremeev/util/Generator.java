package iaroslav.eremeev.util;

import iaroslav.eremeev.model.Galaxy;
import iaroslav.eremeev.model.Planet;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    /**
     * Random number generator - for numbers in planets' and galaxies' names
     * @param a - from
     * @param b - to, not included
     * @return int number
     */
    public static int generateNumber(int a, int b){
        Random random = new Random();
        return ThreadLocalRandom.current().nextInt(a, b + 1);
    }

    /**
     * Method for generating types of planets
     * @return String - type of planet (one of class Planet variables)
     */
    public static String generateType(){
        Random random = new Random();
        String[] types = {"Desert", "Rocky", "Oceanic", "Volcano", "Forestland"};
        return types[random.nextInt(types.length)];
    }

    /**
     * Method for generating Planet objects using generateType() and generateNumber()
     * @return Planet object
     */
    public static Planet generatePlanet() {
        Planet planet = new Planet();
        planet.setType(generateType());
        planet.setName("P" + generateNumber(10, 90));
        return planet;
    }

    /**
     * Method for generating a Galaxy object with name G**, where ** is a 2-digit int
     * Galaxy object has from 1 to 20 planets
     * @return Galaxy object
     */
    public static Galaxy generateGalaxy() {
        Galaxy galaxy = new Galaxy();
        galaxy.setName("G" + generateNumber(10, 90));
        int numberOfPlanets = generateNumber(1, 20);
        for (int i = 0; i < numberOfPlanets; i++) {
            galaxy.addPlanet(generatePlanet());
        }
        return galaxy;
    }

    public static ArrayList<Galaxy> generateGalaxies(){
        ArrayList<Galaxy> galaxies = new ArrayList<>();
        int numberOfGalaxies = generateNumber(1, 10);
        for (int i = 0; i < numberOfGalaxies; i++) {
            galaxies.add(generateGalaxy());
        }
        return galaxies;
    }

}
