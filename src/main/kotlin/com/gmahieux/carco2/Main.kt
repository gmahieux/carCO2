package com.gmahieux.carco2

import com.gmahieux.carco2.data.DataLoaderKt
import com.gmahieux.carco2.data.DataLoadingError
import com.gmahieux.carco2.data.DataLoadingError.LocalFileNotAvailable
import com.gmahieux.carco2.data.DataLoadingError.RemoteFileNotAvailable
import com.gmahieux.carco2.model.Car

fun main() =
    DataLoaderKt().loadData()
        .fold(::displayError, ::printCo2EmissionHallOfFame)

private fun printCo2EmissionHallOfFame(cars: List<Car>) =
    cars.filter { it.energy.energyType in listOf("ES", "GO") }
        .groupBy({ it.model.brand }, { it.emissions.co2 })
        .mapValues { (_, values) -> values.filterNotNull().average() }
        .toList().sortedBy { (_, value) -> value }
        .map { (k, v) -> "$k=$v" }
        .forEach(::println)


private fun displayError(error: DataLoadingError) =
    when (error) {
        is RemoteFileNotAvailable -> "Unable to download remote file"
        is LocalFileNotAvailable -> "Unable to download local file"
    }.let {
        """|An error occured :
           |   $it
           |Here is the stacktrace
        """.trimMargin()
    }.let(::println)

