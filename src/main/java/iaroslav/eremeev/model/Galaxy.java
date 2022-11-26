package iaroslav.eremeev.model;

import java.util.ArrayList;
import java.util.Objects;

public class Galaxy {

    private String name;
    private ArrayList<Planet> planets = new ArrayList<>();

    public Galaxy(String name) {
        this.name = name;
    }

    public Galaxy() {
    }

    public String behavior(){
        System.out.println("This is galaxy " + name + ". Check the list of its planets");
        StringBuilder stringBuilder = new StringBuilder();
        for (Planet planet : this.planets) {
            stringBuilder.append(planet.behaviour());
        }
        return stringBuilder.toString();
    }

    public void addPlanet(Planet planet){
        this.planets.add(planet);
    }

    public int findPlanet(Planet planet){
        return this.planets.indexOf(planet);
    }

    public Planet findPlanet(String planetName){
        for (Planet planet : this.planets) {
            if (planet.getName().equals(planetName)) return planet;
        }
        return null;
    }

    public boolean removePlanet(Planet planet){
        return this.planets.remove(planet);
    }

    public Planet removePlanet(String planetName){
        for (Planet planet : this.planets){
            if (planet.getName().equals(planetName)){
                this.planets.remove(planet);
                return planet;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(name, galaxy.name) && Objects.equals(planets, galaxy.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planets);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }
}
