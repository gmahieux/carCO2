package com.gmahieux.carco2

import com.gmahieux.carco2.data.DataLoaderKt
import com.gmahieux.carco2.data.DataLoadingError

fun main() {

    DataLoaderKt().loadData()
        .fold({
            println("An error occured : " +
                when (it) {
                    is DataLoadingError.RemoteFileNotAvailable -> "Unable to download remote file"
                    is DataLoadingError.LocalFileNotAvailable -> "Unable to download local file"
                }
            )
        }, {
            it.filter { it.energy.energyType in listOf("ES", "GO") }
                .groupBy({ it.model.brand }, { it.emissions.co2 })
                .mapValues { (_, values) -> values.filterNotNull().average() }
                .toList().sortedBy { (_, value) -> value }
                .map { (k, v) -> "$k=$v" }
                .forEach(::println)
        })
}
