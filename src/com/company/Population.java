package com.company;
/*
 * Population.java
 * Manages a population of candidate tours
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Population {

    // Holds population of tours
    Tour[] tours;
    Tour fittestTour;
    int populationSize ;


    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        this.populationSize = populationSize;
        fittestTour = new Tour();
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize; i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

//     Saves a tour
//    public void saveTour(int index, Tour tour) {
//        tours[index] = tour;
//    }

    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }


//    // Gets a tour from population
//    public Tour getTour(int index) {
//        return tours[index];
//    }
// Gets a tour from population
public Tour getTour(int index) {
    return tours[index];
}




    // Gets the best tour in the population
    public Tour getFittest() throws InterruptedException {
        Client client = new Client();

        tours = client.connection(tours.length, tours);

        //Tour fittest = tours[0];
        Tour fittest = tours[0];

        for (int i = 1; i < populationSize(); i++) {
            if ( fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }


        setFittestTour(fittest);
        System.out.println(fittest.stringDistance());
        return fittest;


    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public Tour getFittestTour() {
        return fittestTour;
    }


    public void setFittestTour(Tour fittestTour) {
        this.fittestTour = fittestTour;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}