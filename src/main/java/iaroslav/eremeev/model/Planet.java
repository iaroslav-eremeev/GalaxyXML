package iaroslav.eremeev.model;

import java.util.Objects;

public class Planet {

    private String name;
    private String type;

    public Planet() {
    }

    public Planet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void behaviour(){
        System.out.println("Planet " + name + " is here. Its type is " + type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name) && Objects.equals(type, planet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
