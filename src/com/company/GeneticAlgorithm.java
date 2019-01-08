package com.company;
/*
 * GA.java
 * Manages algorithms for evolving population
 */


import java.util.ArrayList;

public class GeneticAlgorithm {

    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop) throws InterruptedException {
        Population newPopulation = new Population(pop.populationSize(), false);

        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);

            Tour child = crossover(parent1, parent2);
            newPopulation.saveTour(i, child);
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    public static Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour();

        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent2.tourSize());

        for (int i = 0; i < child.tourSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            }
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }


        for (int i = 0; i < parent2.tourSize(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }

        return child;
    }

    private static void mutate(Tour tour) {
        for (int tourPos1 = 0; tourPos1 < tour.tourSize(); tourPos1++) {
            if (Math.random() < mutationRate) {
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                //zamiana miejscami
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }


    }

    private static Tour tournamentSelection(Population pop) throws InterruptedException {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        Tour fittest = tournament.getFittest();
        return fittest;
    }


//    private static Tour checkChild(Tour child) {
//        ArrayList<City> cities = new ArrayList<>();
//
//        for (int i = 0; i < child.tourSize(); i++) {
//            City cityFromChild = child.getCity(i);
//            System.out.print(child.getCity(i));
//            if (citiesContains(cities,cityFromChild)) {
//                child.setCity(i, findCityNotContains(child));
//                System.out.print("JESTEM W");
//            } else {
//                cities.add(cityFromChild);
//            }
//
//        }
//        return child;
//    }

//    private static boolean citiesContains(ArrayList<City> tour, City city){
//        Integer x = city.x;
//        Integer y = city.y;
//        for (int i = 0; i < tour.size(); i++) {
//            if (!(x.equals(null) && y.equals(null))) {
//                if (city.x == tour.get(i).x && city.y == tour.get(i).y) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//
//    private static City findCityNotContains(Tour child) {
//        System.out.print(TourManager.getDestinationCities());
//
//        for (int i = 0; i < TourManager.numberOfCities(); i++) {
//
//            if (!child.containsCity(TourManager.getCity(i))) {
//                return TourManager.getCity(i);
//            }
//        }
//        return null;
//    }
}