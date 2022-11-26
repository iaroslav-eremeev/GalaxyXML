package iaroslav.eremeev.model;

import iaroslav.eremeev.util.Generator;

import java.util.ArrayList;
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
