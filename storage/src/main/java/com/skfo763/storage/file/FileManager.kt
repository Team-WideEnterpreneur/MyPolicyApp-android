package com.skfo763.storage.file

import android.content.Context
import java.io.File

interface FileManager {

    companion object {
        const val TAG = "FileManager"
    }

    val isExternalStorageWritable: Boolean

    val isExternalStorageReadable: Boolean

    fun getExternalDocumentsDir(): File?

    fun getPrivateAlbumStorageDir(context: Context, name: String): File?

    fun saveFile(filename: String)

}