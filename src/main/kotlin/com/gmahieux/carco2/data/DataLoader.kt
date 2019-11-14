package com.gmahieux.carco2.data

import com.gmahieux.carco2.internal.FileDownloader
import com.gmahieux.carco2.model.*
import java.io.File
import java.math.BigDecimal

class DataLoaderKt : DataLoader {
    override fun loadData(): List<Car> =
        runCatching { loadRemoteData() }
            .recoverCatching { loadLocalData() }
            .map { it.drop(1) }
            .getOrThrow()
            .map (toCar)

    private fun loadLocalData() = File(DataLoaderNoLambdas::class.java.getResource("/data.csv").toURI())
        .readLines()

    private fun loadRemoteData() = FileDownloader().getFile("/data.csv")

    private val toCar = { string: String ->
        string.split(";").run {
            Car(
                model = Model(
                    brand = this[0],
                    model = this[2],
                    type = this[24],
                    swag = this[25]),
                name = this[3],
                power = this[8],
                energy = Energy(this[6], this[7]),
                weight = this[21],
                consumption =  Consumption(
                    urban = this[11].toBigDecimal(),
                    extraUrban = this[12].toBigDecimal(),
                    combined = this[13].toBigDecimal()
                ),
                emissions = Emissions(
                    co2= this[14].toIntOrNull(),
                    nox = this[17].toBigDecimal())
            )
        }
    }

    private fun String.toBigDecimal() =
        if (isNullOrBlank())
            null
        else
            BigDecimal(replace(",", "."))
}
