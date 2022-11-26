package iaroslav.eremeev.util;

import iaroslav.eremeev.model.Galaxy;
import iaroslav.eremeev.model.Planet;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public static int generateNumber(int a, int b){
        Random random = new Random();
        return random.nextInt(b) + a;
    }

    public static String generateType(){
        Random random = new Random();
        String[] types = {"Desert", "Rocky", "Oceanic", "Volcano", "Forestland"};
        return types[random.nextInt(types.length)];
    }

    public static Planet generatePlanet() {
        Planet planet = new Planet();
        planet.setType(generateType());
        planet.setName("P" + generateNumber(1000, 10000));
        return planet;
    }

    public static Galaxy generateGalaxy() {
        Galaxy galaxy = new Galaxy();
        galaxy.setName("G" + generateNumber(1000, 10000));
        int numberOfPlanets = generateNumber(1, 21);
        for (int i = 0; i < numberOfPlanets; i++) {
            galaxy.addPlanet(generatePlanet());
        }
        return galaxy;
    }

    public static ArrayList<Galaxy> generateGalaxies(){
        ArrayList<Galaxy> galaxies = new ArrayList<>();
        int numberOfGalaxies = generateNumber(1, 11);
        for (int i = 0; i < numberOfGalaxies; i++) {
            galaxies.add(generateGalaxy());
        }
        return galaxies;
    }

}
