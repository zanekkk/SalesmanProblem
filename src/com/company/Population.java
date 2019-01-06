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
    ArrayList<Tour> tourList;
    Tour fittestTour;
    Tour baseTour;
    int populationSize ;


    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tourList = new ArrayList<>();
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
        tourList.add(index, tour);
    }

//    // Gets a tour from population
//    public Tour getTour(int index) {
//        return tours[index];
//    }

    public Tour getTour(int index) {
        return tourList.get(index);
    }




    // Gets the best tour in the population
    public Tour getFittest() throws InterruptedException {
        Client client = new Client();

        tourList = client.connection(tourList.size(), tourList);

        //Tour fittest = tours[0];
        Tour fittest = tourList.get(0);

        for (int i = 1; i < populationSize(); i++) {
            if ( fittest.getFitness() <= tourList.get(i).getFitness()) {
                fittest = getTour(i);
            }
        }


        setFittestTour(fittest);
        return fittest;

//        LinkedList<Fitness> fitnessList = new LinkedList<>();
//        Lock lock = new Lock();
//
//
//        for (int i = 0; i < populationSize(); i++) {
//
//                fitnessList.add(new Fitness(tours[i], lock));
//                fitnessList.get(i).start();  // Start download in another thread
//
//        }
//
//        while (lock.getRunningThreadsNumber() > 0) {
//            synchronized (lock) {
//                lock.wait();
//            }
//
//        }
//
//        for(int i = 0 ; i < tours.length ; i++){
//           if(tours[i].getFitness() == 0) {
//                tours[i] = fitnessList.get(i).getTour();
//            }
//        }
//        fitnessList.clear();
//
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

    public Tour getBaseTour() {
        return baseTour;
    }

    public void setBaseTour(Tour baseTour) {
        this.baseTour = baseTour;
    }

    // Gets population size
    public int populationSize() {
        return tourList.size();
    }
}