package com.gmahieux.carco2.data

import arrow.core.Either
import arrow.core.Try
import arrow.core.handleErrorWith
import com.gmahieux.carco2.data.DataLoadingError.LocalFileNotAvailable
import com.gmahieux.carco2.data.DataLoadingError.RemoteFileNotAvailable
import com.gmahieux.carco2.internal.FileDownloader
import com.gmahieux.carco2.model.*
import java.io.File
import java.math.BigDecimal

sealed class DataLoadingError(open val cause: Throwable) {
    data class RemoteFileNotAvailable(override val cause: Throwable) : DataLoadingError(cause)
    data class LocalFileNotAvailable(override val cause: Throwable) : DataLoadingError(cause)
}

class DataLoaderKt {

    private val toCar = { string: String ->
        string.split(";").run {
            Car(
                model = Model(
                    brand = this[0],
                    model = this[2],
                    type = this[24],
                    swag = this[25]
                ),
                name = this[3],
                power = this[8],
                energy = Energy(this[6], this[7]),
                weight = this[21],
                consumption = Consumption(
                    urban = this[11].toBigDecimal(),
                    extraUrban = this[12].toBigDecimal(),
                    combined = this[13].toBigDecimal()
                ),
                emissions = Emissions(
                    co2 = this[14].toIntOrNull(),
                    nox = this[17].toBigDecimal()
                )
            )
        }
    }

    fun loadData(): Either<DataLoadingError, List<Car>> =
        loadRemoteData()
            .handleErrorWith {loadLocalData()}
            .map { it.drop(1).map(toCar) }

    private fun loadLocalData(): Either<DataLoadingError,List<String>> = Try {
        File(DataLoaderKt::class.java.getResource("/data.csv").toURI())
            .readLines()
    }.toEither { LocalFileNotAvailable(it) }

    private fun loadRemoteData(): Either<DataLoadingError,List<String>>  = Try {
        FileDownloader().getFile("/data.csv")
    }.toEither { RemoteFileNotAvailable(it) }

    private fun String.toBigDecimal() =
        if (isNullOrBlank())
            null
        else
            BigDecimal(replace(",", "."))


}
