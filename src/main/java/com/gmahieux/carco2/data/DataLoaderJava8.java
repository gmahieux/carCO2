package com.gmahieux.carco2.data;

import com.gmahieux.carco2.internal.FileDownloader;
import com.gmahieux.carco2.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoaderJava8 implements DataLoader {

    public List<Car> loadData() throws IOException, URISyntaxException {
        List<String> data;
        try {
            data = loadRemoteData();
        } catch (IOException e) {
            data = loadLocalData();
        }
        data.remove(0);
        return data.stream().map(this::convertDataLineToCar).collect(Collectors.toList());
    }

    private List<String> loadRemoteData() throws IOException {
        return new FileDownloader().getFile("http://myhost:8080/data.csv");
    }

    private List<String> loadLocalData() throws URISyntaxException, IOException {
        return Files.readAllLines(Paths.get(DataLoaderJava8.class.getResource("/data.csv").toURI()));
    }

    private Car convertDataLineToCar(String line) {
        String[] col = line.split(";");
        return new Car(
            new Model(col[0], col[2], col[24], col[25]),
            col[3],
            col[8],
            new Energy(col[6], col[7]),
            col[21],
            new Consumption(toBigDecimal(col[11]), toBigDecimal(col[12]), toBigDecimal(col[13])),
            new Emissions(toIntOrNull(col[14]), toBigDecimal(col[17])));

    }

    private Integer toIntOrNull(String string) {
        Integer result = null;
        if (!string.trim().isEmpty()) {
            result = Integer.valueOf(string);
        }
        return result;
    }

    private BigDecimal toBigDecimal(String string) {
        BigDecimal result = null;
        if (!string.trim().isEmpty()) {
            result = new BigDecimal(string.replace(",", "."));
        }
        return result;
    }


}
