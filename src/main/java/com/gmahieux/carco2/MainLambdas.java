package com.gmahieux.carco2;

import com.gmahieux.carco2.data.DataLoader;
import com.gmahieux.carco2.data.DataLoaderNoLambdas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class MainLambdas {

    public static void main(String[] args) throws IOException, URISyntaxException {
        DataLoader data = new DataLoaderNoLambdas();
        data.loadData().stream()
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
