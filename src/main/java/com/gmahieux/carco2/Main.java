package com.gmahieux.carco2;

import com.gmahieux.carco2.data.DataLoaderKt;
import com.gmahieux.carco2.data.DataLoaderJava8;
import com.gmahieux.carco2.model.Car;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataLoaderJava8 data = new DataLoaderJava8();
        try {
            printCo2EmissionHallOfFame(data.loadData());
        } catch (IOException | URISyntaxException e) {
            System.out.println("An error occured :\n\tUnable to download data file");
        }
    }

    private static void printCo2EmissionHallOfFame(List<Car> data) {
        //Fill a map containing a list of co2 emission of all models for each car brand
        Map<String, List<Integer>> map = new HashMap();
        for (Car car : data) {
            if (car.getEnergy().getEnergyType().equals("ES") ||  car.getEnergy().getEnergyType().equals("GO") ) {
                List<Integer> co2List= map.get(car.getModel().getBrand());
                if(co2List == null) {
                    co2List = new ArrayList<>();
                    map.put(car.getModel().getBrand(), co2List);
                }
                co2List.add(car.getEmissions().getCo2());
            }
        }

        //Convert the list of co2 emission into an average co2 emission for each brand
        Map<String,Double> finalMap = new HashMap<>();
        for (Map.Entry<String,List<Integer>> entry: map.entrySet()) {
            int value = 0;
            for (Integer val : entry.getValue()) {
                value += val;
            }
            finalMap.put(entry.getKey(),  value / (double) entry.getValue().size());
        }

        //Sort data from the less to the most emitting brand
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(finalMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String,Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        //Display data
        for(Map.Entry<String,Double> entry : entryList ) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
