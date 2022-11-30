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
        planet.setName("P" + generateNumber(10, 99));
        return planet;
    }

    /**
     * Method for generating a Galaxy object with name G**, where ** is a 2-digit int
     * @param numberOfPlanets - number of planets in galaxy
     * @return Galaxy object
     */
    public static Galaxy generateGalaxy(int numberOfPlanets) {
        Galaxy galaxy = new Galaxy();
        galaxy.setName("G" + generateNumber(10, 99));
        for (int i = 0; i < numberOfPlanets; i++) {
            galaxy.addPlanet(generatePlanet());
        }
        return galaxy;
    }

    /**
     * Method for generating ArrayList of Galaxies. Number of planets in each Galaxy
     * is defined using generateNumber() - random number of planets between 1 and 20
     * @param numberOfGalaxies - number of galaxies in the ArrayList
     * @return ArrayList of Galaxy objects
     */
    public static ArrayList<Galaxy> generateGalaxies(int numberOfGalaxies){
        ArrayList<Galaxy> galaxies = new ArrayList<>();
        for (int i = 0; i < numberOfGalaxies; i++) {
            galaxies.add(generateGalaxy(generateNumber(1, 20)));
        }
        return galaxies;
    }

}
