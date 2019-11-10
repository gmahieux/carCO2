package com.gmahieux.carco2.internal

import java.io.IOException

class FileDownloader {


    @Throws(IOException::class)
    fun getFile(filename : String):List<String> = throw IOException("No network")
}
