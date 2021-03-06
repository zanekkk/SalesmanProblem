package com.company;

/*
 * Tour.java
 * Stores a candidate tour
 */


import java.awt.*;
import java.io.Serializable;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;

public class Tour implements Serializable {

    private ArrayList baseCities;
    // Holds our tour of cities
    private ArrayList<City> tour = new ArrayList<City>();
    // Cache
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour) {
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(City city) {
        Integer x = city.x;
        Integer y = city.y;
        for (int i = 0; i < tourSize(); i++) {
            City xcity = tour.get(i);
            if ((x == null) || y == null || xcity == (null)) {

            }else{
                if (city.x == getCity(i).x && city.y == getCity(i).y) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + "|";
        }
        geneString += getCity(0);
        geneString += "|\n";

        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i).getName() + " -> ";
        }
        geneString += getCity(0).getName();
        return geneString;
    }


    public String stringDistance(){
        return "Dystans: " + distance;
    }
    public ArrayList getBaseCities() {
        return baseCities;
    }

    public void setBaseCities(ArrayList baseCities) {
        this.baseCities = baseCities;
    }
}