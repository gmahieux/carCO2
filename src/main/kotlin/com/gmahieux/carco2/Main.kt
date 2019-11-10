package com.gmahieux.carco2

import com.gmahieux.carco2.data.DataLoaderKt

fun main() {
    DataLoaderKt().loadData()
        .filter { it.energy.energyType in listOf("ES", "GO") }
        .groupBy({ it.model.brand }, { it.emissions.co2 })
        .mapValues { (_, values) -> values.filterNotNull().average() }
        .toList().sortedBy { (_, value) -> value }
        .map { (k, v) -> "$k=$v" }
        .forEach(::println)
}
