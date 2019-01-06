package com.company;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  throws UnknownHostException, IOException, InterruptedException {
//        int number, temp ;
//        Scanner sc = new Scanner(System.in);
//        Socket s = new Socket("127.0.0.1",1342);
//        Scanner citiessc1 =  new Scanner(s.getInputStream());


        // Create and add our
        City city = new City(60, 200, "Opole");
        TourManager.addCity(city);
        City city2 = new City(180, 200,"Warszawa");
        TourManager.addCity(city2);
        City city3 = new City(80, 180, "Radom");
        TourManager.addCity(city3);
        City city4 = new City(140, 180,"Krakow");
        TourManager.addCity(city4);
        City city5 = new City(20, 160, "Kalisz");
        TourManager.addCity(city5);
        City city6 = new City(100, 160, "Katowice");
        TourManager.addCity(city6);
        City city7 = new City(200, 160, "Gdansk");
        TourManager.addCity(city7);
        City city8 = new City(140, 140, "Rzeszow");
        TourManager.addCity(city8);
        City city9 = new City(40, 120, "Warka");
        TourManager.addCity(city9);
        City city10 = new City(100, 120, "Wroclaw");
        TourManager.addCity(city10);


        // Initialize population
        Population population = new Population(50, true);

        System.out.println("Initial distance: " + population.getFittest().getDistance());
        System.out.println(population.getFittestTour());
        // Evolve population for 100 generations
//        population = GeneticAlgorithm.evolvePopulation(population);
        for (int i = 0; i < 5; i++) {
             population = GeneticAlgorithm.evolvePopulation(population);
        }

        // Print final results
        System.out.println("Finished");
//        population.getFittest();
        System.out.println("Final distance: " + population.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(population.getFittestTour());


    }

}

