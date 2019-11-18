package com.gmahieux.carco2;

import com.gmahieux.carco2.data.DataLoaderJava8;
import com.gmahieux.carco2.model.Car;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainLambdas {

    public static void main(String[] args) {
        DataLoaderJava8 data = new DataLoaderJava8();
        try {
            printCo2EmissionHallOfFame(data.loadData());
        } catch (IOException | URISyntaxException e) {
            System.out.println("An error occured :\n\tUnable to download data file");
        }
    }

    private static void printCo2EmissionHallOfFame(List<Car> cars) {
        cars.stream()
            .filter(car -> car.getEnergy().getEnergyType().equals("ES") || car.getEnergy().getEnergyType().equals("GO"))
            .collect(Collectors.groupingBy(car -> car.getModel().getBrand(),
                Collectors.averagingInt(car -> car.getEmissions().getCo2()))
            )
            .entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getValue))
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .forEach(entry -> System.out.println(entry));

    }
}
