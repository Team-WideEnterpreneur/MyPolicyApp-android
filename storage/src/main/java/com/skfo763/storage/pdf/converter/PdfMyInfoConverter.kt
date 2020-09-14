package com.skfo763.storage.pdf.converter

import com.skfo763.storage.pdf.PdfMyInfoData

interface PdfMyInfoConverter {
    fun getMyInfoPdfData(): PdfMyInfoData
}