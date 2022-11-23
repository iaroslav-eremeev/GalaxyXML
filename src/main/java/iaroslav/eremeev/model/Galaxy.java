package iaroslav.eremeev.model;

import java.util.ArrayList;
import java.util.Objects;

public class Galaxy {

    private String name;
    private ArrayList<Planet> planets = new ArrayList<>();

    public Galaxy(String name, ArrayList<Planet> planets) {
        this.name = name;
        this.planets = planets;
    }

    public Galaxy() {
    }

    public void behavior(){
        System.out.println("This is galaxy " + name + ". Check the list of its planets");
        for (int i = 0; i < this.planets.size(); i++) {
            this.planets.get(i).behaviour();
        }
    }

    public void addPlanet(Planet planet){
        this.planets.add(planet);
    }

    public boolean findPlanet(Planet planet){
        return this.planets.contains(planet);
    }

    public boolean findPlanet(String planetName){
        for (Planet planet : this.planets) {
            if (planet.getName().equals(planetName)) return true;
        }
        return false;
    }

    public void removePlanet(Planet planet){
        this.planets.remove(planet);
    }

    public void removePlanet(String planetName){
        for (Planet planet : this.planets){
            if (planet.getName().equals(planetName)){
                this.planets.remove(planet);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
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
