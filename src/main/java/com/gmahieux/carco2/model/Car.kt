package com.gmahieux.carco2.model

import java.math.BigDecimal

data class Car(
    val model: Model,
    val name: String,
    val power: String,
    val energy: Energy,
    val weight: String,
    val consumption: Consumption,
    val emissions: Emissions
) {

    override fun toString(): String {
        return model.brand + " " + name + " ( " + emissions.toString() + ")"
    }
}

data class Model(val brand: String, val model: String, val type: String, val swag: String)
data class Consumption(
    val urban: BigDecimal?,
    val extraUrban: BigDecimal?,
    val combined: BigDecimal?
)

data class Emissions(val co2: Int?, val nox: BigDecimal?)
data class Energy(val energyType: String, val hybrid: String)
