package iaroslav.eremeev.model;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public Planet generatePlanet() {
        Planet planet = new Planet();
        Random random = new Random();
        int planetNum = random.nextInt(1000) + 1000;
        String[] types = {"Desert", "Rocky", "Oceanic", "Volcano", "Forestland"};
        String type = types[random.nextInt(types.length)];
        planet.setType(type);
        planet.setName("P" + planetNum);
        return planet;
    }

    public Galaxy generateGalaxy() {
        Generator generator = new Generator();
        Galaxy galaxy = new Galaxy();
        Random random = new Random();
        int galaxyNum = random.nextInt(1000) + 1000;
        galaxy.setName("G" + galaxyNum);
        int numberOfPlanets = random.nextInt(20) + 1;
        ArrayList<Planet> planets = new ArrayList<>();
        for (int i = 0; i < numberOfPlanets; i++) {
            planets.add(generator.generatePlanet());
        }
        galaxy.setPlanets(planets);
        return galaxy;
    }

}
