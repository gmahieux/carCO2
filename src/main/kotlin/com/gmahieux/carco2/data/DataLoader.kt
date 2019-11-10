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
                Model(this[0], this[2], this[24], this[25]),
                this[3],
                this[8],
                Energy(this[6], this[7]),
                this[21],
                Consumption(
                    this[11].toBigDecimal(),
                    this[12].toBigDecimal(),
                    this[13].toBigDecimal()
                ),
                Emissions(this[14].toIntOrNull(), this[17].toBigDecimal())
            )
        }
    }

    private fun String.toBigDecimal() =
        if (isNullOrBlank())
            null
        else
            BigDecimal(replace(",", "."))
}
