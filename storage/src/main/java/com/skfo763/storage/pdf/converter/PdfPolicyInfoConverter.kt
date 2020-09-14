package com.skfo763.storage.pdf.converter

import com.skfo763.storage.pdf.PdfPolicyInfoData

interface PdfPolicyInfoConverter {
    fun getPdfPolicyInfo(): PdfPolicyInfoData
}