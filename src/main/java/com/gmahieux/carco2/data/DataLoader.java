package com.gmahieux.carco2.data;

import com.gmahieux.carco2.model.Car;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface DataLoader {

    List<Car> loadData() throws IOException, URISyntaxException;
}
