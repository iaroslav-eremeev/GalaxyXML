package iaroslav.eremeev.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Universe {

    private ArrayList<Galaxy> galaxies = new ArrayList<>();

    public Universe() {
    }

    public Universe(ArrayList<Galaxy> galaxies) {
        this.galaxies = galaxies;
    }

    public void addGalaxy(Galaxy galaxy){
        this.galaxies.add(galaxy);
    }

    public boolean findPlanet(Planet planet){
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.findPlanet(planet)) return true;
        }
        return false;
    }

    public boolean findPlanet(String planetName){
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.findPlanet(planetName)) return true;
        }
        return false;
    }

    public boolean findGalaxy(Galaxy galaxy){
        return this.galaxies.contains(galaxy);
    }

    public boolean findGalaxy(String galaxyName){
        for (Galaxy galaxy : this.galaxies) {
            if (galaxy.getName().equals(galaxyName)) return true;
        }
        return false;
    }

    public void behavior(){
        System.out.println("Generating galaxies and planets... type 1 to finish");
        Random random = new Random();
        Generator generator = new Generator();
        Scanner scanner = new Scanner(System.in);
        long lastCall = 0;
        while (scanner.nextInt() != 1){
            if(System.currentTimeMillis() - lastCall > 30000) {
                lastCall = System.currentTimeMillis();
                int numberOfGalaxies = random.nextInt(10) + 1;
                for (int i = 0; i < numberOfGalaxies; i++) {
                    generator.generateGalaxy();
                }
                System.out.println("The Universe expands! Look what it consists of now:");
                System.out.println(this.galaxies.toString());
            }
        }
    }

    public ArrayList<Galaxy> getGalaxies() {
        return galaxies;
    }

    public void setGalaxies(ArrayList<Galaxy> galaxies) {
        this.galaxies = galaxies;
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
