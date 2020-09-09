package com.skfo763.storage.xls

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.skfo763.xlsconvert.generated.getColumnList
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.sl.usermodel.Sheet
import org.apache.poi.ss.usermodel.CellType
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class XlsStorageManager(private val context: Context) {

    private val workbook = HSSFWorkbook()

    fun setSheet(sheetName: String) {
        workbook.createSheet(sheetName)
    }

    fun setRow(data: List<MyPolicyData>, sheetName: String) {
        if(data.isEmpty()) return
        val sheet = workbook.getSheet(sheetName)

        addHeader(sheet, data[0])
        data.forEachIndexed { index, myPolicyData ->
            addRow(sheet, index+2, myPolicyData)
        }
    }

    fun saveExcel(filename: String): Uri? {
        val file = File(context.getExternalFilesDir(null), filename)
        try {
            FileOutputStream(file).apply {
                workbook.write(this)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(context, file.absolutePath +"에 저장되었습니다", Toast.LENGTH_SHORT).show()
        Log.d("hellohello", "${file.absolutePath} 에 저장되었습니다")

        return Uri.fromFile(file)
    }

    private fun addHeader(sheet: HSSFSheet, value: MyPolicyData) {
        value.getColumnList().apply {
            sheet.createRow(0).createCell(0).setCellValue(tableName)
            val row = sheet.createRow(1)
            columnList.forEachIndexed { i, triple ->
                row.createCell(i).setCellValue(triple.first)
            }
        }
    }

    private fun addRow(sheet: HSSFSheet, rowIndex: Int, value: MyPolicyData) {
        val row = sheet.createRow(rowIndex)

        value.getColumnList().columnList.forEachIndexed { i, triple ->
            row.createCell(i, CellType.STRING).setCellValue(triple.second.toString())
        }
    }
}