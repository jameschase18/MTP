package com.example.MyTimePlan.model;

public class Star {

    // Star name
    private String name;
    // Distance to the star
    private long distance;

    // Constructor with name and distance
    public Star(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }

    // Constructor for stars with unknown distance
    public Star(String name) {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for distance
    public long getDistance() {
        return distance;
    }

    // Setter for distance
    public void setDistance(long distance) {
        this.distance = distance;
    }

    // toString override
    @Override
    public String toString() {
        return "Star data: name= " + name + ", distance=" + distance;
    }

    // equals override
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        // cast to Star
        final Star star = (Star) obj;

        // for stars with the same name and distance return true
        if (star.getName().equals(this.name) && star.distance == this.distance) {
            return true;
        }
        // else return false
        return false;

    }
}
