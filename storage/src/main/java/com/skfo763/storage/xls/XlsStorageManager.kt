package com.skfo763.storage.xls

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.skfo763.xlsconvert.generated.getColumnList
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class XlsStorageManager(private val context: Context) {

    private val workbook = HSSFWorkbook()
    private var fileUri: Uri? = null
    private var xlsFile: File? = null

    fun setSheet(sheetName: String) {
        workbook.createSheet(sheetName)
    }

    fun setRow(data: List<XlsMyInfoData>, sheetName: String) {
        if(data.isEmpty()) return
        val sheet = workbook.getSheet(sheetName)

        addHeader(sheet, data[0])
        data.forEachIndexed { index, myPolicyData ->
            addRow(sheet, index+2, myPolicyData)
        }
    }

    fun checkFileExists(filename: String): Boolean {
        xlsFile = File(context.getExternalFilesDir("documents"), addFileType(filename))
        return if(xlsFile != null && xlsFile!!.exists()) {
            fileUri = FileProvider.getUriForFile(context, "com.skfo763.my_data_app.fileProvider", xlsFile!!)
            true
        } else false
    }

    fun saveExcel(filename: String) {
        val xlsFile = File(context.getExternalFilesDir("documents"), addFileType(filename))
        try {
            workbook.write(FileOutputStream(xlsFile))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        fileUri = FileProvider.getUriForFile(context, "com.skfo763.my_data_app.fileProvider", xlsFile)
    }

    fun getUrl() = fileUri

    private fun addFileType(filename: String) = "$filename.xls"

    private fun addHeader(sheet: HSSFSheet, value: XlsMyInfoData) {
        value.getColumnList().apply {
            sheet.createRow(0).createCell(0).setCellValue(tableName)
            val row = sheet.createRow(1)
            columnList.forEachIndexed { i, triple ->
                row.createCell(i).setCellValue(triple.first)
            }
        }
    }

    private fun addRow(sheet: HSSFSheet, rowIndex: Int, value: XlsMyInfoData) {
        val row = sheet.createRow(rowIndex)

        value.getColumnList().columnList.forEachIndexed { i, triple ->
            row.createCell(i, CellType.STRING).setCellValue(triple.second.toString())
        }
    }
}