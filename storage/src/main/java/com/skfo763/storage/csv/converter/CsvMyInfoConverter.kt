package com.skfo763.storage.csv.converter

import com.skfo763.storage.csv.CsvMyInfoData

interface CsvMyInfoConverter {
    fun getMyInfoCsvData(): CsvMyInfoData
}