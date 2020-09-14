package com.skfo763.storage.csv.converter

import com.skfo763.storage.csv.CsvPolicyInfoData

interface CsvPolicyInfoConverter {
    fun getCsvPolicyInfo(): CsvPolicyInfoData
}